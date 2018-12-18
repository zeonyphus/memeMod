package xen.modone.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xen.modone.ModOne;
import xen.modone.blocks.BlockBasic;
import xen.modone.blocks.BlockMemeSlab;
import xen.modone.blocks.BlockOre;

@Mod.EventBusSubscriber(modid = ModOne.MODID)
public class ModBlocks {

    static Block memeBlock;
    public static Block memeOre;
    public static BlockMemeSlab memeSlabHalf;
    public static BlockMemeSlab memeSlabDouble;

    static final CreativeTabs tabMemeModBlocks = (new CreativeTabs("tabMemeModBlocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(memeBlock);
        }
    });

    static final CreativeTabs tabMemeModEnvironment = (new CreativeTabs("tabMemeModEnvironment") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack((memeOre));
        }
    });

    public static void init(){
        memeBlock = new BlockBasic("memeBlock", Material.ROCK).setHardness(30.0f).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setCreativeTab(ModBlocks.tabMemeModBlocks).setLightLevel(1.0f);
        memeBlock.setHarvestLevel("pickaxe", 3);
        memeOre = new BlockOre("memeOre", Material.ROCK, ModItems.memeDust, 1, 5).setHardness(5.0f).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setCreativeTab(ModBlocks.tabMemeModEnvironment).setResistance(5f).setLightLevel(3.0f/15.0f);
        memeOre.setHarvestLevel("pickaxe", 3);

        memeSlabHalf = new BlockMemeSlab.Half("meme_slab_half", Material.ROCK);
        memeSlabHalf.setCreativeTab(ModBlocks.tabMemeModBlocks).setHardness(3f).setResistance(5f).setHarvestLevel("pickaxe", 2);
        memeSlabDouble = new BlockMemeSlab.Double("meme_slab_double", Material.ROCK);
        memeSlabDouble.setHardness(3f).setResistance(5f).setHarvestLevel("pickaxe", 2);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(memeBlock, memeOre, memeSlabHalf, memeSlabDouble);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(new ItemBlock(memeBlock).setRegistryName(memeBlock.getRegistryName()), new ItemBlock(memeOre).setRegistryName(memeOre.getRegistryName()));
        event.getRegistry().register(new ItemSlab(memeSlabHalf, memeSlabHalf, memeSlabDouble).setRegistryName(memeSlabHalf.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event){
        registerRender(Item.getItemFromBlock(memeBlock));
        registerRender(Item.getItemFromBlock(memeOre));
        registerRender(Item.getItemFromBlock(memeSlabHalf));
    }

    public static void registerRender(Item item){
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
}
