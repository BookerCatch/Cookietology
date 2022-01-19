package com.github.eterdelta.cookietology.compat.jei.category;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.compat.jei.recipe.ButteratorExampleRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyBlocks;
import com.github.eterdelta.cookietology.registry.CookietologyItems;
import com.github.eterdelta.cookietology.util.TimeUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.RandomUtils;

public class ButteratorRecipeCategory implements IRecipeCategory<ButteratorExampleRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(Cookietology.MODID, "butterator");
    private static final ResourceLocation TEXTURE = new ResourceLocation(Cookietology.MODID, "textures/gui/jei/butterator.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final Component name;
    private final IGuiHelper guiHelper;

    public ButteratorRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 142, 30);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(CookietologyBlocks.BUTTERATOR.get()));
        this.name = new TranslatableComponent("block.cookietology.butterator");
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends ButteratorExampleRecipe> getRecipeClass() {
        return ButteratorExampleRecipe.class;
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
    public void setIngredients(ButteratorExampleRecipe recipe, IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.ITEM, new ItemStack(CookietologyItems.SOFT_BUTTER.get(), RandomUtils.nextInt(1, 5)));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ButteratorExampleRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, false, 62, 12);
        recipeLayout.getItemStacks().set(ingredients);
        recipeLayout.getItemStacks().addTooltipCallback((slotIndex, input, ingredient, tooltip) ->
                tooltip.add(new TranslatableComponent("jei.category.butterator.butter_amount").withStyle(ChatFormatting.DARK_GRAY)));
    }

    @Override
    public void draw(ButteratorExampleRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
        this.drawButterTime(recipe, stack, 22);
        this.guiHelper.drawableBuilder(TEXTURE, 0, 30, (int) (136.0F * ((float) recipe.getInputMilk() / 9000.0F)), 4).build()
                .draw(stack, 3, 1);
    }

    protected void drawButterTime(ButteratorExampleRecipe recipe, PoseStack poseStack, int y) {
        int cookTime = recipe.getTime();
        if (cookTime > 0) {
            String timeString = TimeUtil.formatTicksToTime(cookTime);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(poseStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }
}
