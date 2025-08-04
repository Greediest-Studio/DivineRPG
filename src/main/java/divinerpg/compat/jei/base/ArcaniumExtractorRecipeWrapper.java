package divinerpg.compat.jei.base;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import divinerpg.objects.blocks.tile.entity.TileEntityArcaniumExtractor;

import java.util.*;

public class ArcaniumExtractorRecipeWrapper implements IRecipeWrapper {
    private final ItemStack input;
    private final List<ItemStack> fuels;
    private final ItemStack output;

    public ArcaniumExtractorRecipeWrapper(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
        this.fuels = TileEntityArcaniumExtractor.getFuelItems();
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<List<ItemStack>> inputs = new ArrayList<>();
        inputs.add(Collections.singletonList(input)); // 输入物品（单个）
        inputs.add(fuels); // 燃料（所有可用燃料）

        ingredients.setInputLists(ItemStack.class, inputs);
        ingredients.setOutput(ItemStack.class, output);
    }
}

