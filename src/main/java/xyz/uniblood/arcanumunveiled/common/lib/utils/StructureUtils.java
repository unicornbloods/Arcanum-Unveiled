package xyz.uniblood.arcanumunveiled.common.lib.utils;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent;
import thaumcraft.common.config.ConfigBlocks;
import xyz.uniblood.arcanumunveiled.compat.EtFuturum;

import java.util.Random;

public class StructureUtils {

    public static class HilltopStonesVines extends StructureComponent.BlockSelector {
        public HilltopStonesVines() {
        }

        /**
         * picks Block Ids and Metadata
         */
        public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
            if (rand.nextInt(3) != 0) {
                this.field_151562_a = Blocks.air;
            }
        }
    }

    public static class EldritchStoneStone extends StructureComponent.BlockSelector {
        public EldritchStoneStone() {
        }

        /**
         * picks Block Ids and Metadata
         */
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
}
