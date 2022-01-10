package com.github.eterdelta.cookietology.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MoldItem extends Item {
    private final Item moldResult;

    public MoldItem(Properties properties, Item moldResult) {
        super(properties);
        this.moldResult = moldResult;
    }

    public Item getMoldResult() {
        return moldResult;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(this);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
