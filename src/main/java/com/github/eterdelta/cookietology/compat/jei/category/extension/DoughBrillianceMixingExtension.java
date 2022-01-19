package com.github.eterdelta.cookietology.compat.jei.category.extension;

import com.github.eterdelta.cookietology.item.crafting.special.DoughBrillianceRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyItems;
import com.github.eterdelta.cookietology.util.CookieHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class DoughBrillianceMixingExtension extends SimpleCategoryExtension<DoughBrillianceRecipe> {
    public DoughBrillianceMixingExtension(DoughBrillianceRecipe recipe) {
        super(recipe);
    }

    @Override
    public void setIngredients(IIngredients ingredients) {
        ingredients.setInputIngredients(List.of(Ingredient.of(CookietologyItems.SOFT_DOUGH.get()), Ingredient.of(CookietologyItems.SOFT_BUTTER.get())));

        ItemStack upgradedDough = new ItemStack(CookietologyItems.SOFT_DOUGH.get());
        CookieHelper.saveDoughProperties(upgradedDough, 5, 2);
        ingredients.setOutput(VanillaTypes.ITEM, upgradedDough);
    }
}
