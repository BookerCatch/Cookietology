package com.github.eterdelta.cookietology;

import com.github.eterdelta.cookietology.client.gui.screens.inventory.ButteratorScreen;
import com.github.eterdelta.cookietology.item.CookietologyCreativeTab;
import com.github.eterdelta.cookietology.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(Cookietology.MODID)
public class Cookietology {
    public static final String MODID = "cookietology";
    public static final CookietologyCreativeTab CREATIVE_TAB = new CookietologyCreativeTab();

    public Cookietology() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CookietologyBlockEntities.BLOCK_ENTITIES.register(eventBus);
        CookietologyBlocks.BLOCKS.register(eventBus);
        CookietologyItems.ITEMS.register(eventBus);
        CookietologyMenus.MENUS.register(eventBus);
        CookietologySounds.SOUNDS.register(eventBus);

        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(this::onClientSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(CookietologyMenus.BUTTERATOR.get(), ButteratorScreen::new);
            Minecraft.getInstance().getItemColors().register((itemStack, colorIndex) -> {
                if (colorIndex <= 0) {
                    List<MobEffectInstance> cookieEffects = PotionUtils.getCustomEffects(itemStack);
                    if (!cookieEffects.isEmpty()) {
                        return PotionUtils.getColor(PotionUtils.getCustomEffects(itemStack));
                    } else {
                        return 0xFFA070;
                    }
                } else {
                    return -1;
                }
            }, CookietologyItems.COOKIE.get());
        });
    }
}
