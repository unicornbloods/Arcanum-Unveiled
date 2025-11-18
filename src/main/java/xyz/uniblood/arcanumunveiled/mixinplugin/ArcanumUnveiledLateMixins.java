package xyz.uniblood.arcanumunveiled.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import org.jetbrains.annotations.NotNull;
import xyz.uniblood.arcanumunveiled.common.config.ConfigUtils;

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
        ConfigUtils configUtils = new ConfigUtils();

        configUtils.initConfigs();

        mixins.add("MixinThaumcraft");

        return mixins;
    }
}
