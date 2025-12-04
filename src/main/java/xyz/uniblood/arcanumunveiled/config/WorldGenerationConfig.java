package xyz.uniblood.arcanumunveiled.config;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import xyz.uniblood.arcanumunveiled.Tags;


public final class WorldGenerationConfig {

    private final static String worldGenerationConfigLangKeyBase = "config.arcanumunveiled.config.worldgeneration.";
    private final static String worldGenerationConfigFile = "ArcanumUnveiled/worldGeneration";
    // Used to sort the config entries in the mod menu gui.
    private final static String worldGenerationConfigNumericKeyBase = "01";


    public static void init() {
        StructureCustomizationConfig.init();
        StructureSpawnWeightConfig.init();
        StructureBiomesConfig.init();
        StructureDimensionsConfig.init();
        StructureMiscellaneousConfig.init();
        OreGenerationConfig.init();
    }


    @Config.Comment("Use this section to enable / disable the biome spawn conditions config per structure")
    @Config(modid = Tags.MOD_ID,
            category = worldGenerationConfigNumericKeyBase + "_structure_customization",
            customPath = worldGenerationConfigFile
    )
    @Config.LangKey(worldGenerationConfigLangKeyBase + "CategoryStructureCustomization")
    public final class StructureCustomizationConfig {
        private StructureCustomizationConfig() {
        }

        static {
            ConfigurationManager.selfInit();
        }

        public static void init() {
        }

        @Config.Name("Hill Top Stones Biome Configuration")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "HillTopStonesConfig")
        @Config.RequiresWorldRestart
        @Config.DefaultBoolean(false)
        public static boolean HillTopStonesConfig;

        @Config.Name("Stone Ring Biome Configuration")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "StoneRingConfig")
        @Config.RequiresWorldRestart
        @Config.DefaultBoolean(false)
        public static boolean StoneRingConfig;

        @Config.Name("Mound Biome Configuration")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "MoundConfig")
        @Config.RequiresWorldRestart
        @Config.DefaultBoolean(false)
        public static boolean MoundConfig;

    }

    @Config.Comment("A value of 0 is off. A value of 32 is 32 times more common than a structure of value 1. Be sure to check the nbtlib.cfg for more.")
    @Config(modid = Tags.MOD_ID,
            category = worldGenerationConfigNumericKeyBase + "_structure_spawn_weights",
            customPath = worldGenerationConfigFile
    )
    @Config.LangKey(worldGenerationConfigLangKeyBase + "CategoryStructureWeights")
    public final class StructureSpawnWeightConfig {
        private StructureSpawnWeightConfig() {
        }

        static {
            ConfigurationManager.selfInit();
        }

        public static void init() {
        }

        @Config.Name("Hill Top Stones Spawn Weight")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "HillTopStonesWeight")
        @Config.RequiresWorldRestart
        @Config.DefaultInt(1)
        @Config.RangeInt(min = 0, max = 32)
        public static int HillTopStonesWeight;

        @Config.Name("Stone Ring Biome Spawn Weight")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "StoneRingWeight")
        @Config.RequiresWorldRestart
        @Config.DefaultInt(0) // TODO: Temporarily disable
        @Config.RangeInt(min = 0, max = 32)
        public static int StoneRingWeight;

        @Config.Name("Mound Biome Spawn Weight")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "MoundWeight")
        @Config.RequiresWorldRestart
        @Config.DefaultInt(1)
        @Config.RangeInt(min = 0, max = 32)
        public static int MoundWeight;

    }

    @Config.Comment("This is just a numerical list of biome ids that is checked against for structure spawning.")
    @Config(modid = Tags.MOD_ID,
            category = worldGenerationConfigNumericKeyBase + "_structure_biomes",
            customPath = worldGenerationConfigFile
    )
    @Config.LangKey(worldGenerationConfigLangKeyBase + "CategoryStructureBiomes")
    public final class StructureBiomesConfig {
        private StructureBiomesConfig() {
        }

        static {
            ConfigurationManager.selfInit();
        }

        public static void init() {
        }

        @Config.Name("Hill Top Stones Biome Id Whitelist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "HillTopStonesBiomeIds")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({3, 17, 18, 19, 20, 22, 28, 31, 33, 34, 36, 38, 39})
        public static int[] HillTopStonesBiomeIds;

        @Config.Name("Stone Rings Biome Id Whitelist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "StoneRingBiomeIds")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({})
        public static int[] StoneRingBiomeIds;

        @Config.Name("Mound Biome Id Whitelist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "MoundBiomeIds")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({})
        public static int[] MoundBiomeIds;

    }

    @Config.Comment("This is just a numerical list of dimension ids that is checked against for structure spawning. If it is not a surface type world the structures will spawn above bedrock.")
    @Config(modid = Tags.MOD_ID,
            category = worldGenerationConfigNumericKeyBase + "_structure_dimensions",
            customPath = worldGenerationConfigFile
    )
    @Config.LangKey(worldGenerationConfigLangKeyBase + "CategoryStructureDimensions")
    public final class StructureDimensionsConfig {
        private StructureDimensionsConfig() {
        }

        static {
            ConfigurationManager.selfInit();
        }

        public static void init() {
        }

        @Config.Name("Hill Top Stones Dimension Id Whitelist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "HillTopStonesDimensionIds")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({0})
        public static int[] HillTopStonesDimensionIds;

        @Config.Name("Stone Rings Dimension Id Whitelist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "StoneRingDimensionIds")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({0})
        public static int[] StoneRingDimensionIds;

        @Config.Name("Mound Dimension Id Whitelist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "MoundDimensionIds")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({0})
        public static int[] MoundDimensionIds;

    }

    @Config.Comment("Category for anything that doesn't fit in the others.")
    @Config(modid = Tags.MOD_ID,
            category = worldGenerationConfigNumericKeyBase + "_structure_miscellaneous",
            customPath = worldGenerationConfigFile
    )
    @Config.LangKey(worldGenerationConfigLangKeyBase + "CategoryStructureMiscellaneous")
    public final class StructureMiscellaneousConfig {
        private StructureMiscellaneousConfig() {
        }

        static {
            ConfigurationManager.selfInit();
        }

        public static void init() {
        }

        @Config.Name("Enable Mounds to conform to the terrain")
        @Config.Comment("This can add a nice deteriorated look. It will shred them on the side of large hills though.")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "MoundConformToTerrain")
        @Config.RequiresWorldRestart
        @Config.DefaultBoolean(true)
        public static boolean MoundConformToTerrain;

    }

    @Config(modid = Tags.MOD_ID,
            category = worldGenerationConfigNumericKeyBase + "_ore_generation",
            customPath = worldGenerationConfigFile
    )
    @Config.LangKey(worldGenerationConfigLangKeyBase + "CategoryOreGeneration")
    public final class OreGenerationConfig {
        private OreGenerationConfig() {
        }

        static {
            ConfigurationManager.selfInit();
        }

        public static void init() {
        }

        @Config.Name("Biome Blacklist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "oreGenerationBiomeIdBlacklist")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({})
        public static int[] OreGenerationBiomeIdBlacklist;

        @Config.Name("Dimension Blacklist")
        @Config.LangKey(worldGenerationConfigLangKeyBase + "oreGenerationDimensionIdBlacklist")
        @Config.RequiresWorldRestart
        @Config.DefaultIntList({-1, 1})
        public static int[] OreGenerationDimensionIdBlacklist;
    }

}

