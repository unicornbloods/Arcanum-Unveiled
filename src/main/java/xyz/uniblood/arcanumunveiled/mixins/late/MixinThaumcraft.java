package xyz.uniblood.arcanumunveiled.mixins.late;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.Thaumcraft;

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
}
