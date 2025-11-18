package xyz.uniblood.arcanumunveiled.common.lib.world.dimension;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;

/**
 * A simple chunk provider for the 'Outer' dimension.
 */
public class ChunkProviderOuter implements IChunkProvider {
    // Total blocks in a chunk section: 16*16*256
    private static final int CHUNK_SIZE = 16 * 16 * 256;

    private final World worldObj;

    public ChunkProviderOuter(World world, long seed, boolean features) {
        this.worldObj = world;
        // The seed and features arguments are typically used for noise generation,
    }

    // --- IChunkProvider Methods ---

    /**
     * Loads a chunk from memory/disk if it exists, otherwise generates it.
     */
    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        return this.provideChunk(chunkX, chunkZ);
    }

    /**
     * Generates a new chunk at the specified coordinates.
     */
    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        // Allocate array for blocks and metadata.
        Block[] ablock = new Block[CHUNK_SIZE / 2];
        byte[] meta = new byte[ablock.length];

        Chunk chunk = new Chunk(this.worldObj, ablock, meta, chunkX, chunkZ);

        chunk.generateSkylightMap();
        chunk.setChunkModified();
        return chunk;
    }

    @Override
    public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ) {
    }

    @Override
    public boolean saveChunks(boolean saveAll, IProgressUpdate progressCallback) {
        return true;
    }

    @Override
    public void saveExtraData() {
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "ChunkProviderOuter";
    }

    @Override
    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        // Delegate to the biome at the coordinates
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(x, z);
        return biomegenbase.getSpawnableList(creatureType);
    }

    @Override
    public ChunkPosition func_147416_a(World world, String structureName, int x, int y, int z) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int chunkX, int chunkZ) {
    }
}