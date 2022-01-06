package com.github.eterdelta.cookietology.client.gui.screens.inventory;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.inventory.ButteratorMenu;
import com.github.eterdelta.cookietology.util.TimeUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ButteratorScreen extends AbstractContainerScreen<ButteratorMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Cookietology.MODID, "textures/gui/container/butterator.png");
    private static final Component MISSING_FAN_COMPONENT = new TranslatableComponent("machineInfo.missing_fan").withStyle(ChatFormatting.RED);
    private static final Component COOLDOWN_COMPONENT = new TranslatableComponent("machineInfo.cooldown").withStyle(ChatFormatting.YELLOW);
    private static final Component EMPTY_COMPONENT = new TextComponent("--:--:--");

    public ButteratorScreen(ButteratorMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = 28;
        this.titleLabelY = 4;
    }

    @Override
    public void render(PoseStack poseStack, int p_97796_, int p_97797_, float p_97798_) {
        this.renderBackground(poseStack);
        super.render(poseStack, p_97796_, p_97797_, p_97798_);
        this.renderTooltip(poseStack, p_97796_, p_97797_);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int p_97809_, int p_97810_) {
        this.font.draw(poseStack, this.title.copy().withStyle(ChatFormatting.WHITE), (float) this.titleLabelX, (float) this.titleLabelY, 4210752);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        int milkWidth = (int) (136.0F * ((float) this.menu.getMilkTime() / 9000.0F));
        if (milkWidth > 0) {
            this.blit(poseStack, this.leftPos + 20, this.topPos + 20, 0, 166, milkWidth, 4);
        }
        if (!this.menu.hasCooldown()) {
            if (this.menu.hasWorkingFan()) {
                int progressHeight = (int) (40.0F * ((float) this.menu.getResultProgress() / 3000.0F));
                if (progressHeight > 0) {
                    this.blit(poseStack, this.leftPos + 139, this.topPos + 25, 176, 0, 8, progressHeight);
                    this.drawScreenInfo(poseStack, new TextComponent(TimeUtil.formatTicksToTime(3000 - this.menu.getResultProgress())));
                } else {
                    this.drawScreenInfo(poseStack, EMPTY_COMPONENT);
                }
            } else {
                this.drawScreenInfo(poseStack, MISSING_FAN_COMPONENT);
            }
        } else {
            this.drawScreenInfo(poseStack, COOLDOWN_COMPONENT);
        }
    }

    public void drawScreenInfo(PoseStack poseStack, Component component) {
        this.font.draw(poseStack, component, this.leftPos + ((this.imageWidth - this.font.width(component)) / 2.0F), this.topPos + 33, 4210752);
    }
}
