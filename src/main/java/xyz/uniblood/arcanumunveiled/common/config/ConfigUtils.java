package xyz.uniblood.arcanumunveiled.common.config;

import java.io.File;

public class ConfigUtils {
    // Config files
    public static final String GENERAL_CONFIG = "general.cfg";
    public static final String WORLD_GENERATION_CONFIG = "worldGeneration.cfg";
    // Config directory path
    private static final String CONFIG_DIRECTORY = "config" + File.separator + "ArcanumUnveiled" + File.separator;

    public void initConfigs() {
        GeneralConfig.synchronizeConfiguration(new File(CONFIG_DIRECTORY + GENERAL_CONFIG));
        WorldGenerationConfig.synchronizeConfiguration(new File(CONFIG_DIRECTORY + WORLD_GENERATION_CONFIG));
    }
}
