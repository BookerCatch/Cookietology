package com.github.eterdelta.cookietology.registry;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.item.cookie.CookieItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CookietologyItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cookietology.MODID);

    public static final RegistryObject<Item> SOFT_BUTTER = ITEMS.register("soft_butter", () -> new Item(new Item.Properties().tab(Cookietology.CREATIVE_TAB)));
    public static final RegistryObject<Item> COOKIE = ITEMS.register("cookie", () -> new CookieItem((new Item.Properties()).tab(Cookietology.CREATIVE_TAB).food(Foods.COOKIE)));
    public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", () -> new Item((new Item.Properties()).tab(Cookietology.CREATIVE_TAB)));
    public static final RegistryObject<Item> SOFT_DOUGH = ITEMS.register("soft_dough", () -> new Item((new Item.Properties()).tab(Cookietology.CREATIVE_TAB)));

    public static final RegistryObject<Item> BUTTERATOR = ITEMS.register("butterator", () -> new BlockItem(CookietologyBlocks.BUTTERATOR.get(), new Item.Properties().tab(Cookietology.CREATIVE_TAB)));
    public static final RegistryObject<Item> BUTTERATOR_FAN = ITEMS.register("butterator_fan", () -> new BlockItem(CookietologyBlocks.BUTTERATOR_FAN.get(), new Item.Properties().tab(Cookietology.CREATIVE_TAB)));
    public static final RegistryObject<Item> BROKEN_BUTTERATOR_FAN = ITEMS.register("broken_butterator_fan", () -> new BlockItem(CookietologyBlocks.BROKEN_BUTTERATOR_FAN.get(), new Item.Properties().tab(Cookietology.CREATIVE_TAB)));
    public static final RegistryObject<Item> MIXING_BOWL = ITEMS.register("mixing_bowl", () -> new BlockItem(CookietologyBlocks.MIXING_BOWL.get(), new Item.Properties().tab(Cookietology.CREATIVE_TAB)));
}
