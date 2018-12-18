package xen.modone.worldgen;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import xen.modone.init.ModBlocks;

import java.util.Random;

public class OreGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        switch(world.provider.getDimension()) {
            //Nether
            case -1:
                break;
            //Overworld
            case 0:
                runGenerator(ModBlocks.memeOre.getDefaultState(), 7,10,6,58, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
            //End
            case 1:
                break;
            //Others
            default:
                break;
        }

    }

    private void runGenerator(IBlockState blockToGen, int amountToGen, int spawnChance, int minHeight, int maxHeight, Predicate<IBlockState> blockToReplace, World world, Random rand, int chunk_X, int chunk_Z){

        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        WorldGenMinable generator = new WorldGenMinable(blockToGen, amountToGen, blockToReplace);
        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < spawnChance; i++){
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z + rand.nextInt(16);

            generator.generate(world, rand, new BlockPos(x, y, z));
            //System.out.println("New ore block at x: " + x + ", y: " + y + ", z: " + z + " in chunk [" + chunk_X + ", " + chunk_Z + "]");
        }
    }
}
