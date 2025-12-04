package xyz.uniblood.arcanumunveiled.common;

import net.mellow.nbtlib.api.JigsawPiece;
import net.mellow.nbtlib.api.JigsawPool;
import net.mellow.nbtlib.api.NBTGeneration;
import net.mellow.nbtlib.api.NBTStructure;
import net.mellow.nbtlib.api.SpawnCondition;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import xyz.uniblood.arcanumunveiled.Tags;
import xyz.uniblood.arcanumunveiled.common.lib.utils.StructureUtils;

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

public class Structures {

    public static void init() {
        // Rooms
        final NBTStructure portal_room = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/portal_room.nbt"));
        final NBTStructure boss_room = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/boss_room.nbt"));
        final NBTStructure key_room = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/key_room.nbt"));
        final NBTStructure insect_room = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/insect_room.nbt"));
        final NBTStructure library_room = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/library_room.nbt"));

        // Passages
        final NBTStructure passage_corner = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/passage_corner.nbt"));
        final NBTStructure passage_dead_end = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/passage_dead_end.nbt"));
        final NBTStructure passage_intersection = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/passage_intersection.nbt"));
        final NBTStructure passage_straight = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/passage_straight.nbt"));
        final NBTStructure passage_t = new NBTStructure(new ResourceLocation(Tags.MOD_ID, "structures/labyrinth/test/passage_t.nbt"));


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
                conformToTerrain = MoundConformToTerrain;

                blockTable = new HashMap<>() {{
                    // TODO: Aura node once I rewrite blockAiry
                    put(Blocks.cobblestone, new StructureUtils.structureMossyStone());
                    put(Blocks.stained_hardened_clay, new StructureUtils.structureUrns());
                }};
            }};

        }});

        // TODO: Have structure types for nether. Clamp em between minHeight and maxHeight
        NBTGeneration.registerStructure(0, new SpawnCondition("labyrinth") {{

            // How likely this structure is to spawn compared to others, higher = more likely
            spawnWeight = 0;

            // Height modifiers, will clamp height that the start generates at, allowing for:
            //  * Submarines that must spawn under the ocean surface
            //  * Bunkers that sit underneath the ground
            //  * Airships that must float in the sky
            maxHeight = 150;
            minHeight = 100;


            // Which of the below pools should be used to select our first piece.
            startPool = "start";

            // The pools from which structure pieces are pulled from to connect to a given jigsaw.
            pools = new HashMap<>() {{

                // Setup my passages
                final JigsawPiece passageCorner = new JigsawPiece("passage_corner", passage_corner);
                final JigsawPiece passageDeadEnd = new JigsawPiece("passage_dead_end", passage_dead_end);
                final JigsawPiece passageIntersection = new JigsawPiece("passage_intersection", passage_intersection);
                final JigsawPiece passageStraight = new JigsawPiece("passage_straight", passage_straight);
                final JigsawPiece passageT = new JigsawPiece("passage_t", passage_t);

                // Setup my rooms
                final JigsawPiece roomBoss = new JigsawPiece("boss_room", boss_room) {{
                    required = true;
                    instanceLimit = 1;
                }};
                final JigsawPiece roomKey = new JigsawPiece("key_room", key_room) {{
                    required = true;
                    instanceLimit = 1;
                }};
                final JigsawPiece roomInsect = new JigsawPiece("insect_room", insect_room);
                final JigsawPiece roomLibrary = new JigsawPiece("library_room", library_room);

//                    roomBoss.required = true;
//                    roomBoss.instanceLimit = 1;
//                    roomKey.required = true;
//                    roomKey.instanceLimit = 1;

                // Our starting pool, this one has just one structure in it, so we'll always generate from the same piece.
                put("start", new JigsawPool() {
                    {
                        add(new JigsawPiece("labyrinth_portal", portal_room), 1);
                    }
                });

                put("labyrinth_rooms", new JigsawPool() {
                    {
                        add(roomBoss, 1);
                        add(roomKey, 1);
                        add(roomInsect, 1);
                        add(roomLibrary, 1);


                        add(passageCorner, 1);
                        add(passageDeadEnd, 1);
                        add(passageIntersection, 1);
                        add(passageStraight, 1);
                        add(passageT, 1);

                        // If the structure runs out of space or has too many pieces, it'll instead grab a piece from this pool.
                        // Fallbacks do not generate any more connecting pieces.
                        fallback = "fallback";
                    }
                });

                put("labyrinth_passages", new JigsawPool() {
                    {
                        add(passageCorner, 1);
                        add(passageDeadEnd, 1);
                        add(passageIntersection, 1);
                        add(passageStraight, 1);
                        add(passageT, 1);

                        // If the structure runs out of space or has too many pieces, it'll instead grab a piece from this pool.
                        // Fallbacks do not generate any more connecting pieces.
                        fallback = "fallback";
                    }
                });

                // Our fallback pool, note that none of these names are explicit, they can be called whatever you want,
                // as long as the jigsaw blocks use them in their defined Target Pool.
                put("fallback", new JigsawPool() {
                    {
                        add(passageDeadEnd, 1);
                    }
                });
            }};

        }});

    }

}

