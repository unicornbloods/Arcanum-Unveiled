package xyz.uniblood.arcanumunveiled.common.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class WorldGenerationConfig {

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
