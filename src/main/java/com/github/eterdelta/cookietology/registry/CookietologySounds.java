package com.github.eterdelta.cookietology.registry;

import com.github.eterdelta.cookietology.Cookietology;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CookietologySounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Cookietology.MODID);

    public static final RegistryObject<SoundEvent> FAN_REPAIR = SOUNDS.register("block.butterator_fan.repair", () -> new SoundEvent(new ResourceLocation(Cookietology.MODID, "block.butterator_fan.repair")));
}
