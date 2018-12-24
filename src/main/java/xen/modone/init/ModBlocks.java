package xen.modone.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
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
import xen.modone.blocks.*;
import xen.modone.blocks.machines.BlockMemeProcessor;
import xen.modone.items.ItemBasic;
import xen.modone.items.ItemMemeSeeds;

@Mod.EventBusSubscriber(modid = ModOne.MODID)
public class ModBlocks {

    public static Block memeBlock;
    public static Block memeOre;
    public static Block memeBrick;
    public static Block memeObsidian;

    public static BlockMemeSlab memeSlabHalf;
    public static BlockMemeSlab memeSlabDouble;

    public static BlockLog memeLog;
    public static BlockMemeLeaves memeLeaves;
    public static BlockMemeSapling memeSapling;

    public static BlockBasic memeGrass;

    static Block memeWheat;

    public static Block memeProcessor;

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
        memeBlock = new BlockBasic("meme_block", Material.ROCK).setHardness(30.0f).setCreativeTab(ModBlocks.tabMemeModBlocks).setLightLevel(1.0f);
        memeBlock.setHarvestLevel("pickaxe", 3);
        memeOre = new BlockOre("meme_ore", Material.ROCK, ModItems.memeDust, 1, 5).setHardness(5.0f).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setCreativeTab(ModBlocks.tabMemeModEnvironment).setResistance(5f).setLightLevel(4.0f/15.0f);
        memeOre.setHarvestLevel("pickaxe", 3);
        memeBrick = new BlockBasic("meme_brick", Material.ROCK).setHardness(35.0f).setCreativeTab(ModBlocks.tabMemeModBlocks).setLightLevel(1.0f);
        memeBrick.setHarvestLevel("picaxe", 3);
        memeObsidian = new BlockBasic("meme_obsidian", Material.ROCK).setHardness(60.0f).setCreativeTab(ModBlocks.tabMemeModBlocks).setLightLevel(10.0f/15.0f);
        memeObsidian.setHarvestLevel("pickaxe", 4);

        //TODO Need to fix the grass so trees can be planted on it
        //TODO the way the grass pulls in the textures is absolute GARBAGE in the JSON
        memeGrass = new BlockBasic("meme_grass", Material.GRASS);
        memeGrass.setHardness(20.0f).setHarvestLevel("shovel", 3);
        memeGrass.setCreativeTab(ModBlocks.tabMemeModEnvironment);

        //TODO the slabs seem slightly off color
        memeSlabHalf = new BlockMemeSlab.Half("meme_slab_half", Material.ROCK);
        memeSlabHalf.setCreativeTab(ModBlocks.tabMemeModBlocks).setHardness(3f).setResistance(5f).setHarvestLevel("pickaxe", 2);
        memeSlabDouble = new BlockMemeSlab.Double("meme_slab_double", Material.ROCK);
        memeSlabDouble.setHardness(3f).setResistance(5f).setHarvestLevel("pickaxe", 2);

        memeWheat = new MemeWheat("meme_wheat");

        memeLog = new BlockMemeLog("meme_log");
        memeLog.setCreativeTab(ModBlocks.tabMemeModEnvironment).setHarvestLevel("axe", 3);

        //TODO leaves have no "fancy" variant
        memeLeaves = new BlockMemeLeaves("meme_leaves");
        memeLeaves.setCreativeTab(ModBlocks.tabMemeModEnvironment);
        memeSapling = new BlockMemeSapling("meme_sapling");
        memeSapling.setCreativeTab(ModBlocks.tabMemeModEnvironment);

        //TODO make more than one block come out of processors
        memeProcessor = new BlockMemeProcessor("meme_processor", Material.ROCK);
        memeProcessor.setCreativeTab(ModBlocks.tabMemeModBlocks);

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(memeBlock, memeOre, memeGrass, memeBrick, memeSlabHalf, memeSlabDouble, memeWheat, memeObsidian, memeLog, memeLeaves, memeSapling, memeProcessor);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new ItemBlock(memeBlock).setRegistryName(memeBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(memeOre).setRegistryName(memeOre.getRegistryName()));
        event.getRegistry().register(new ItemBlock(memeBrick).setRegistryName(memeBrick.getRegistryName()));
        event.getRegistry().register(new ItemSlab(memeSlabHalf, memeSlabHalf, memeSlabDouble).setRegistryName(memeSlabHalf.getRegistryName()));
        event.getRegistry().register(new ItemBlock(memeObsidian).setRegistryName(memeObsidian.getRegistryName()));
        event.getRegistry().register(new ItemBlock(memeGrass).setRegistryName(memeGrass.getRegistryName()));

        ModItems.memeSeeds = new ItemMemeSeeds(ModBlocks.memeWheat, Blocks.GOLD_BLOCK, "meme_seeds").setCreativeTab(ModItems.tabMemeModItems);
        event.getRegistry().register(ModItems.memeSeeds);

        event.getRegistry().register(new ItemBlock(memeLog).setRegistryName(memeLog.getRegistryName()));
        event.getRegistry().register(new ItemBlock(memeLeaves).setRegistryName(memeLeaves.getRegistryName()));
        event.getRegistry().register(new ItemBlock(memeSapling).setRegistryName(memeSapling.getRegistryName()));
        event.getRegistry().register(new ItemBlock(memeProcessor).setRegistryName(memeProcessor.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event){
        registerRender(Item.getItemFromBlock(memeBlock));
        registerRender(Item.getItemFromBlock(memeOre));
        registerRender(Item.getItemFromBlock(memeSlabHalf));
        registerRender(Item.getItemFromBlock(memeBrick));
        registerRender(Item.getItemFromBlock(memeObsidian));
        registerRender(Item.getItemFromBlock(memeLog));
        registerRender(Item.getItemFromBlock(memeLeaves));
        registerRender(Item.getItemFromBlock(memeSapling));
        registerRender(Item.getItemFromBlock(memeGrass));
        registerRender(Item.getItemFromBlock(memeProcessor));
    }

    public static void registerRender(Item item){
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
}
