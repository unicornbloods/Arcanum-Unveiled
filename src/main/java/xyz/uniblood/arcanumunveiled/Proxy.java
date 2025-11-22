package xyz.uniblood.arcanumunveiled;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.mellow.nbtlib.api.JigsawPiece;
import net.mellow.nbtlib.api.NBTGeneration;
import net.mellow.nbtlib.api.NBTStructure;
import net.mellow.nbtlib.api.SpawnCondition;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.common.config.Config;
import xyz.uniblood.arcanumunveiled.common.lib.events.EventHandlerWorld;
import xyz.uniblood.arcanumunveiled.common.lib.utils.StructureUtils;
import xyz.uniblood.arcanumunveiled.common.lib.world.ArcanumUnveiledWorldGenerator;
import xyz.uniblood.arcanumunveiled.common.lib.world.dimension.WorldProviderEldritch;
import xyz.uniblood.arcanumunveiled.compat.EtFuturum;

import java.util.HashMap;

import static xyz.uniblood.arcanumunveiled.common.config.WorldGenerationConfig.hillTopStonesBiomeIds;
import static xyz.uniblood.arcanumunveiled.common.config.WorldGenerationConfig.hillTopStonesConfig;
import static xyz.uniblood.arcanumunveiled.common.config.WorldGenerationConfig.hillTopStonesWeight;

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
            final NBTStructure stone_ring = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/stone_ring.nbt"));
            final NBTStructure hilltop_stones = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/hilltop_stones.nbt"));

            NBTGeneration.registerStructure(0, new SpawnCondition("hilltop_stones") {{
                spawnWeight = hillTopStonesWeight;

                canSpawn = biome -> {

                    if (hillTopStonesConfig) {
                        for (int id : hillTopStonesBiomeIds) {
                            if (biome.isEqualTo(BiomeGenBase.getBiome(id))) {
                                return true;
                            }
                        }
                    } else {
                        return biome.rootHeight >= 1.0;
                    }

                    return false;
                };

                structure = new JigsawPiece("hilltop_stones", hilltop_stones, 0) {{
                    conformToTerrain = false;

                    blockTable = new HashMap<>() {{
                        put(Blocks.vine, new StructureUtils.HilltopStonesVines());
                        put(Blocks.stone, new StructureUtils.EldritchStoneStone());
                    }};
                }};

            }});


            NBTGeneration.registerStructure(0, new SpawnCondition("stone_ring") {{
                spawnWeight = 60;

                structure = new JigsawPiece("stone_ring", stone_ring, -4) {{
                    conformToTerrain = false;
                }};
            }});

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
