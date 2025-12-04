package xyz.uniblood.arcanumunveiled;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.common.config.Config;
import xyz.uniblood.arcanumunveiled.common.Structures;
import xyz.uniblood.arcanumunveiled.common.lib.events.EventHandlerWorld;
import xyz.uniblood.arcanumunveiled.common.lib.world.ArcanumUnveiledWorldGenerator;
import xyz.uniblood.arcanumunveiled.common.lib.world.dimension.WorldProviderEldritch;
import xyz.uniblood.arcanumunveiled.compat.EtFuturum;

public interface Proxy {

    class Common implements Proxy {
        @Override
        public void preInit(FMLPreInitializationEvent event) {
            Share.LOG.info("I am " + Tags.MOD_NAME + " at version " + Tags.MOD_VERSION + " and root package " + Tags.ROOT_PKG);
            EtFuturum.IS_LOADED = Loader.isModLoaded("etfuturum");
        }

        @Override
        public void init(FMLInitializationEvent event) {
            DimensionManager.registerProviderType(Config.dimensionOuterId, WorldProviderEldritch.class, false);
            DimensionManager.registerDimension(Config.dimensionOuterId, Config.dimensionOuterId);

            Structures.init();

            EventHandlerWorld worldEventHandler = new EventHandlerWorld();

            MinecraftForge.EVENT_BUS.register(worldEventHandler);
        }


        // Interact with blocks from other mods
        // It's good practice to do stuff like recipe or mod compat from postInit
        @Override
        public void postInit(FMLPostInitializationEvent event) {

            if (EtFuturum.IS_LOADED) {
                EtFuturum.CRYING_OBSIDIAN = Block.getBlockFromName("etfuturum:crying_obsidian") == null ? Blocks.obsidian : Block.getBlockFromName("etfuturum:crying_obsidian");
            }
        }
    }

    @SuppressWarnings("unused")
    class Client extends Common {
    }

    @SuppressWarnings("unused")
    class Server extends Common {
    }

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);
}
