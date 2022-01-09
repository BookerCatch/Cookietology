package com.github.eterdelta.cookietology.registry;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.block.ButteratorBlock;
import com.github.eterdelta.cookietology.block.ButteratorFanBlock;
import com.github.eterdelta.cookietology.block.MixingBowlBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CookietologyBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cookietology.MODID);

    public static final RegistryObject<Block> BUTTERATOR = BLOCKS.register("butterator", () -> new ButteratorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.5F)));
    public static final RegistryObject<Block> BUTTERATOR_FAN = BLOCKS.register("butterator_fan", () -> new ButteratorFanBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.5F)));
    public static final RegistryObject<Block> BROKEN_BUTTERATOR_FAN = BLOCKS.register("broken_butterator_fan", () -> new ButteratorFanBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(SoundType.METAL).requiresCorrectToolForDrops().strength(1.0F)));
    public static final RegistryObject<Block> MIXING_BOWL = BLOCKS.register("mixing_bowl", () -> new MixingBowlBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(SoundType.METAL).requiresCorrectToolForDrops().strength(1.0F)));
}
