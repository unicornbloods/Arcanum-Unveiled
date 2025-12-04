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

import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureBiomesConfig.HillTopStonesBiomeIds;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureBiomesConfig.MoundBiomeIds;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureBiomesConfig.StoneRingBiomeIds;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureCustomizationConfig.HillTopStonesConfig;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureCustomizationConfig.MoundConfig;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureCustomizationConfig.StoneRingConfig;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureDimensionsConfig.HillTopStonesDimensionIds;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureDimensionsConfig.MoundDimensionIds;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureDimensionsConfig.StoneRingDimensionIds;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureMiscellaneousConfig.MoundConformToTerrain;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureSpawnWeightConfig.HillTopStonesWeight;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureSpawnWeightConfig.MoundWeight;
import static xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig.StructureSpawnWeightConfig.StoneRingWeight;

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
            final NBTStructure mound = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/mound.nbt"));

            // Hilltop Stones
            NBTGeneration.registerStructure(HillTopStonesDimensionIds, new SpawnCondition("hilltop_stones") {{
                spawnWeight = HillTopStonesWeight;

                canSpawn = biome -> {

                    if (HillTopStonesConfig) {
                        for (int id : HillTopStonesBiomeIds) {
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

                    platform = new StructureUtils.EldritchStoneStone();

                    blockTable = new HashMap<>() {{
                        // TODO: Aura node once I rewrite blockAiry
                        put(Blocks.vine, new StructureUtils.HilltopStonesVines());
                        put(Blocks.stone, new StructureUtils.EldritchStoneStone());
                    }};
                }};

            }});

            // Stone Ring
            NBTGeneration.registerStructure(StoneRingDimensionIds, new SpawnCondition("stone_ring") {{
                spawnWeight = StoneRingWeight;

                canSpawn = biome -> {

                    if (StoneRingConfig) {
                        for (int id : StoneRingBiomeIds) {
                            if (biome.isEqualTo(BiomeGenBase.getBiome(id))) {
                                return true;
                            }
                        }
                    } else {
                        return biome.rootHeight >= 0.1;
                    }
                    return false;
                };

                structure = new JigsawPiece("stone_ring", stone_ring, -4) {{
                    conformToTerrain = false;

                    platform = new StructureUtils.EldritchStoneStone();

                    blockTable = new HashMap<>() {{
                        // TODO: Aura node once I rewrite blockAiry
                        // TODO: Cultists, probably use pooled version
                        put(Blocks.stone, new StructureUtils.EldritchStoneStone());
                    }};
                }};
            }});

            // Mound
            NBTGeneration.registerStructure(MoundDimensionIds, new SpawnCondition("mound") {{

                // TODO: Make mound sinister nodes a lot more rare and / or disable-able

                spawnWeight = MoundWeight;

                canSpawn = biome -> {

                    if (MoundConfig) {
                        for (int id : MoundBiomeIds) {
                            if (biome.isEqualTo(BiomeGenBase.getBiome(id))) {
                                return true;
                            }
                        }
                    } else {
                        // I never actually tested min below 0.0.
                        final float minHeightVatiation = 0.0F;
                        // Setting this to 0.3 allows the structure to spawn in forests and similar biomes as well.
                        final float maxHeightVatiation = 0.2F;

                        // This targets flatter biomes by default to avoid shredding on hills.
                        return biome.heightVariation > minHeightVatiation && biome.heightVariation < maxHeightVatiation;
                    }

                    return false;
                };

                structure = new JigsawPiece("mound", mound, -9) {{
                    conformToTerrain = moundConformToTerrain;

                    blockTable = new HashMap<>() {{
                        // TODO: Aura node once I rewrite blockAiry
                        put(Blocks.cobblestone, new StructureUtils.structureMossyStone());
                        put(Blocks.stained_hardened_clay, new StructureUtils.structureUrns());
                    }};
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
