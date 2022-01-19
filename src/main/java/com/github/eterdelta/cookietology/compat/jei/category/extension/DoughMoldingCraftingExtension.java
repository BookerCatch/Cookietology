package com.github.eterdelta.cookietology.compat.jei.category.extension;

import com.github.eterdelta.cookietology.item.crafting.special.DoughMoldingRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.plugins.vanilla.crafting.CraftingCategoryExtension;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class DoughMoldingCraftingExtension extends CraftingCategoryExtension<DoughMoldingRecipe> {
    public DoughMoldingCraftingExtension(DoughMoldingRecipe recipe) {
        super(recipe);
    }

    @Override
    public void setIngredients(IIngredients ingredients) {
        ingredients.setInputIngredients(List.of(Ingredient.of(CookietologyItems.SOFT_DOUGH.get()), Ingredient.of(CookietologyItems.COOKIE_MOLD.get())));
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(CookietologyItems.COOKIE_SHAPED_DOUGH.get()));
    }
}
