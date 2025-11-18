package xyz.uniblood.arcanumunveiled;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.common.config.Config;
import xyz.uniblood.arcanumunveiled.common.lib.world.dimension.WorldProviderEldritch;

public interface Proxy {

    class Common implements Proxy {
        @Override
        public void preInit(FMLPreInitializationEvent event) {
            Share.LOG.info("I am " + Tags.MOD_NAME + " at version " + Tags.MOD_VERSION + " and root package " + Tags.ROOT_PKG);
        }

        @Override
        public void init(FMLInitializationEvent event) {
            DimensionManager.registerProviderType(Config.dimensionOuterId, WorldProviderEldritch.class, false);
            DimensionManager.registerDimension(Config.dimensionOuterId, Config.dimensionOuterId);
        }

        @Override
        public void postInit(FMLPostInitializationEvent event) {

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
