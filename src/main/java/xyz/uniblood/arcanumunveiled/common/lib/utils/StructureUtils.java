package xyz.uniblood.arcanumunveiled.common.lib.utils;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent;
import thaumcraft.common.config.ConfigBlocks;
import xyz.uniblood.arcanumunveiled.compat.EtFuturum;

import java.util.Random;

public class StructureUtils {
    /*
        Blocks reserved for replacement
        - Stone is used for Eldritch stones
        - Terracotta (stained_hardened_clay) is used for urns
        - Cobblestone is mossy cobblestone 25%
    */


    public static class HilltopStonesVines extends StructureComponent.BlockSelector {
        public HilltopStonesVines() {
        }

        public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
            if (rand.nextInt(3) != 0) {
                this.field_151562_a = Blocks.air;
            }
        }
    }

    public static class EldritchStoneStone extends StructureComponent.BlockSelector {
        public EldritchStoneStone() {
        }

        public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
            // For the vine
            if (rand.nextBoolean()) {
                if (EtFuturum.IS_LOADED && rand.nextInt(4) == 0) {
                    this.field_151562_a = EtFuturum.CRYING_OBSIDIAN;
                } else {
                    this.field_151562_a = Blocks.obsidian;
                }
            } else {
                this.field_151562_a = ConfigBlocks.blockCosmeticSolid;
                this.selectedBlockMetaData = 1;
            }
        }
    }

    public static class structureUrns extends StructureComponent.BlockSelector {
        public structureUrns() {
        }

        public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
            this.field_151562_a = ConfigBlocks.blockLootUrn;
            float metaDataRandom = rand.nextFloat();
            if (metaDataRandom < 0.1F) {
                this.selectedBlockMetaData = 2;
            } else if (metaDataRandom < 0.33F) {
                this.selectedBlockMetaData = 1;
            } else {
                this.selectedBlockMetaData = 0;
            }
        }
    }

    public static class structureMossyStone extends StructureComponent.BlockSelector {
        public structureMossyStone() {
        }

        public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
            if (rand.nextInt(3) == 0) {
                this.field_151562_a = Blocks.mossy_cobblestone;
            } else {
                this.field_151562_a = Blocks.cobblestone;
            }
        }
    }
}
