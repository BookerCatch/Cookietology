package com.github.eterdelta.cookietology.registry;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.inventory.BakerMenu;
import com.github.eterdelta.cookietology.inventory.ButteratorMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CookietologyMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Cookietology.MODID);

    public static final RegistryObject<MenuType<ButteratorMenu>> BUTTERATOR = MENUS.register("butterator", () -> new MenuType<>(ButteratorMenu::new));
    public static final RegistryObject<MenuType<BakerMenu>> BAKER = MENUS.register("baker", () -> new MenuType<>(BakerMenu::new));
}
