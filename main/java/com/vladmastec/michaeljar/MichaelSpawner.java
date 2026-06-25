package com.vladmastec.michaeljar;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = MichaelJar.MOD_ID)
public class MichaelSpawner {
    private static final Random RANDOM = new Random();
    private static int nextSpawnTick = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.level().isClientSide() || event.phase == TickEvent.Phase.START) return;
        if (event.player.tickCount < nextSpawnTick) return;

        int minDelay = 3 * 1200;
        int maxDelay = 10 * 1200;
        int delay = minDelay + RANDOM.nextInt(maxDelay - minDelay + 1);
        nextSpawnTick = event.player.tickCount + delay;

        MichaelEntity entity = new MichaelEntity(ModEntities.JUMPScare.get(), event.player.level());
        double angle = RANDOM.nextDouble() * Math.PI * 2;
        double distance = 20 + RANDOM.nextDouble() * 10;
        entity.setPos(
                event.player.getX() + Math.cos(angle) * distance,
                event.player.getY() + 1.0,
                event.player.getZ() + Math.sin(angle) * distance
        );
        event.player.level().addFreshEntity(entity);
    }
}