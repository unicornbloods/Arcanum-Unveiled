package xyz.uniblood.arcanumunveiled.common.lib.utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * Utility class containing various helper methods.
 */
public class Utils {

    /**
     * Checks if a specific bit in an integer value is set (equal to 1).
     *
     * @param value The integer value to check.
     * @param bit The index of the bit to check (0-indexed from the right).
     * @return {@code true} if the specified bit is set, {@code false} otherwise.
     */
    public static boolean getBit(int value, int bit) {
        return (value & 1 << bit) != 0;
    }

    /**
     * Sets a specific bit in an integer value to 1.
     *
     * @param value The integer value to modify.
     * @param bit The index of the bit to set (0-indexed from the right).
     * @return The modified integer value with the specified bit set.
     */
    public static int setBit(int value, int bit) {
        return value | 1 << bit;
    }

    /**
     * Finds the first uncovered Y-level (block position in the vertical axis) at a given (X, Z) coordinate
     * that is suitable for placing a structure.
     * <p>
     * For non-Nether worlds (dimension ID != -1), it searches downwards from the top of the chunk's filled segment
     * for a non-replaceable, movement-blocking block that is not leaves, foliage, or wood, and returns the block
     * position *above* that block.
     * <p>
     * For Nether worlds (dimension ID == -1), it searches upwards from Y=5 until it finds an air block *above*
     * a non-air block.
     *
     * @param world The world object.
     * @param x The X-coordinate.
     * @param z The Z-coordinate.
     * @return The Y-level suitable for placement.
     */
    public static int getFirstUncoveredY(World world, int x, int z) {
        // Use this for non-nether worlds
        if (world.provider.dimensionId != -1) {
            Chunk chunk = world.getChunkFromBlockCoords(x, z);
            int chunkX = x & 15;
            int chunkZ = z & 15;
            for (int yLevel = chunk.getTopFilledSegment() + 15; yLevel > 0; --yLevel) {
                Block block = chunk.getBlock(chunkX, yLevel, chunkZ);
                Material material = block.getMaterial();
                if (material.blocksMovement() && !material.isReplaceable() && !block.isLeaves(world, x, yLevel, z) && !block.isFoliage(world, x, yLevel, z) && !block.isWood(world, x, yLevel, z)) {
                    return yLevel + 1;
                }
            }
        }

        // Use this for nether worlds. The above method tries to place structures on the surface, which is not possible in nether.
        int yLevel = 5;
        while (!world.isAirBlock(x, yLevel + 1, z)) {
            ++yLevel;
        }
        return yLevel;
    }
}
