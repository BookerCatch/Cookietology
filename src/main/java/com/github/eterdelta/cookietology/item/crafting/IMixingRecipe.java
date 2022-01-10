package com.github.eterdelta.cookietology.item.crafting;

import com.github.eterdelta.cookietology.registry.CookietologyRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface IMixingRecipe extends Recipe<Container> {

    @Override
    default RecipeType<?> getType() {
        return CookietologyRecipes.MIXING;
    }

    int getAttempts();
}
