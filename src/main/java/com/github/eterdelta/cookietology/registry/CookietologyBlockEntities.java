package com.github.eterdelta.cookietology.registry;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.block.entity.ButteratorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CookietologyBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Cookietology.MODID);

    public static final RegistryObject<BlockEntityType<ButteratorBlockEntity>> BUTTERATOR = BLOCK_ENTITIES.register("butterator", () -> BlockEntityType.Builder.of(ButteratorBlockEntity::new, CookietologyBlocks.BUTTERATOR.get()).build(null));
}
