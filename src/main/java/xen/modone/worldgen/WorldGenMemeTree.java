package xen.modone.worldgen;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import xen.modone.init.ModBlocks;

import java.util.Random;

public class WorldGenMemeTree extends WorldGenAbstractTree {

    private IBlockState blockStateWood = ModBlocks.memeLog.getDefaultState();
    private IBlockState blockStateLeaves = ModBlocks.memeLeaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private final int minTreeHeight = 6;

    public WorldGenMemeTree(boolean parShouldNotify){
        super(parShouldNotify);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos){
        int minHeight = rand.nextInt(3) + minTreeHeight;

        if(pos.getY() >= 1 && pos.getY() + minHeight + 1 <= world.getHeight()){
            if(!isSuitableLocation(world, pos, minHeight)){
                return false;
            }else{
                IBlockState state = world.getBlockState(pos.down());
                if(state.getBlock().canSustainPlant(state, world, pos.down(), EnumFacing.UP, (IPlantable) Blocks.SAPLING) && pos.getY() < world.getHeight() - minHeight - 1){
                    state.getBlock().onPlantGrow(state, world, pos.down(), pos);
                    generateLeaves(world, pos, minHeight, rand);
                    generateTrunk(world, pos, minHeight);
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
    }

    private void generateLeaves(World world, BlockPos pos, int height, Random rand){
        for (int foliageY = pos.getY() - 3 + height; foliageY <= pos.getY() + height; ++foliageY)
        {
            int foliageLayer = foliageY - (pos.getY() + height);
            int foliageLayerRadius = 1 - foliageLayer / 2;

            for (int foliageX = pos.getX() - foliageLayerRadius; foliageX <= pos.getX() + foliageLayerRadius; ++foliageX)
            {
                int foliageRelativeX = foliageX - pos.getX();

                for (int foliageZ = pos.getZ() - foliageLayerRadius; foliageZ <= pos.getZ() + foliageLayerRadius; ++foliageZ)
                {
                    int foliageRelativeZ = foliageZ - pos.getZ();

                    // Fill in layer with some randomness
                    if (Math.abs(foliageRelativeX) != foliageLayerRadius || Math.abs(foliageRelativeZ) != foliageLayerRadius || rand.nextInt(2) != 0 && foliageLayer != 0)
                    {
                        BlockPos blockPos = new BlockPos(foliageX, foliageY, foliageZ);
                        IBlockState state = world.getBlockState(blockPos);

                        if (state.getBlock().isAir(state, world, blockPos) || state.getBlock().isLeaves(state, world, blockPos))
                        {
                            setBlockAndNotifyAdequately(world, blockPos, blockStateLeaves);
                        }
                    }
                }
            }
        }
    }

    private void generateTrunk(World world, BlockPos pos, int minHeight){
        for(int height = 0; height < minHeight; height++){
            BlockPos upN = pos.up(height);
            IBlockState state = world.getBlockState(upN);
            if(state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)){
                setBlockAndNotifyAdequately(world, pos.up(height), blockStateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            }
        }
    }

    private boolean isSuitableLocation(World world, BlockPos pos, int minHeigt){
        boolean isSuitableLocation = true;
        for(int checkY = pos.getY(); checkY <= pos.getY() + 1 + minHeigt; checkY++){
            int extraSpaceNeeded = 1;
            if(checkY == pos.getY()){
                extraSpaceNeeded = 0;
            }
            if(checkY >= pos.getY() + 1 + minHeigt - 2){
                extraSpaceNeeded = 2;
            }
            BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
            for (int checkX = pos.getX() - extraSpaceNeeded; checkX <= pos.getX() + extraSpaceNeeded && isSuitableLocation; checkX++){
                for(int checkZ = pos.getZ() - extraSpaceNeeded; checkZ <= pos.getZ() + extraSpaceNeeded && isSuitableLocation; checkZ++){
                    isSuitableLocation = isReplaceable(world, blockPos.setPos(checkX, checkY, checkZ));
                }
            }
        }
        return isSuitableLocation;
    }

}
