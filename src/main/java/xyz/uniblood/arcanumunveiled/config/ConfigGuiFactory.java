package xyz.uniblood.arcanumunveiled.config;

import com.falsepattern.lib.config.ConfigException;
import com.falsepattern.lib.config.SimpleGuiConfig;
import com.falsepattern.lib.config.SimpleGuiFactory;
import net.minecraft.client.gui.GuiScreen;
import xyz.uniblood.arcanumunveiled.Tags;

import java.util.ArrayList;

@SuppressWarnings("unused")
public final class ConfigGuiFactory implements SimpleGuiFactory {
    private static final String CONFIG_GUI_NAME = Tags.MOD_NAME + " Config";

    public ConfigGuiFactory() {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ArcanumUnveiledGuiConfig.class;
    }

    private static Class<?>[] getConfigClasses() {
        ArrayList<Class<?>> result = new ArrayList<>();
        // General
        result.add(GeneralConfig.IdentifiersConfig.class);
        // World Generation
        result.add(WorldGenerationConfig.StructureCustomizationConfig.class);
        result.add(WorldGenerationConfig.StructureSpawnWeightConfig.class);
        result.add(WorldGenerationConfig.StructureBiomesConfig.class);
        result.add(WorldGenerationConfig.StructureDimensionsConfig.class);
        result.add(WorldGenerationConfig.StructureMiscellaneousConfig.class);
        result.add(WorldGenerationConfig.OreGenerationConfig.class);
        return result.toArray(new Class<?>[0]);
    }

    public static final class ArcanumUnveiledGuiConfig extends SimpleGuiConfig {
        public ArcanumUnveiledGuiConfig(GuiScreen parent) throws ConfigException {
            super(parent, Tags.MOD_ID, CONFIG_GUI_NAME, getConfigClasses());
        }
    }
}