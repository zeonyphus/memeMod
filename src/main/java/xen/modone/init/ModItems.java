package xen.modone.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xen.modone.ModOne;
import xen.modone.items.*;
import xen.modone.items.tools.*;

@Mod.EventBusSubscriber(modid = ModOne.MODID)
public class ModItems {

    public static Item memeDust;
    public static Item memeIngot;
    public static Item memeRod;

    static Item memeApple;
    static Item superMemeApple;
    public static Item memeSeeds;

    static Item memeSword;
    static Item memeShovel;
    static Item memePickxe;
    static Item memeHoe;
    static Item memeAxe;
    static Item memePickaxeaxe;


    static final CreativeTabs tabMemeModItems = (new CreativeTabs("tabMemeModItems") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(memeDust);
        }
    });

    public static final Item.ToolMaterial MEMETOOLMATERIAL = EnumHelper.addToolMaterial("MEMETOOLMATERIAL", 3, 4096, 18f, 7.0f, 30);

    public static void init(){
        memeDust = new ItemBasic("memeDust").setCreativeTab(ModItems.tabMemeModItems).setMaxStackSize(64);
        memeIngot = new ItemBasic("memeIngot").setCreativeTab(ModItems.tabMemeModItems).setMaxStackSize(64);
        memeApple = new ItemCustomFood("memeApple", 5, 1f, false).setCreativeTab(ModItems.tabMemeModItems).setMaxStackSize(64);
        superMemeApple = new ItemEffectFood("superMemeApple", 5, 0.3f, false).setCreativeTab(ModItems.tabMemeModItems).setMaxStackSize(64);
        memeSword = new ItemCustomSword("memeSword", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memePickxe = new ItemCustomPickaxe("memePickaxe", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memeAxe = new ItemCustomAxe("memeAxe", MEMETOOLMATERIAL, 9.0f, -2.5f).setCreativeTab(ModItems.tabMemeModItems);
        memeShovel = new ItemCustomShovel("memeShovel", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memeHoe = new ItemCustomHoe("memeHoe", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memePickaxeaxe = new ItemPickaxeAxe("memePickaxeaxe", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memeRod = new ItemBasic("meme_rod").setCreativeTab(ModItems.tabMemeModItems).setMaxStackSize(64);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(memeIngot, memeDust, memeApple, superMemeApple, memeRod, memeSword, memePickxe, memeAxe, memeShovel, memeHoe, memePickaxeaxe);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event){
        registerRender(memeIngot);
        registerRender(memeDust);
        registerRender(memeApple);
        registerRender(superMemeApple);
        registerRender(memeSeeds);
        registerRender(memeSword);
        registerRender(memePickxe);
        registerRender(memeAxe);
        registerRender(memeShovel);
        registerRender(memeHoe);
        registerRender(memePickaxeaxe);
        registerRender(memeRod);
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
}
