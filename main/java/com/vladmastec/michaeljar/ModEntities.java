package com.vladmastec.michaeljar;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MichaelJar.MOD_ID);
    public static final RegistryObject<EntityType<MichaelEntity>> JUMPScare = ENTITY_TYPES.register("jumpscare", () -> EntityType.Builder.<MichaelEntity>of(MichaelEntity::new, MobCategory.MISC).sized(1.0f, 1.0f).build("jumpscare"));
    public static void register(IEventBus bus) { ENTITY_TYPES.register(bus); }
}