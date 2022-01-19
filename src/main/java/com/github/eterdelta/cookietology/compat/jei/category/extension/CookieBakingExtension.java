package com.github.eterdelta.cookietology.compat.jei.category.extension;

import com.github.eterdelta.cookietology.item.crafting.special.CookieBakingRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class CookieBakingExtension extends SimpleCategoryExtension<CookieBakingRecipe> {
    public CookieBakingExtension(CookieBakingRecipe recipe) {
        super(recipe);
    }

    @Override
    public void setIngredients(IIngredients ingredients) {
        ingredients.setInputIngredients(List.of(Ingredient.of(CookietologyItems.COOKIE_SHAPED_DOUGH.get()), Ingredient.of(Items.COCOA_BEANS)));
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(CookietologyItems.COOKIE.get()));
    }
}
