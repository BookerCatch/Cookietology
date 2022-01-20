package com.github.eterdelta.cookietology.compat.jei;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.compat.jei.category.BakingRecipeCategory;
import com.github.eterdelta.cookietology.compat.jei.category.ButteratorRecipeCategory;
import com.github.eterdelta.cookietology.compat.jei.category.MixingRecipeCategory;
import com.github.eterdelta.cookietology.compat.jei.category.extension.DoughCompressingCraftingExtension;
import com.github.eterdelta.cookietology.compat.jei.category.extension.DoughMoldingCraftingExtension;
import com.github.eterdelta.cookietology.compat.jei.recipe.ButteratorExampleRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.DoughCompressingRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.DoughMoldingRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyBlocks;
import com.github.eterdelta.cookietology.registry.CookietologyRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;

import java.util.Collections;

@JeiPlugin
public class CookietologyPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation(Cookietology.MODID, "main");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(CookietologyBlocks.BUTTERATOR.get()), ButteratorRecipeCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(CookietologyBlocks.MIXING_BOWL.get()), MixingRecipeCategory.ID);
        registration.addRecipeCatalyst(new ItemStack(CookietologyBlocks.BAKER.get()), BakingRecipeCategory.ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new ButteratorRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new MixingRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new BakingRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        IExtendableRecipeCategory<CraftingRecipe, ICraftingCategoryExtension> craftingCategory = registration.getCraftingCategory();
        craftingCategory.addCategoryExtension(DoughMoldingRecipe.class, DoughMoldingCraftingExtension::new);
        craftingCategory.addCategoryExtension(DoughCompressingRecipe.class, DoughCompressingCraftingExtension::new);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            registration.addRecipes(Collections.singleton(new ButteratorExampleRecipe(2250, 2250)), ButteratorRecipeCategory.ID);
            registration.addRecipes(level.getRecipeManager().getAllRecipesFor(CookietologyRecipes.MIXING), MixingRecipeCategory.ID);
            registration.addRecipes(level.getRecipeManager().getAllRecipesFor(CookietologyRecipes.BAKING), BakingRecipeCategory.ID);
        }
    }
}
