package xen.modone.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xen.modone.ModOne;
import xen.modone.enchantment.EnchantmentGrassness;
import xen.modone.enchantment.EnchantmentThunder;

@Mod.EventBusSubscriber(modid= ModOne.MODID)
public class ModEnchantments {

    public static final Enchantment THUNDER = new EnchantmentThunder("enchantmentThunder");
    public static final Enchantment GRASSNESS = new EnchantmentGrassness("enchantmentGrassness");

    public static final EnumEnchantmentType WEAPONS = EnumHelper.addEnchantmentType("weapons", (item)->(item instanceof ItemSword || item instanceof ItemBow));

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event){
        event.getRegistry().registerAll(THUNDER, GRASSNESS);
    }

}
