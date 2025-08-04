package divinerpg.compat.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import divinerpg.objects.blocks.tile.entity.TileEntityArcaniumExtractor;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.divinerpg.ArcaniumExtractor")
public class ArcaniumExtractorCT {

    // 添加配方
    @ZenMethod
    public static void addRecipe(IIngredient input, IItemStack output) {
        ItemStack inputStack = CraftTweakerMC.getItemStack(input);
        ItemStack outputStack = CraftTweakerMC.getItemStack(output);

        TileEntityArcaniumExtractor.addSmeltingRecipe(inputStack, outputStack);
    }

    // 移除配方
    @ZenMethod
    public static void removeRecipeByInput(IIngredient input) {
        ItemStack inputStack = CraftTweakerMC.getItemStack(input);
        TileEntityArcaniumExtractor.removeSmeltingRecipeByInput(inputStack);
    }

    // 移除配方
    @ZenMethod
    public static void removeRecipeByOutput(IIngredient output) {
        ItemStack outputStack = CraftTweakerMC.getItemStack(output);
        TileEntityArcaniumExtractor.removeSmeltingRecipeByOutput(outputStack);
    }

    // 添加燃料
    @ZenMethod
    public static void addFuel(IIngredient fuel, int burnTime) {
        ItemStack fuelStack = CraftTweakerMC.getItemStack(fuel);
        TileEntityArcaniumExtractor.addFuel(fuelStack, burnTime);
    }

    // 移除燃料
    @ZenMethod
    public static void removeFuel(IIngredient fuel) {
        ItemStack fuelStack = CraftTweakerMC.getItemStack(fuel);
        TileEntityArcaniumExtractor.removeFuel(fuelStack);
    }
}

