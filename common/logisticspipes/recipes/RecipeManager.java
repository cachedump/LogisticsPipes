package logisticspipes.recipes;

import logisticspipes.LogisticsPipes;
import logisticspipes.interfaces.ILogisticsModule;
import logisticspipes.items.ItemModule;
import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftSilicon;
import buildcraft.BuildCraftTransport;

public class RecipeManager {
	public static void loadRecipes() {
		CraftingManager craftingManager = CraftingManager.getInstance();
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsBuilderSupplierPipe, 1), new Object[]{"iPy", Character.valueOf('i'), new ItemStack(Item.dyePowder, 1, 0), Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('y'), new ItemStack(Item.dyePowder, 1,11)});
		//craftingManager.addRecipe(new ItemStack(LogisticsNetworkMonitior, 1), new Object[] { "g g", " G ", " g ", Character.valueOf('g'), Item.ingotGold, Character.valueOf('G'), BuildCraftCore.goldGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsLiquidSupplierPipe, 1), new Object[]{" B ", "lPl", " B ", Character.valueOf('l'), new ItemStack(Item.dyePowder, 1, 4), Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('B'), Item.bucketEmpty});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRemoteOrderer, 1), new Object[] { "gg", "gg", "DD", Character.valueOf('g'), Block.glass, Character.valueOf('D'), BuildCraftCore.diamondGearItem});
		
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsBasicPipe, 8), new Object[] { "grg", "GdG", "grg", Character.valueOf('g'), Block.glass, 
								   Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2),
								   Character.valueOf('d'), BuildCraftTransport.pipeItemsDiamond, 
								   Character.valueOf('r'), Block.torchRedstoneActive});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsBasicPipe, 8), new Object[] { "grg", "GdG", "grg", Character.valueOf('g'), Block.glass, 
								   Character.valueOf('G'), BuildCraftCore.goldGearItem,
								   Character.valueOf('d'), BuildCraftTransport.pipeItemsDiamond, 
								   Character.valueOf('r'), Block.torchRedstoneActive});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsProviderPipe, 1), new Object[] { "d", "P", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('d'), Item.lightStoneDust});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsProviderPipeMK2, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsProviderPipe, Character.valueOf('U'), BuildCraftCore.diamondGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsProviderPipeMK2, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsProviderPipe, Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsCraftingPipe, 1), new Object[] { "dPd", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('d'), Item.lightStoneDust});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsSatellitePipe, 1), new Object[] { "rPr", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('r'), Item.redstone});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsSupplierPipe, 1), new Object[] { "lPl", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('l'), new ItemStack(Item.dyePowder, 1, 4)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRequestPipe, 1), new Object[] { "gPg", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('g'), BuildCraftCore.goldGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRequestPipe, 1), new Object[] { "gPg", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('g'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRequestPipeMK2, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsRequestPipe, Character.valueOf('U'), BuildCraftCore.diamondGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRequestPipeMK2, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsRequestPipe, Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsCraftingPipeMK2, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsCraftingPipe, Character.valueOf('U'), BuildCraftCore.goldGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsCraftingPipeMK2, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsCraftingPipe, Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2)});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRemoteOrdererPipe, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('U'), Item.enderPearl});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsInvSysCon, 1), new Object[] {"EPE", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('E'), Item.enderPearl});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsEntrance, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsProviderPipe, Character.valueOf('U'), new ItemStack(Item.dyePowder, 1, 2)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsDestination, 1), new Object[] {"U", "B", Character.valueOf('B'), LogisticsPipes.LogisticsProviderPipe, Character.valueOf('U'), new ItemStack(Item.dyePowder, 1, 1)});
		
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsItemDisk, 1), new Object[] { "igi", "grg", "igi", Character.valueOf('i'), new ItemStack(Item.dyePowder, 1, 0), Character.valueOf('r'), Item.redstone, Character.valueOf('g'), Item.goldNugget});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK), new Object[] { "prp", "prp", "pgp", Character.valueOf('p'), Item.paper, Character.valueOf('r'), Item.redstone, Character.valueOf('g'), Item.goldNugget});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ITEMSINK), new Object[] { "CGC", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 2),
									Character.valueOf('G'), BuildCraftCore.ironGearItem, 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ITEMSINK), new Object[] { " G ", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 2),
									Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1), 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PASSIVE_SUPPLIER), new Object[] { "CGC", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 1),
									Character.valueOf('G'), BuildCraftCore.ironGearItem, 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PASSIVE_SUPPLIER), new Object[] { " G ", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 1),
									Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1), 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR), new Object[] { "CGC", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 4),
									Character.valueOf('G'), BuildCraftCore.ironGearItem, 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR), new Object[] { " G ", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 4),
									Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1), 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR),
									Character.valueOf('U'), Item.redstone});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR_MK2), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR),
									Character.valueOf('U'), BuildCraftCore.goldGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR_MK2), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR),
									Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR_MK2), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR),
									Character.valueOf('U'), BuildCraftCore.goldGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR_MK2), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR),
									Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR_MK3), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR_MK2),
									Character.valueOf('U'), BuildCraftCore.diamondGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR_MK3), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.EXTRACTOR_MK2),
									Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR_MK3), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR_MK2),
									Character.valueOf('U'), BuildCraftCore.diamondGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR_MK3), new Object[] {"U", "B",
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.ADVANCED_EXTRACTOR_MK2),
									Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.POLYMORPHIC_ITEMSINK), new Object[] { "CGC", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 14),
									Character.valueOf('G'), BuildCraftCore.ironGearItem, 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.POLYMORPHIC_ITEMSINK), new Object[] { " G ", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 14),
									Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1), 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.QUICKSORT), new Object[] { "CGC", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 4),
									Character.valueOf('G'), BuildCraftCore.diamondGearItem, 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.QUICKSORT), new Object[] { " G ", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 4),
									Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3), 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.TERMINUS), new Object[] { "CGD", "rBr", "DrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 0),
									Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 5),
									Character.valueOf('G'), BuildCraftCore.ironGearItem, 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.TERMINUS), new Object[] { " G ", "rBr", "CrD", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 0),
									Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 5),
									Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1), 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PROVIDER), new Object[] { "CGC", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 4),
									Character.valueOf('G'), BuildCraftCore.goldGearItem, 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PROVIDER), new Object[] { " G ", "rBr", "CrC", 
									Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 4),
									Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2), 
									Character.valueOf('r'), Item.redstone, 
									Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.BLANK)});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PROVIDER_MK2), new Object[] {"U", "B",
			Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PROVIDER),
			Character.valueOf('U'), BuildCraftCore.diamondGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PROVIDER_MK2), new Object[] {"U", "B",
			Character.valueOf('B'), new ItemStack(LogisticsPipes.ModuleItem, 1, ItemModule.PROVIDER),
			Character.valueOf('U'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});


		for(int i=0; i<1000;i++) {
			ILogisticsModule module = ((ItemModule)LogisticsPipes.ModuleItem).getModuleForItem(new ItemStack(LogisticsPipes.ModuleItem, 1, i), null, null, null, null, null);
			if(module != null) {
				NBTTagCompound nbt = new NBTTagCompound();
				boolean force = false;
				try {
					module.writeToNBT(nbt, "");
				} catch(Exception e) {
					force = true;
				}
				if(!nbt.equals(new NBTTagCompound())) {
					registerShapelessResetRecipe(LogisticsPipes.ModuleItem, i, LogisticsPipes.ModuleItem, i);
				}
			}
		}
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe1, 1), new Object[] { "iii", "iPi", "iii", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), Item.redstone});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe1, 1), new Object[] { " i ","iPi", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 0)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe2, 1), new Object[] { "iii", "iPi", "iii", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), Item.ingotIron});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe2, 1), new Object[] { " i ","iPi", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe3, 1), new Object[] { "iii", "iPi", "iii", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), Item.ingotGold});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe3, 1), new Object[] { " i ","iPi", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe4, 1), new Object[] { "iii", "iPi", "iii", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), Item.diamond});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe4, 1), new Object[] { " i ","iPi", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsChassiPipe5, 1), new Object[] { "gig", "iPi", "gig", Character.valueOf('P'), LogisticsPipes.LogisticsBasicPipe, Character.valueOf('i'), Block.blockDiamond, Character.valueOf('g'), Block.blockGold});

		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsNetworkMonitior, 1), new Object[] { "g g", " G ", " g ", Character.valueOf('g'), Item.ingotGold, Character.valueOf('G'), BuildCraftCore.goldGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsNetworkMonitior, 1), new Object[] { "g g", " G ", " g ", Character.valueOf('g'), Item.ingotGold, Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2)});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRemoteOrderer, 1), new Object[] { "gg", "gg", "DD", Character.valueOf('g'), Block.glass, Character.valueOf('D'), BuildCraftCore.diamondGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsRemoteOrderer, 1), new Object[] { "gg", "gg", "DD", Character.valueOf('g'), Block.glass, Character.valueOf('D'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsCraftingSignCreator, 1), new Object[] {"G G", " S ", " D ", Character.valueOf('G'), BuildCraftCore.goldGearItem, Character.valueOf('S'), Item.sign, Character.valueOf('D'), BuildCraftCore.diamondGearItem});
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.LogisticsCraftingSignCreator, 1), new Object[] {"G G", " S ", " D ", Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2), Character.valueOf('S'), Item.sign, Character.valueOf('D'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 3)});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.logisticsSolidBlock, 1, 0), new Object[] { "iCi", "i i", "iri", 
			Character.valueOf('C'), new ItemStack(Block.workbench, 1),
			Character.valueOf('r'), Item.redstone, 
			Character.valueOf('i'), Item.ingotIron});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.logisticsSolidBlock, 1, 1), new Object[] { "iGi", "rBr", "iii", 
			Character.valueOf('G'), new ItemStack(BuildCraftCore.goldGearItem, 1),
			Character.valueOf('r'), Item.redstone, 
			Character.valueOf('B'), LogisticsPipes.LogisticsBasicPipe, 
			Character.valueOf('i'), Item.ingotIron});
		
		craftingManager.addRecipe(new ItemStack(LogisticsPipes.logisticsSolidBlock, 1, 1), new Object[] { "iGi", "rBr", "iii", 
			Character.valueOf('G'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2),
			Character.valueOf('r'), Item.redstone, 
			Character.valueOf('B'), LogisticsPipes.LogisticsBasicPipe, 
			Character.valueOf('i'), Item.ingotIron});

	}
	
	private static void registerShapelessResetRecipe(Item fromItem, int fromData, Item toItem, int toData) {
		for(int j=1;j < 10; j++) {
			Object[] obj = new Object[j];
			for(int k=0;k<j;k++) {
				obj[k] = new ItemStack(fromItem, 1, toData);
			}
			CraftingManager.getInstance().addShapelessRecipe(new ItemStack(toItem, j, fromData), obj);
		}
	}
}
