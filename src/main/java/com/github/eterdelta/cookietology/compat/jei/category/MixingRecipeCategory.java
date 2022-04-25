package com.github.eterdelta.cookietology.compat.jei.category;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.compat.jei.category.extension.DoughBrillianceMixingExtension;
import com.github.eterdelta.cookietology.compat.jei.category.extension.SimpleCategoryExtension;
import com.github.eterdelta.cookietology.item.crafting.IMixingRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.DoughBrillianceRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyBlocks;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.api.recipe.category.extensions.IRecipeCategoryExtension;
import mezz.jei.recipes.ExtendableRecipeCategoryHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class MixingRecipeCategory implements IExtendableRecipeCategory<IMixingRecipe, IRecipeCategoryExtension> {
    public static final ResourceLocation ID = new ResourceLocation(Cookietology.MODID, "mixing");
    private static final ResourceLocation TEXTURE = new ResourceLocation(Cookietology.MODID, "textures/gui/jei/mixer.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final Component name;
    private final ExtendableRecipeCategoryHelper<Recipe<?>, IRecipeCategoryExtension> extendableHelper = new ExtendableRecipeCategoryHelper<>(IMixingRecipe.class);

    public MixingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 118, 36);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(CookietologyBlocks.MIXING_BOWL.get()));
        this.name = new TranslatableComponent("block.cookietology.mixing_bowl");

        this.addCategoryExtension(IMixingRecipe.class, recipe -> !recipe.isSpecial(), SimpleCategoryExtension::new);
        this.addCategoryExtension(DoughBrillianceRecipe.class, DoughBrillianceMixingExtension::new);
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends IMixingRecipe> getRecipeClass() {
        return IMixingRecipe.class;
    }

    @Override
    public Component getTitle() {
        return this.name;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(IMixingRecipe recipe, IIngredients ingredients) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        extension.setIngredients(ingredients);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IMixingRecipe recipe, IIngredients ingredients) {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                recipeLayout.getItemStacks().init(col + row * 3, true, col * 18, row * 18);
            }
        }
        recipeLayout.getItemStacks().init(6, false, 96, 10);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(IMixingRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        int recipeWidth = this.background.getWidth();
        int recipeHeight = this.background.getHeight();
        extension.drawInfo(recipeWidth, recipeHeight, poseStack, mouseX, mouseY);
    }

    @Override
    public List<Component> getTooltipStrings(IMixingRecipe recipe, double mouseX, double mouseY) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        return extension.getTooltipStrings(mouseX, mouseY);
    }

    @Override
    public boolean handleInput(IMixingRecipe recipe, double mouseX, double mouseY, InputConstants.Key input) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        return extension.handleInput(mouseX, mouseY, input);
    }

    @Override
    public boolean isHandled(IMixingRecipe recipe) {
        Optional<IRecipeCategoryExtension> extension = this.extendableHelper.getOptionalRecipeExtension(recipe);
        return extension != null;
    }

    @Override
    public <R extends IMixingRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Function<R, ? extends IRecipeCategoryExtension> extensionFactory) {
        extendableHelper.addRecipeExtensionFactory(recipeClass, null, extensionFactory);
    }

    @Override
    public <R extends IMixingRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Predicate<R> extensionFilter, Function<R, ? extends IRecipeCategoryExtension> extensionFactory) {
        extendableHelper.addRecipeExtensionFactory(recipeClass, extensionFilter, extensionFactory);
    }
}
