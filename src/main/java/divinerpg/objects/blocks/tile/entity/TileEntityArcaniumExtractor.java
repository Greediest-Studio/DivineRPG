package divinerpg.objects.blocks.tile.entity;

import divinerpg.compat.jei.base.ArcaniumExtractorRecipeWrapper;
import divinerpg.registry.BlockRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class TileEntityArcaniumExtractor extends TileEntityModFurnace {

    private static final Map<Item, ItemStack> smeltingRecipes = new HashMap<>();
    private static final Map<Item, Integer> fuelMap = new HashMap<>();

    public String getFuranceName() {
        return "tile.arcanium_extractor.name";
    }

    int getFurnaceSpeed() {
        return 100;
    }

    public boolean needsFuel() {
        return true;
    }

    public void updateBlockState(boolean isBurning) {
    }

    static {
        addSmeltingRecipe(
                new ItemStack(Item.getItemFromBlock(BlockRegistry.rawArcanium)),
                new ItemStack(ItemRegistry.arcanium)
        );

        addFuel(new ItemStack(ItemRegistry.collector), 400);
    }

    public static void addSmeltingRecipe(ItemStack input, ItemStack output) {
        smeltingRecipes.put(input.getItem(), output);
    }

    public static void removeSmeltingRecipeByInput(ItemStack input) {
        smeltingRecipes.remove(input.getItem());
    }

    public static void removeSmeltingRecipeByOutput(ItemStack output) {
        smeltingRecipes.entrySet().removeIf(entry ->
                ItemStack.areItemsEqual(entry.getValue(), output)
        );
    }

    public static void addFuel(ItemStack fuel, int burnTime) {
        fuelMap.put(fuel.getItem(), burnTime);
    }

    public static void removeFuel(ItemStack fuel) {
        fuelMap.remove(fuel.getItem());
    }
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (stack.isEmpty()) return false;

        if (index == 0) {

            return smeltingRecipes.containsKey(stack.getItem());
        } else if (index == 1) {

            return fuelMap.containsKey(stack.getItem());
        }
        return false;
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        return fuelMap.getOrDefault(stack.getItem(), 0);
    }

    @Override
    public ItemStack getSmeltingResult(ItemStack stack) {
        if (stack.isEmpty() || !smeltingRecipes.containsKey(stack.getItem())) {
            return ItemStack.EMPTY;
        }
        return smeltingRecipes.get(stack.getItem()).copy();
    }

    public static List<ItemStack> getFuelItems() {
        List<ItemStack> fuels = new ArrayList<>();
        for (Item item : fuelMap.keySet()) {
            fuels.add(new ItemStack(item));
        }
        return fuels;
    }

    public static Map<Item, ItemStack> getSmeltingRecipes() {
        return Collections.unmodifiableMap(smeltingRecipes);
    }

    public static int getBurnTime(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        return fuelMap.getOrDefault(stack.getItem(), 0);
    }
}