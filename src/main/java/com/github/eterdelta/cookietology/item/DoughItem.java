package com.github.eterdelta.cookietology.item;

import com.github.eterdelta.cookietology.util.CookieHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class DoughItem extends Item {
    public static final DoughProperties DEFAULT_PROPERTIES = new DoughProperties(10, 0);

    public DoughItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> components, TooltipFlag tooltipFlag) {
        DoughProperties doughProperties = CookieHelper.getDoughProperties(itemStack);
        components.add(new TranslatableComponent("dough.thickness", doughProperties.thickness()).withStyle(ChatFormatting.GRAY));
        components.add(new TranslatableComponent("dough.brilliance", doughProperties.brilliance()).withStyle(ChatFormatting.GRAY));
    }

    public record DoughProperties(int thickness, int brilliance) { }
}
