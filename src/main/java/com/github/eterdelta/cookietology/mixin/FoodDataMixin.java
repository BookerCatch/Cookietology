package com.github.eterdelta.cookietology.mixin;

import com.github.eterdelta.cookietology.item.cookie.CookieItem;
import com.github.eterdelta.cookietology.registry.CookietologyItems;
import com.github.eterdelta.cookietology.util.CookieHelper;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyVariable(at = @At("STORE"), method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;)V")
    private FoodProperties handleFoodProperties(FoodProperties foodProperties, Item item, ItemStack itemStack) {
        if (item == CookietologyItems.COOKIE.get()) {
            CookieItem.CookieProperties cookieProperties = CookieHelper.getCookieProperties(itemStack);
            return new FoodProperties.Builder()
                    .nutrition(cookieProperties.nutrition())
                    .saturationMod(cookieProperties.saturation())
                    .build();
        } else {
            return foodProperties;
        }
    }
}