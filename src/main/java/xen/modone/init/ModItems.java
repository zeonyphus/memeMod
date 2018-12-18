package xen.modone.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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

    static Item memeDust;
    static Item memeIngot;
    static Item memeApple;
    static Item superMemeApple;

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
        memeDust = new ItemBasic("memeDust").setCreativeTab(CreativeTabs.MISC).setCreativeTab(ModItems.tabMemeModItems).setMaxStackSize(27);
        memeIngot = new ItemBasic("memeIngot").setCreativeTab(CreativeTabs.MISC).setCreativeTab(ModItems.tabMemeModItems).setMaxStackSize(9);
        memeApple = new ItemCustomFood("memeApple", 5, 1f, false).setCreativeTab(CreativeTabs.FOOD).setCreativeTab(ModItems.tabMemeModItems);
        superMemeApple = new ItemEffectFood("superMemeApple", 5, 0.3f, false).setCreativeTab(CreativeTabs.FOOD).setCreativeTab(ModItems.tabMemeModItems);
        memeSword = new ItemCustomSword("memeSword", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memePickxe = new ItemCustomPickaxe("memePickaxe", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memeAxe = new ItemCustomAxe("memeAxe", MEMETOOLMATERIAL, 9.0f, -2.5f).setCreativeTab(ModItems.tabMemeModItems);
        memeShovel = new ItemCustomShovel("memeShovel", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memeHoe = new ItemCustomHoe("memeHoe", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
        memePickaxeaxe = new ItemPickaxeAxe("memePickaxeaxe", MEMETOOLMATERIAL).setCreativeTab(ModItems.tabMemeModItems);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(memeIngot, memeDust, memeApple, superMemeApple, memeSword, memePickxe, memeAxe, memeShovel, memeHoe, memePickaxeaxe);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event){
        registerRender(memeIngot);
        registerRender(memeDust);
        registerRender(memeApple);
        registerRender(superMemeApple);
        registerRender(memeSword);
        registerRender(memePickxe);
        registerRender(memeAxe);
        registerRender(memeShovel);
        registerRender(memeHoe);
        registerRender(memePickaxeaxe);
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }
}
