package xyz.uniblood.arcanumunveiled;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.MOD_VERSION,
        acceptedMinecraftVersions = "[1.7.10]",
        dependencies = "after:Baubles|Expanded; after:nbtlib; after:Thaumcraft;")
public class ArcanumUnveiled implements Proxy {
    @SidedProxy(clientSide = Tags.ROOT_PKG + ".Proxy$Client", serverSide = Tags.ROOT_PKG + ".Proxy$Server")
    public static Proxy proxy;

    @Override
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Override
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Override
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}