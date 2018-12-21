package xen.modone.blocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xen.modone.init.ModBlocks;
import xen.modone.init.ModItems;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockMemeLeaves extends BlockLeaves {

    public BlockMemeLeaves(String name){
        setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE,Boolean.valueOf(true)));
        setRegistryName(name);
        setUnlocalizedName(name);
    }

    @Override
    protected void dropApple(World world, BlockPos pos, IBlockState state, int chance){
        if(world.rand.nextInt(chance) == 0);{
            spawnAsEntity(world, pos, new ItemStack(ModItems.memeDust));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ModBlocks.memeSapling);
    }

    @Override
    public void getSubBlocks(CreativeTabs item, NonNullList<ItemStack> items){
        items.add(new ItemStack(this));
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state){
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state){
        int i = 0;
        if(!state.getValue(DECAYABLE).booleanValue()){
            i |=4;
        }
        if(state.getValue(CHECK_DECAY).booleanValue()){
            i |=8;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    @Override
    public int damageDropped(IBlockState state){
        return 0;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack){
        if(!world.isRemote && stack.getItem() == Items.SHEARS){
            player.addStat(StatList.getBlockStats(this));
        }else{
            super.harvestBlock(world, player, pos, state, te, stack);
        }
    }

    @Override
    public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune){
        return NonNullList.withSize(1, new ItemStack(this));
    }

    @Override
    public EnumType getWoodType(int meta){
        return null;
    }

}
