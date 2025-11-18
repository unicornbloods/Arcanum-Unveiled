package xyz.uniblood.arcanumunveiled.common.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class GeneralConfig {

    //Category names
    static final String categoryIds = "ids";

    // Ids
    public static int dimensionOuterId = -42;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        // Ids
        dimensionOuterId = configuration.getInt("Outer Lands Dimension Id", categoryIds, dimensionOuterId, Short.MIN_VALUE, Short.MAX_VALUE, "");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
