package xen.modone.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xen.modone.init.ModBlocks;

import java.util.Random;

public abstract class BlockMemeSlab extends BlockSlab {

    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);

    public BlockMemeSlab(String name, Material material){
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if(!this.isDouble()){
            iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate);
        this.useNeighborBrightness = !this.isDouble();
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName();
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return Variant.DEFAULT;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.memeSlabHalf);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModBlocks.memeSlabHalf);
    }

    @Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if(!this.isDouble()){
            blockstate = blockstate.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP:EnumBlockHalf.BOTTOM);
        }
        return blockstate;
    }

    @Override
    public final int getMetaFromState(final IBlockState state) {
        int meta = 0;

        if(!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP){
            meta |= 8;
        }
        return meta;
    }


    @Override
    protected BlockStateContainer createBlockState(){

        if(!this.isDouble()){
            return new BlockStateContainer(this, new IProperty[]{VARIANT, HALF});
        }
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    public static class Double extends BlockMemeSlab{
        public Double(String name, Material material){
            super(name, material);
        }

        @Override
        public boolean isDouble(){
            return true;
        }
    }

    public static class Half extends BlockMemeSlab{
        public Half(String name, Material material){
            super(name, material);
        }

        @Override
        public boolean isDouble(){
            return false;
        }
    }

    public static enum Variant implements IStringSerializable{

        DEFAULT;

        @Override
        public String getName(){
            return "default";
        }
    }
}