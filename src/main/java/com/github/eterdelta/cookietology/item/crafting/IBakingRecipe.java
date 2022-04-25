package com.github.eterdelta.cookietology.item.crafting;

import com.github.eterdelta.cookietology.item.crafting.special.CookieBakingRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface IBakingRecipe extends Recipe<Container> {

    @Override
    default RecipeType<?> getType() {
        return CookieBakingRecipe.TYPE;
    }

    int getCookTime();
}
