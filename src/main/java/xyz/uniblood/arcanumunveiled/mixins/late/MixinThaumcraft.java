package xyz.uniblood.arcanumunveiled.mixins.late;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigAspects;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigRecipes;
import thaumcraft.common.config.ConfigResearch;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

@Mixin(value = Thaumcraft.class, remap = false)
public class MixinThaumcraft {
    @Inject(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/common/DimensionManager;registerProviderType(ILjava/lang/Class;Z)Z"
            ), cancellable = true
    )
    private void cancelDimensionRegister(FMLInitializationEvent evt, CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/GameRegistry;registerWorldGenerator(Lcpw/mods/fml/common/IWorldGenerator;I)V"))
    public void removeWorldGenRegister(IWorldGenerator generator, int modGenerationWeight) {
        return;
    }

    @Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lthaumcraft/common/lib/world/ThaumcraftWorldGenerator;initialize()V"))
    public void removeWorldGenInit(ThaumcraftWorldGenerator instance) {
        return;
    }

    /**
     * @author Uniblood
     * @reason test
     */
    @Mod.EventHandler
    @Overwrite()
    public void postInit(FMLPostInitializationEvent evt) {
        Config.initPotions();
        ConfigEntities.initEntitySpawns();
        Config.initModCompatibility();
        ConfigItems.postInit();
        ConfigRecipes.init();
        ConfigAspects.init();
        ConfigResearch.init();
        Config.initLoot();
        Config.initMisc();
    }
}
