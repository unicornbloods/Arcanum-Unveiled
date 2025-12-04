package xyz.uniblood.arcanumunveiled.common.lib.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import xyz.uniblood.arcanumunveiled.common.lib.world.dimension.MazeHandler;

public class EventHandlerWorld {
    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        if (!event.world.isRemote && event.world.provider.dimensionId == 0) {
            MazeHandler.loadMaze(event.world);
        }
    }

    @SubscribeEvent
    public void worldSave(WorldEvent.Save event) {
        if (!event.world.isRemote && event.world.provider.dimensionId == 0) {
            MazeHandler.saveMaze(event.world);
        }
    }
}
