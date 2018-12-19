package xen.modone.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import xen.modone.init.ModItems;

import java.util.Random;

public class MemeWheat extends BlockCrops {

    static PropertyInteger CROP_AGE = PropertyInteger.create("age", 0,4);
    static AxisAlignedBB[] CROP_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.35D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.40D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)};

    public MemeWheat(String name){
        super();
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    protected boolean canSustainBush(IBlockState state){
        return state.getBlock() == Blocks.GOLD_BLOCK;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state){
        IBlockState soil = worldIn.getBlockState(pos.down());
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && soil.getBlock() == Blocks.GOLD_BLOCK;
    }

    protected PropertyInteger getAgeProperty(){
        return CROP_AGE;
    }

    public int getMaxAge(){
        return 4;
    }

    protected Item getSeed(){
        return ModItems.memeSeeds;
    }

    protected Item getCrop(){
        return ModItems.memeDust;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
        if(rand.nextInt(3) == 0){
            this.checkAndDropBlock(worldIn, pos, state);
        }else{
            super.updateTick(worldIn, pos, state, rand);
        }
    }

    protected int getBonemealAgeIncrease(World worldIn){
        return MathHelper.getInt(worldIn.rand, 1, 3);
    }

    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[]{CROP_AGE});
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return CROP_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }
}
