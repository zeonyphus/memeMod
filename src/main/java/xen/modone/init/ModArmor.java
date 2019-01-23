package xen.modone.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xen.modone.ModOne;
import xen.modone.items.ItemModArmor;

@Mod.EventBusSubscriber(modid = ModOne.MODID)
public class ModArmor {

    public static ItemArmor.ArmorMaterial MEMEARMOR = EnumHelper.addArmorMaterial("meme_armor", "modone:meme", 780, new int[] {4,9,7,4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0f);

    public static ItemArmor MEME_HELMET;
    public static ItemArmor MEME_CHESTPLATE;
    public static ItemArmor MEME_LEGGINGS;
    public static ItemArmor MEME_BOOTS;

    public static void init(){

        MEME_HELMET = new ItemModArmor("meme_helmet", MEMEARMOR, 1 , EntityEquipmentSlot.HEAD);
        MEME_CHESTPLATE = new ItemModArmor("meme_chestplate", MEMEARMOR, 1, EntityEquipmentSlot.CHEST);
        MEME_LEGGINGS = new ItemModArmor("meme_leggings", MEMEARMOR, 2, EntityEquipmentSlot.LEGS);
        MEME_BOOTS = new ItemModArmor("meme_boots", MEMEARMOR, 1, EntityEquipmentSlot.FEET);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(MEME_HELMET, MEME_CHESTPLATE, MEME_LEGGINGS, MEME_BOOTS);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event){
        registerRender(MEME_HELMET);
        registerRender(MEME_CHESTPLATE);
        registerRender(MEME_LEGGINGS);
        registerRender(MEME_BOOTS);

    }

    private static void registerRender(Item item){
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}