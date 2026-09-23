package xyz.uniblood.arcanumunveiled.common.lib.events;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import xyz.uniblood.arcanumunveiled.ArcanumUnveiled;

/**
 * Handles world/level events.
 */
@EventBusSubscriber(modid = ArcanumUnveiled.MODID)
public class EventHandlerWorld {

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            if (serverLevel.getGameTime() % 1200 == 0) { // Every minute
                for (var player : serverLevel.players()) {
                    WarpEvents.checkWarpEvent(player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
    }

    @SubscribeEvent
    public static void onLevelSave(LevelEvent.Save event) {
    }
}
