package gtPlusPlus.xmod.gregtech.loaders;

import net.minecraft.item.ItemStack;

import gregtech.api.util.GT_ModHandler;

import gtPlusPlus.api.objects.Logger;
import gtPlusPlus.core.lib.CORE;
import gtPlusPlus.core.lib.LoadedMods;
import gtPlusPlus.core.material.Material;
import gtPlusPlus.core.util.minecraft.RecipeUtils;

public class RecipeGen_ShapedCrafting  implements Runnable{

	final Material toGenerate;

	public RecipeGen_ShapedCrafting(final Material M){
		this.toGenerate = M;
	}

	@Override
	public void run() {
		generateRecipes(this.toGenerate);
	}

	public static void generateRecipes(final Material material){
		Logger.WARNING("Generating Shaped Crafting recipes for "+material.getLocalizedName()); //TODO

		if (!CORE.GTNH) {
			//Nuggets
			GT_ModHandler.addShapelessCraftingRecipe(
					material.getIngot(1),
					new Object[]{
							material.getNugget(1),
							material.getNugget(1),
							material.getNugget(1),
							material.getNugget(1),
							material.getNugget(1),
							material.getNugget(1),
							material.getNugget(1),
							material.getNugget(1),
							material.getNugget(1)
					});
		}
		
		//Plates
		
		//Single Plate Shaped/Shapeless
		GT_ModHandler.addCraftingRecipe(
				material.getPlate(1),
				gregtech.api.util.GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | gregtech.api.util.GT_ModHandler.RecipeBits.BUFFERED,
				new Object[]{"h", "B", "I",
						Character.valueOf('I'),
						material.getIngot(1),
						Character.valueOf('B'),
						material.getIngot(1)});

		GT_ModHandler.addShapelessCraftingRecipe(
				material.getPlate(1),
				new Object[]{gregtech.api.enums.ToolDictNames.craftingToolForgeHammer,
						material.getIngot(1),
						material.getIngot(1)});

		//Double Plate Shaped/Shapeless
		GT_ModHandler.addCraftingRecipe(
				material.getPlateDouble(1),
				gregtech.api.util.GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | gregtech.api.util.GT_ModHandler.RecipeBits.BUFFERED,
				new Object[]{"I", "B", "h",
						Character.valueOf('I'),
						material.getPlate(1),
						Character.valueOf('B'),
						material.getPlate(1)});

		GT_ModHandler.addShapelessCraftingRecipe(
				material.getPlateDouble(1),
				new Object[]{gregtech.api.enums.ToolDictNames.craftingToolForgeHammer,
						material.getPlate(1),
						material.getPlate(1)});

		//Ring Recipe
		if (!material.isRadioactive){
			if (LoadedMods.DreamCraft){
				if (RecipeUtils.recipeBuilder(
						"craftingToolHardHammer", null, null,
						"craftingToolFile", material.getRod(1), null,
						null, null, null,
						material.getRing(1))){
					Logger.WARNING("GT:NH Ring Recipe: "+material.getLocalizedName()+" - Success");
				}
				else {
					Logger.WARNING("GT:NH Ring Recipe: "+material.getLocalizedName()+" - Failed");
				}
			}
			else {
				if (RecipeUtils.recipeBuilder(
						"craftingToolHardHammer", null, null,
						null, material.getRod(1), null,
						null, null, null,
						material.getRing(1))){
					Logger.WARNING("Ring Recipe: "+material.getLocalizedName()+" - Success");
				}
				else {
					Logger.WARNING("Ring Recipe: "+material.getLocalizedName()+" - Failed");
				}
			}
		}


		//Framebox Recipe
		if (!material.isRadioactive){
			final ItemStack stackStick = material.getRod(1);
			if (RecipeUtils.recipeBuilder(
					stackStick, stackStick, stackStick,
					stackStick, "craftingToolWrench", stackStick,
					stackStick, stackStick, stackStick,
					material.getFrameBox(2))){
				Logger.WARNING("Framebox Recipe: "+material.getLocalizedName()+" - Success");
			}
			else {
				Logger.WARNING("Framebox Recipe: "+material.getLocalizedName()+" - Failed");
			}
		}


		/*final int tVoltageMultiplier = material.getMeltingPointK() >= 1600 ? 60 : 15;


		//Add a shapeless recipe for each dust this way - Compat mode.
		ItemStack[] inputStacks = material.getMaterialComposites();
		ItemStack outputStacks = material.getDust(material.smallestStackSizeWhenProcessing);

		if (inputStacks.length > 0 && tVoltageMultiplier == 15){
			Utils.LOG_WARNING(ItemUtils.getArrayStackNames(inputStacks));
			long[] inputStackSize = material.vSmallestRatio;
			if (inputStackSize != null){
				for (short x=0;x<inputStacks.length;x++){
					if (inputStacks[x] != null && inputStackSize[x] != 0)
					inputStacks[x].stackSize = (int) inputStackSize[x];
				}
				Utils.LOG_WARNING(ItemUtils.getArrayStackNames(inputStacks));
				if (RecipeUtils.buildShapelessRecipe(
						outputStacks,
						inputStacks
						)){
					Utils.LOG_WARNING("Shapeless Crafting Recipe: "+material.getLocalizedName()+" - Success");
				}
				else {
					Utils.LOG_WARNING("Shapeless Crafting Recipe: "+material.getLocalizedName()+" - Failed");
				}
			}
		}	*/


		//Shaped Recipe - Bolts
		if (!material.isRadioactive){
			if (RecipeUtils.recipeBuilder(
					"craftingToolSaw", null, null,
					null, material.getRod(1), null,
					null, null, null,
					material.getBolt(2))){
				Logger.WARNING("Bolt Recipe: "+material.getLocalizedName()+" - Success");
			}
			else {
				Logger.WARNING("Bolt Recipe: "+material.getLocalizedName()+" - Failed");
			}
		}


		//Shaped Recipe - Ingot to Rod
		if (RecipeUtils.recipeBuilder(
				"craftingToolFile", null, null,
				null, material.getIngot(1), null,
				null, null, null,
				material.getRod(1))){
			Logger.WARNING("Rod Recipe: "+material.getLocalizedName()+" - Success");
		}
		else {
			Logger.WARNING("Rod Recipe: "+material.getLocalizedName()+" - Failed");
		}


		//Shaped Recipe - Long Rod to two smalls
		if (RecipeUtils.recipeBuilder(
				"craftingToolSaw", null, null,
				material.getLongRod(1), null, null,
				null, null, null,
				material.getRod(2))){
			Logger.WARNING("Rod Recipe: "+material.getLocalizedName()+" - Success");
		}
		else {
			Logger.WARNING("Rod Recipe: "+material.getLocalizedName()+" - Failed");
		}

		//Two small to long rod
		if (RecipeUtils.recipeBuilder(
				material.getRod(1), "craftingToolHardHammer", material.getRod(1),
				null, null, null,
				null, null, null,
				material.getLongRod(1))){
			Logger.WARNING("Long Rod Recipe: "+material.getLocalizedName()+" - Success");
		}
		else {
			Logger.WARNING("Long Rod Recipe: "+material.getLocalizedName()+" - Failed");
		}

		//Rotor Recipe
		if (!material.isRadioactive){
			if (RecipeUtils.recipeBuilder(
					material.getPlate(1), "craftingToolHardHammer", material.getPlate(1),
					material.getScrew(1), material.getRing(1), "craftingToolFile",
					material.getPlate(1), "craftingToolScrewdriver", material.getPlate(1),
					material.getRotor(1))){
				Logger.WARNING("Rotor Recipe: "+material.getLocalizedName()+" - Success");
			}
			else {
				Logger.WARNING("Rotor Recipe: "+material.getLocalizedName()+" - Failed");
			}
		}

		//Gear Recipe
		if (!material.isRadioactive){
			if (RecipeUtils.recipeBuilder(
					material.getRod(1), material.getPlate(1), material.getRod(1),
					material.getPlate(1), "craftingToolWrench", material.getPlate(1),
					material.getRod(1), material.getPlate(1), material.getRod(1),
					material.getGear(1))){
				Logger.WARNING("Gear Recipe: "+material.getLocalizedName()+" - Success");
			}
			else {
				Logger.WARNING("Gear Recipe: "+material.getLocalizedName()+" - Failed");
			}
		}

		//Screws
		if (!material.isRadioactive){
			if (RecipeUtils.recipeBuilder(
					"craftingToolFile", material.getBolt(1), null,
					material.getBolt(1), null, null,
					null, null, null,
					material.getScrew(1))){
				Logger.WARNING("Screw Recipe: "+material.getLocalizedName()+" - Success");
			}
			else {
				Logger.WARNING("Screw Recipe: "+material.getLocalizedName()+" - Failed");
			}
		}
	}
}
