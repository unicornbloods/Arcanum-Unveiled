package xyz.uniblood.arcanumunveiled;

import cpw.mods.fml.common.event.*;

public interface Proxy {

    class Common implements Proxy {
        @Override
        public void preInit(FMLPreInitializationEvent event) {
            Share.LOG.info("I am " + Tags.MOD_NAME + " at version " + Tags.MOD_VERSION + " and root package " + Tags.ROOT_PKG);
        }

        @Override
        public void init(FMLInitializationEvent event) {

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
