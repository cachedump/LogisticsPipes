package logisticspipes.modules;

import java.util.ArrayList;
import java.util.List;

import logisticspipes.gui.hud.modules.HUDExtractor;
import logisticspipes.interfaces.IChassiePowerProvider;
import logisticspipes.interfaces.IClientInformationProvider;
import logisticspipes.interfaces.IHUDModuleHandler;
import logisticspipes.interfaces.IHUDModuleRenderer;
import logisticspipes.interfaces.ILogisticsModule;
import logisticspipes.interfaces.IModuleWatchReciver;
import logisticspipes.interfaces.ISendRoutedItem;
import logisticspipes.interfaces.ISneakyOrientationreceiver;
import logisticspipes.interfaces.IWorldProvider;
import logisticspipes.logisticspipes.IInventoryProvider;
import logisticspipes.logisticspipes.SidedInventoryAdapter;
import logisticspipes.network.GuiIDs;
import logisticspipes.network.NetworkConstants;
import logisticspipes.network.packets.PacketModuleInteger;
import logisticspipes.network.packets.PacketPipeInteger;
import logisticspipes.proxy.MainProxy;
import logisticspipes.proxy.SimpleServiceLocator;
import logisticspipes.utils.SinkReply;
import logisticspipes.utils.SneakyOrientation;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraftforge.common.ISidedInventory;
import buildcraft.api.core.Orientations;
import buildcraft.api.inventory.ISpecialInventory;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ModuleExtractor implements ILogisticsModule, ISneakyOrientationreceiver, IClientInformationProvider, IHUDModuleHandler, IModuleWatchReciver {

	//protected final int ticksToAction = 100;
	private int currentTick = 0;
	
	private IInventoryProvider _invProvider;
	private ISendRoutedItem _itemSender;
	private IChassiePowerProvider _power;
	private SneakyOrientation _sneakyOrientation = SneakyOrientation.Default;
	
	private int slot = 0;
	private int xCoord = 0;
	private int yCoord = 0;
	private int zCoord = 0;
	
	private IHUDModuleRenderer HUD = new HUDExtractor(this);
	
	private final List<EntityPlayer> localModeWatchers = new ArrayList<EntityPlayer>();
	
	public ModuleExtractor() {
		
	}

	@Override
	public void registerHandler(IInventoryProvider invProvider, ISendRoutedItem itemSender, IWorldProvider world, IChassiePowerProvider powerprovider) {
		_invProvider = invProvider;
		_itemSender = itemSender;
		_power = powerprovider;
	}

	protected int ticksToAction(){
		return 100;
	}

	protected int itemsToExtract(){
		return 1;
	}
	
	protected int neededEnergy() {
		return 5;
	}
	
	public SneakyOrientation getSneakyOrientation(){
		return _sneakyOrientation;
	}
	
	public void setSneakyOrientation(SneakyOrientation sneakyOrientation){
		_sneakyOrientation = sneakyOrientation;
		MainProxy.sendToPlayerList(new PacketModuleInteger(NetworkConstants.EXTRACTOR_MODULE_RESPONSE, xCoord, yCoord, zCoord, slot, _sneakyOrientation.ordinal()).getPacket(), localModeWatchers);
	}
	
	@Override
	public SinkReply sinksItem(ItemStack item) {
		return null;
	}

	@Override
	public int getGuiHandlerID() {
		return GuiIDs.GUI_Module_Extractor_ID;
	}

	@Override
	public ILogisticsModule getSubModule(int slot) {return null;}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound, String prefix) {
		_sneakyOrientation = SneakyOrientation.values()[nbttagcompound.getInteger("sneakyorientation")];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound, String prefix) {
		nbttagcompound.setInteger("sneakyorientation", _sneakyOrientation.ordinal());
	}

	@Override
	public void tick() {
		if (++currentTick < ticksToAction()) return;
		currentTick = 0;
		
		//Extract Item
		IInventory targetInventory = _invProvider.getRawInventory();
		if (targetInventory == null) return;
		Orientations extractOrientation;
		switch (_sneakyOrientation){
			case Bottom:
				extractOrientation = Orientations.YNeg;
				break;
			case Top:
				extractOrientation = Orientations.YPos;
				break;
			case Side:
				extractOrientation = Orientations.ZPos;
				break;
			default:
				extractOrientation = _invProvider.inventoryOrientation().reverse();
		}
		
		if (targetInventory instanceof ISpecialInventory){
			ItemStack[] stack = ((ISpecialInventory) targetInventory).extractItem(false, extractOrientation,1);
			if (stack == null) return;
			if (stack.length < 1) return;
			if (stack[0] == null) return;
			if (!shouldSend(stack[0])) return;
			stack = ((ISpecialInventory) targetInventory).extractItem(true, extractOrientation,1);
			_itemSender.sendStack(stack[0]);
			return;
		}
		
		if (targetInventory instanceof ISidedInventory){
			targetInventory = new SidedInventoryAdapter((ISidedInventory) targetInventory, extractOrientation);
		}
		
		ItemStack stackToSend;
		
		for (int i = 0; i < targetInventory.getSizeInventory(); i++){
			stackToSend = targetInventory.getStackInSlot(i);
			if (stackToSend == null) continue;
			
			if (!this.shouldSend(stackToSend)) continue;
			
			int count = Math.min(itemsToExtract(), stackToSend.stackSize);
			
			while(!_power.useEnergy(neededEnergy() * count) && count > 0) {
				count--;
			}
			
			if(count <= 0) {
				break;
			}
			
			stackToSend = targetInventory.decrStackSize(i, count);
			_itemSender.sendStack(stackToSend);
			break;
		}
	}
	
	protected boolean shouldSend(ItemStack stack){
		return SimpleServiceLocator.logisticsManager.hasDestination(stack, true, _itemSender.getSourceUUID(), true);
	}
	
	@Override
	public List<String> getClientInformation() {
		List<String> list = new ArrayList<String>();
		list.add("Extraction: " + _sneakyOrientation.name());
		return list;
	}

	@Override
	public void registerPosition(int xCoord, int yCoord, int zCoord, int slot) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.slot = slot;
	}
	
	@Override
	public void startWatching() {
		PacketDispatcher.sendPacketToServer(new PacketPipeInteger(NetworkConstants.HUD_START_WATCHING_MODULE, xCoord, yCoord, zCoord, slot).getPacket());
	}

	@Override
	public void stopWatching() {
		PacketDispatcher.sendPacketToServer(new PacketPipeInteger(NetworkConstants.HUD_START_WATCHING_MODULE, xCoord, yCoord, zCoord, slot).getPacket());
	}

	@Override
	public void startWatching(EntityPlayer player) {
		localModeWatchers.add(player);
		MainProxy.sendToPlayerList(new PacketModuleInteger(NetworkConstants.EXTRACTOR_MODULE_RESPONSE, xCoord, yCoord, zCoord, slot, _sneakyOrientation.ordinal()).getPacket(), localModeWatchers);
	}

	@Override
	public void stopWatching(EntityPlayer player) {
		localModeWatchers.remove(player);
	}

	@Override
	public IHUDModuleRenderer getRenderer() {
		return HUD;
	}
}
