package com.github.eterdelta.cookietology.item;

import com.github.eterdelta.cookietology.Cookietology;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CookietologyCreativeTab extends CreativeModeTab {
    private static final ResourceLocation BACKGROUND_IMAGE = new ResourceLocation(Cookietology.MODID, "textures/gui/container/creative_inventory/tab_cookietology.png");

    public CookietologyCreativeTab() {
        super(Cookietology.MODID);
        this.setBackgroundImage(BACKGROUND_IMAGE);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.COOKIE);
    }
}
