package com.github.eterdelta.cookietology.compat.jei.category.extension;

import com.github.eterdelta.cookietology.item.crafting.special.DoughCompressingRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyItems;
import com.github.eterdelta.cookietology.util.CookieHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.plugins.vanilla.crafting.CraftingCategoryExtension;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class DoughCompressingCraftingExtension extends CraftingCategoryExtension<DoughCompressingRecipe> {
    public DoughCompressingCraftingExtension(DoughCompressingRecipe recipe) {
        super(recipe);
    }

    @Override
    public void setIngredients(IIngredients ingredients) {
        ingredients.setInputIngredients(List.of(Ingredient.of(CookietologyItems.SOFT_DOUGH.get()), Ingredient.of(CookietologyItems.SOFT_DOUGH.get())));

        ItemStack upgradedDough = new ItemStack(CookietologyItems.SOFT_DOUGH.get());
        CookieHelper.saveDoughProperties(upgradedDough, 10, 1);
        ingredients.setOutput(VanillaTypes.ITEM, upgradedDough);
    }
}
