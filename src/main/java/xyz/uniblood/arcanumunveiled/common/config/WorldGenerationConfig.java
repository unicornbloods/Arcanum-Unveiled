package xyz.uniblood.arcanumunveiled.common.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class WorldGenerationConfig {

    //Category names
    static final String categoryStructureWeights = "Structure Weights";
    static final String categoryStructureBiomes = "Structure Biomes";
    static final String categoryStructureDimensions = "Structure Dimensions";

    // Structure spawn weights
    public static int hillTopStonesWeight = 1;

    // Structure biome ids
    public static int[] hillTopStonesBiomeIds = {
            3,
            17,
            18,
            19,
            20,
            22,
            28,
            31,
            33,
            34,
            36,
            38,
            39
    };

    // Structure dimension ids
    public static int[] hillTopStonesDimensionIds = {0, 7};

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);


        // Structure spawn weights
        configuration.addCustomCategoryComment(categoryStructureWeights,
                "A value of 0 is off. A value of 32 is 32 times more common than a structure of value 1." + System.lineSeparator() + "Be sure to check the nbtlib.cfg for more.");
        hillTopStonesWeight = configuration.getInt("Hill Top Stones Spawn Weight", categoryStructureWeights, hillTopStonesWeight, 0, 32, "");

        // Structure biome ids
        configuration.addCustomCategoryComment(categoryStructureBiomes, "This is just a numerical list of biome ids that is checked against for structure spawning.");
        configuration.get(categoryStructureBiomes, "Hill Top Stones Biome Id Whitelist", hillTopStonesBiomeIds, "").getIntList();

        // Structure dimension ids
        configuration.addCustomCategoryComment(categoryStructureDimensions, "This is just a numerical list of dimension ids that is checked against for structure spawning.");
        configuration.get(categoryStructureDimensions, "Hill Top Stones Dimension Id Whitelist", hillTopStonesDimensionIds, "").getIntList();

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
