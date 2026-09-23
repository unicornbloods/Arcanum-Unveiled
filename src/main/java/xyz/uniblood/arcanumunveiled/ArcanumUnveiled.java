package xyz.uniblood.arcanumunveiled;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import xyz.uniblood.arcanumunveiled.common.config.ConfigAspects;
import xyz.uniblood.arcanumunveiled.common.config.ConfigRecipes;
import xyz.uniblood.arcanumunveiled.common.config.ConfigResearch;
import xyz.uniblood.arcanumunveiled.common.init.*;
import xyz.uniblood.arcanumunveiled.common.lib.events.ModBusEvents;
import xyz.uniblood.arcanumunveiled.common.lib.world.dim.EldritchChunkGenerator;
import xyz.uniblood.arcanumunveiled.datagen.DataGenerators;

import java.util.Objects;

@Mod(ArcanumUnveiled.MODID)
public class ArcanumUnveiled {
    public static final String MODID = "arcanumunveiled";

    public ArcanumUnveiled(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModParticles.PARTICLE_TYPES.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        ModDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);
        ModVillagers.POI_TYPES.register(modEventBus);
        ModVillagers.PROFESSIONS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModStructures.PROCESSORS.register(modEventBus);
        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModFluids.FLUID_TYPES.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ModBusEvents::registerAttributes);
        modEventBus.addListener(ModBusEvents::registerSpawnPlacements);
        modEventBus.addListener(ModCapabilities::registerCapabilities);
        modEventBus.addListener(this::registerGenerators);
        modEventBus.addListener(DataGenerators::gatherData);

        // Register custom Overworld biomes via TerraBlender
        terrablender.api.Regions.register(new xyz.uniblood.arcanumunveiled.common.init.ArcanumUnveiledRegion(
                ResourceLocation.fromNamespaceAndPath(MODID, "overworld_region"), 3));
    }

    private void registerGenerators(RegisterEvent event) {
        if (Objects.equals(event.getRegistryKey(), Registries.CHUNK_GENERATOR)) {
            event.register(Registries.CHUNK_GENERATOR, ResourceLocation.fromNamespaceAndPath(MODID, "eldritch_generator"), () -> EldritchChunkGenerator.CODEC);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ConfigAspects.init();
            ConfigResearch.init();
            ConfigRecipes.init();
            ModWandComponents.init();

            // Register surface rules for custom biomes via TerraBlender
            terrablender.api.SurfaceRuleManager.addSurfaceRules(terrablender.api.SurfaceRuleManager.RuleCategory.OVERWORLD, MODID,
                    xyz.uniblood.arcanumunveiled.common.init.ArcanumUnveiledSurfaceRules.makeRules());
        });
    }
}
