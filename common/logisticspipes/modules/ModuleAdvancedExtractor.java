package logisticspipes.modules;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import logisticspipes.gui.hud.modules.HUDAdvancedExtractor;
import logisticspipes.interfaces.IChassiePowerProvider;
import logisticspipes.interfaces.IClientInformationProvider;
import logisticspipes.interfaces.IHUDModuleHandler;
import logisticspipes.interfaces.IHUDModuleRenderer;
import logisticspipes.interfaces.ILogisticsModule;
import logisticspipes.interfaces.IModuleInventoryReceive;
import logisticspipes.interfaces.IModuleWatchReciver;
import logisticspipes.interfaces.ISendRoutedItem;
import logisticspipes.interfaces.ISneakyOrientationreceiver;
import logisticspipes.interfaces.IWorldProvider;
import logisticspipes.logisticspipes.IInventoryProvider;
import logisticspipes.logisticspipes.SidedInventoryAdapter;
import logisticspipes.network.GuiIDs;
import logisticspipes.network.NetworkConstants;
import logisticspipes.network.packets.PacketModuleInteger;
import logisticspipes.network.packets.PacketModuleInvContent;
import logisticspipes.network.packets.PacketPipeInteger;
import logisticspipes.proxy.MainProxy;
import logisticspipes.proxy.SimpleServiceLocator;
import logisticspipes.utils.ISimpleInventoryEventHandler;
import logisticspipes.utils.ItemIdentifierStack;
import logisticspipes.utils.SimpleInventory;
import logisticspipes.utils.SinkReply;
import logisticspipes.utils.SneakyOrientation;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraftforge.common.ISidedInventory;
import buildcraft.api.core.Orientations;
import buildcraft.core.utils.Utils;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ModuleAdvancedExtractor implements ILogisticsModule, ISneakyOrientationreceiver, IClientInformationProvider, IHUDModuleHandler, IModuleWatchReciver, IModuleInventoryReceive, ISimpleInventoryEventHandler {

	protected int currentTick = 0;

	private final SimpleInventory _filterInventory = new SimpleInventory(9, "Item list", 1);
	private boolean _itemsIncluded = true;
	protected IInventoryProvider _invProvider;
	protected ISendRoutedItem _itemSender;
	protected IChassiePowerProvider _power;
	protected SneakyOrientation _sneakyOrientation = SneakyOrientation.Default;
	
	private int slot = 0;
	private int xCoord = 0;
	private int yCoord = 0;
	private int zCoord = 0;
	
	private IHUDModuleRenderer HUD = new HUDAdvancedExtractor(this);
	
	private final List<EntityPlayer> localModeWatchers = new ArrayList<EntityPlayer>();
	

	public ModuleAdvancedExtractor() {
		_filterInventory.addListener(this);
	}

	@Override
	public void registerHandler(IInventoryProvider invProvider, ISendRoutedItem itemSender, IWorldProvider world, IChassiePowerProvider powerprovider) {
		_invProvider = invProvider;
		_itemSender = itemSender;
		_power = powerprovider;
	}
	
	public SimpleInventory getFilterInventory() {
		return _filterInventory;
	}
	
	public SneakyOrientation getSneakyOrientation(){
		return _sneakyOrientation;
	}
	
	public void setSneakyOrientation(SneakyOrientation sneakyOrientation){
		_sneakyOrientation = sneakyOrientation;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound, String prefix) {
		_filterInventory.readFromNBT(nbttagcompound, prefix);
		setItemsIncluded(nbttagcompound.getBoolean("itemsIncluded"));
		_sneakyOrientation = SneakyOrientation.values()[nbttagcompound.getInteger("sneakyorientation")];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound, String prefix) {
		_filterInventory.writeToNBT(nbttagcompound, prefix);
		nbttagcompound.setBoolean("itemsIncluded", areItemsIncluded());
		nbttagcompound.setInteger("sneakyorientation", _sneakyOrientation.ordinal());
	}

	@Override
	public int getGuiHandlerID() {
		return GuiIDs.GUI_Module_Advanced_Extractor_ID;
	}

	@Override
	public SinkReply sinksItem(ItemStack item) {
		return null;
	}

	@Override
	public ILogisticsModule getSubModule(int slot) {
		return null;
	}

	protected int ticksToAction() {
		return 100;
	}

	protected int itemsToExtract() {
		return 1;
	}
	
	protected int neededEnergy() {
		return 6;
	}
	
	public boolean connectedToSidedInventory() {
		return _invProvider.getRawInventory() instanceof ISidedInventory;
	}
	
	@Override
	public void tick() {
		if (++currentTick < ticksToAction())
			return;
		currentTick = 0;

		IInventory inventory = _invProvider.getRawInventory();
		if (inventory == null) return;
		if (inventory instanceof ISidedInventory) {
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
			inventory = new SidedInventoryAdapter((ISidedInventory) inventory, extractOrientation);
		}
		
		ItemStack stack = checkExtract(inventory, true, _invProvider.inventoryOrientation().reverse());
		if (stack == null) return;
		_itemSender.sendStack(stack);
	}

	public ItemStack checkExtract(IInventory inventory, boolean doRemove, Orientations from) {
		IInventory inv = Utils.getInventory(inventory);
		ItemStack result = checkExtractGeneric(inv, doRemove, from);
		return result;
	}

	public ItemStack checkExtractGeneric(IInventory inventory, boolean doRemove, Orientations from) {
		for (int k = 0; k < inventory.getSizeInventory(); k++) {
			if ((inventory.getStackInSlot(k) == null) || (inventory.getStackInSlot(k).stackSize <= 0)) {
				continue;
			}
			
			ItemStack slot = inventory.getStackInSlot(k);
			if ((slot != null) && (slot.stackSize > 0) && (CanExtract(slot)) && (shouldSend(slot))) {
				if (doRemove) {
					int count = Math.min(itemsToExtract(), slot.stackSize);

					while(!_power.useEnergy(neededEnergy() * count) && count > 0) {
						count--;
					}
					
					if(count <= 0) {
						return null;
					}
					
					return inventory.decrStackSize(k, count);
				}
				return slot;
			}
		}
		return null;
	}

	public boolean CanExtract(ItemStack item) {
		for (int i = 0; i < this._filterInventory.getSizeInventory(); i++) {
			
			ItemStack stack = this._filterInventory.getStackInSlot(i);
			if ((stack != null) && (stack.itemID == item.itemID)) {
				if (Item.itemsList[item.itemID].isDamageable()) {
					return areItemsIncluded();
				}
				if (stack.getItemDamage() == item.getItemDamage()) {
					return areItemsIncluded();
				}
			}
		}
		return !areItemsIncluded();
	}

	protected boolean shouldSend(ItemStack stack) {
		return SimpleServiceLocator.logisticsManager.hasDestination(stack, true, _itemSender.getSourceUUID(), true);
	}

	public boolean areItemsIncluded() {
		return _itemsIncluded;
	}

	public void setItemsIncluded(boolean flag) {
		_itemsIncluded = flag;
		MainProxy.sendToPlayerList(new PacketModuleInteger(NetworkConstants.ADVANCED_EXTRACTOR_MODULE_INCLUDED_RESPONSE, xCoord, yCoord, zCoord, slot, areItemsIncluded() ? 1 : 0).getPacket(), localModeWatchers);
	}
	
	@Override
	public List<String> getClientInformation() {
		List<String> list = new ArrayList<String>();
		list.add(areItemsIncluded() ? "Included" : "Excluded");
		list.add("Extraction: " + _sneakyOrientation.name());
		list.add("Filter: ");
		list.add("<inventory>");
		list.add("<that>");
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
	public void InventoryChanged(SimpleInventory inventory) {
		MainProxy.sendToPlayerList(new PacketModuleInvContent(NetworkConstants.MODULE_INV_CONTENT, xCoord, yCoord, zCoord, slot, ItemIdentifierStack.getListFromInventory(inventory)).getPacket(), localModeWatchers);
	}

	@Override
	public void handleInvContent(LinkedList<ItemIdentifierStack> list) {
		_filterInventory.handleItemIdentifierList(list);
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
		PacketDispatcher.sendPacketToPlayer(new PacketModuleInvContent(NetworkConstants.MODULE_INV_CONTENT, xCoord, yCoord, zCoord, slot, ItemIdentifierStack.getListFromInventory(_filterInventory)).getPacket(), (Player)player);
		PacketDispatcher.sendPacketToPlayer(new PacketModuleInteger(NetworkConstants.EXTRACTOR_MODULE_RESPONSE, xCoord, yCoord, zCoord, slot, _sneakyOrientation.ordinal()).getPacket(), (Player)player);
		PacketDispatcher.sendPacketToPlayer(new PacketModuleInteger(NetworkConstants.ADVANCED_EXTRACTOR_MODULE_INCLUDED_RESPONSE, xCoord, yCoord, zCoord, slot, areItemsIncluded() ? 1 : 0).getPacket(), (Player)player);
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
