package com.vladmastec.michaeljar;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MichaelJar.MOD_ID);
    public static final RegistryObject<SoundEvent> JUMPSCARE = SOUNDS.register("jumpscare", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MichaelJar.MOD_ID, "jumpscare")));
    public static void register(IEventBus bus) { SOUNDS.register(bus); }
}