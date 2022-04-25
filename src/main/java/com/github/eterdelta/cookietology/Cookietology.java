package com.github.eterdelta.cookietology;

import com.github.eterdelta.cookietology.client.gui.screens.inventory.BakerScreen;
import com.github.eterdelta.cookietology.client.gui.screens.inventory.ButteratorScreen;
import com.github.eterdelta.cookietology.client.renderer.blockentity.MixingBowlRenderer;
import com.github.eterdelta.cookietology.item.CookietologyCreativeTab;
import com.github.eterdelta.cookietology.item.crafting.MixingRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.CookieBakingRecipe;
import com.github.eterdelta.cookietology.registry.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.http.cookie.Cookie;

@Mod(Cookietology.MODID)
@Mod.EventBusSubscriber(modid = Cookietology.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Cookietology {
    public static final String MODID = "cookietology";
    public static final CookietologyCreativeTab CREATIVE_TAB = new CookietologyCreativeTab();

    public Cookietology() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(this::onClientSetup);
        eventBus.addGenericListener(RecipeSerializer.class, this::registerRecipeSerializers);

        CookietologyBlockEntities.BLOCK_ENTITIES.register(eventBus);
        CookietologyBlocks.BLOCKS.register(eventBus);
        CookietologyItems.ITEMS.register(eventBus);
        CookietologyMenus.MENUS.register(eventBus);
        CookietologyRecipes.RECIPE_SERIALIZERS.register(eventBus);
        CookietologySounds.SOUNDS.register(eventBus);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(CookietologyMenus.BUTTERATOR.get(), ButteratorScreen::new);
            MenuScreens.register(CookietologyMenus.BAKER.get(), BakerScreen::new);
            BlockEntityRenderers.register(CookietologyBlockEntities.MIXING_BOWL.get(), MixingBowlRenderer::new);
        });
    }

    private void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
		event.getRegistry().register(MixingRecipe.SERIALIZER);
        event.getRegistry().register(CookieBakingRecipe.SERIALIZER);
	}
}
