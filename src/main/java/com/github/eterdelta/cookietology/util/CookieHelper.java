package com.github.eterdelta.cookietology.util;

import com.github.eterdelta.cookietology.item.cookie.CookieItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class CookieHelper {

    public static CookieItem.CookieProperties getCookieProperties(ItemStack itemStack) {
        CompoundTag stackTag = itemStack.getTag();

        if (stackTag != null && stackTag.contains("CookieProperties")) {
            CompoundTag propertiesTag = stackTag.getCompound("CookieProperties");

            return new CookieItem.CookieProperties(
                    propertiesTag.getInt("EatSpeed"),
                    propertiesTag.getFloat("SaturationModifier"),
                    propertiesTag.getInt("Nutrition"));
        } else {
            return CookieItem.DEFAULT_PROPERTIES;
        }
    }

    public static void saveCookieProperties(ItemStack itemStack, int eatSpeed, float saturation, int nutrition) {
        CompoundTag stackTag = itemStack.getOrCreateTag();
        CompoundTag propertiesTag = new CompoundTag();

        propertiesTag.putInt("EatSpeed", eatSpeed);
        propertiesTag.putFloat("SaturationModifier", saturation);
        propertiesTag.putInt("Nutrition", nutrition);

        stackTag.put("CookieProperties", propertiesTag);
    }
}