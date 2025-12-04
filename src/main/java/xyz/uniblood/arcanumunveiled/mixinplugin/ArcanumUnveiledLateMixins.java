package xyz.uniblood.arcanumunveiled.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import org.jetbrains.annotations.NotNull;
import xyz.uniblood.arcanumunveiled.config.GeneralConfig;
import xyz.uniblood.arcanumunveiled.config.WorldGenerationConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class ArcanumUnveiledLateMixins implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.arcanumunveiled.late.json";
    }

    @Override
    public @NotNull List<String> getMixins(Set<String> loadedMods) {
        // Client check not needed yet
//        boolean client = FMLLaunchHandler.side().isClient();
        List<String> mixins = new ArrayList<>();

        // Initialize my configs as early as possible.
        GeneralConfig.init();
        WorldGenerationConfig.init();

        mixins.add("MixinThaumcraft");

        return mixins;
    }
}
