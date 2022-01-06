package com.github.eterdelta.cookietology.item.cookie;

import com.github.eterdelta.cookietology.registry.CookietologyItems;
import com.github.eterdelta.cookietology.util.CookieHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

import java.util.List;

public class CookieItem extends Item {
    public static final CookieProperties DEFAULT_PROPERTIES = new CookieProperties(32, 0.1F, 2);
    private static final Component EFFECTS_COMPONENT = (new TranslatableComponent("cookie.effects")).withStyle(ChatFormatting.GRAY);

    public CookieItem(Properties properties) {
        super(properties);
    }

    public static ItemStack create(int count, int eatSpeed, float saturation, int nutrition, List<MobEffectInstance> effects) {
        ItemStack cookieStack = new ItemStack(CookietologyItems.COOKIE.get(), count);
        CookieHelper.saveCookieProperties(cookieStack, eatSpeed, saturation, nutrition);
        PotionUtils.setCustomEffects(cookieStack, effects);
        return cookieStack;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return CookieHelper.getCookieProperties(itemStack).eatSpeed();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> components, TooltipFlag tooltipFlag) {
        CookieProperties cookieProperties = CookieHelper.getCookieProperties(itemStack);
        components.add(new TranslatableComponent("cookie.eat_speed", String.valueOf(cookieProperties.eatSpeed())).withStyle(Style.EMPTY.withColor(0xFF6100)));
        components.add(new TranslatableComponent("cookie.saturation", String.valueOf(cookieProperties.saturation())).withStyle(ChatFormatting.GOLD));
        components.add(new TranslatableComponent("cookie.nutrition", String.valueOf(cookieProperties.nutrition())).withStyle(ChatFormatting.YELLOW));
        components.add(TextComponent.EMPTY);
        components.add(EFFECTS_COMPONENT);
        PotionUtils.addPotionTooltip(itemStack, components, 1.0F);
    }

    public record CookieProperties(int eatSpeed, float saturation, int nutrition) { }
}