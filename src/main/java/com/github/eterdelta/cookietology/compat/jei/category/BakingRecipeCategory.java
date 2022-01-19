package com.github.eterdelta.cookietology.compat.jei.category;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.compat.jei.category.extension.CookieBakingExtension;
import com.github.eterdelta.cookietology.compat.jei.category.extension.SimpleCategoryExtension;
import com.github.eterdelta.cookietology.item.crafting.IBakingRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.CookieBakingRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyBlocks;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.api.recipe.category.extensions.IRecipeCategoryExtension;
import mezz.jei.recipes.ExtendableRecipeCategoryHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class BakingRecipeCategory implements IExtendableRecipeCategory<IBakingRecipe, IRecipeCategoryExtension> {
    public static final ResourceLocation ID = new ResourceLocation(Cookietology.MODID, "baking");
    private static final ResourceLocation TEXTURE = new ResourceLocation(Cookietology.MODID, "textures/gui/jei/baker.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final Component name;
    private final IDrawableAnimated animatedFlame;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;
    private final ExtendableRecipeCategoryHelper<Recipe<?>, IRecipeCategoryExtension> extendableHelper = new ExtendableRecipeCategoryHelper<>(IBakingRecipe.class);

    public BakingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 91, 54);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(CookietologyBlocks.BAKER.get()));
        this.name = new TranslatableComponent("block.cookietology.baker");
        IDrawableStatic staticFlame = guiHelper.createDrawable(TEXTURE, 91, 0, 14, 14);
        this.animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        this.cachedArrows = CacheBuilder.newBuilder()
                .maximumSize(25)
                .build(new CacheLoader<>() {
                    @Override
                    public IDrawableAnimated load(Integer cookTime) {
                        return guiHelper.drawableBuilder(TEXTURE, 91, 14, 24, 17)
                                .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
                    }
                });

        this.addCategoryExtension(IBakingRecipe.class, recipe -> !recipe.isSpecial(), SimpleCategoryExtension::new);
        this.addCategoryExtension(CookieBakingRecipe.class, CookieBakingExtension::new);
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends IBakingRecipe> getRecipeClass() {
        return IBakingRecipe.class;
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
    public void setIngredients(IBakingRecipe recipe, IIngredients ingredients) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        extension.setIngredients(ingredients);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IBakingRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 0, 0);
        recipeLayout.getItemStacks().init(1, true, 18, 0);
        recipeLayout.getItemStacks().init(3, false, 69, 18);

        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(IBakingRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        this.animatedFlame.draw(poseStack, 10, 20);

        IDrawableAnimated arrow = getArrow(recipe);
        arrow.draw(poseStack, 33, 18);

        this.drawCookTime(recipe, poseStack, 45);

        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        int recipeWidth = this.background.getWidth();
        int recipeHeight = this.background.getHeight();
        extension.drawInfo(recipeWidth, recipeHeight, poseStack, mouseX, mouseY);
    }

    @Override
    public List<Component> getTooltipStrings(IBakingRecipe recipe, double mouseX, double mouseY) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        return extension.getTooltipStrings(mouseX, mouseY);
    }

    @Override
    public boolean handleInput(IBakingRecipe recipe, double mouseX, double mouseY, InputConstants.Key input) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtension(recipe);
        return extension.handleInput(mouseX, mouseY, input);
    }

    @Override
    public boolean isHandled(IBakingRecipe recipe) {
        IRecipeCategoryExtension extension = this.extendableHelper.getRecipeExtensionOrNull(recipe);
        return extension != null;
    }

    @Override
    public <R extends IBakingRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Function<R, ? extends IRecipeCategoryExtension> extensionFactory) {
        extendableHelper.addRecipeExtensionFactory(recipeClass, null, extensionFactory);
    }

    @Override
    public <R extends IBakingRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Predicate<R> extensionFilter, Function<R, ? extends IRecipeCategoryExtension> extensionFactory) {
        extendableHelper.addRecipeExtensionFactory(recipeClass, extensionFilter, extensionFactory);
    }

    protected IDrawableAnimated getArrow(IBakingRecipe recipe) {
        int cookTime = recipe.getCookTime();
        if (cookTime <= 0) {
            cookTime = 200;
        }
        return this.cachedArrows.getUnchecked(cookTime);
    }

    protected void drawCookTime(IBakingRecipe recipe, PoseStack poseStack, int y) {
        int cookTime = recipe.getCookTime();
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            TranslatableComponent timeString = new TranslatableComponent("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(poseStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }
}
