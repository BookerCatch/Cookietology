package com.github.eterdelta.cookietology.compat.jei.category.extension;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.IRecipeCategoryExtension;
import net.minecraft.world.item.crafting.Recipe;

public class SimpleCategoryExtension<T extends Recipe<?>> implements IRecipeCategoryExtension {
    protected final T recipe;

    public SimpleCategoryExtension(T recipe) {
        this.recipe = recipe;
    }

    @Override
    public void setIngredients(IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }
}
