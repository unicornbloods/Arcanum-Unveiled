package xyz.uniblood.arcanumunveiled.config;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import xyz.uniblood.arcanumunveiled.Tags;

public final class GeneralConfig {

    private final static String generalConfigLangKeyBase = "config.arcanumunveiled.config.general.";
    private final static String generalConfigFile = "ArcanumUnveiled/general";
    // Used to sort the config entries in the mod menu gui.
    private final static String generalConfigNumericKeyBase = "00";

    public static void init() {
        IdentifiersConfig.init();
    }


    @Config.Comment("Various identifiers from throughout the mod")
    @Config(modid = Tags.MOD_ID,
            category = generalConfigNumericKeyBase + "_ids",
            customPath = generalConfigFile
    )
    @Config.LangKey(generalConfigLangKeyBase + "CategoryIds")
    public final class IdentifiersConfig {
        private IdentifiersConfig() {
        }

        static {
            ConfigurationManager.selfInit();
        }

        public static void init() {
        }

        @Config.Name("Outer Lands Dimension Id")
        @Config.LangKey(generalConfigLangKeyBase + "MoundConformToTerrain")
        @Config.RequiresWorldRestart
        @Config.DefaultInt(-42)
        public static int DimensionOuterId;

    }


}
