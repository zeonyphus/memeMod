package xen.modone.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import xen.modone.init.ModItems;

public class ItemModArmor extends ItemArmor {

    public ItemModArmor(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot){
        super(material, renderIndex, equipmentSlot);
        setRegistryName(name);
        setUnlocalizedName(name);
        setMaxStackSize(1);
        setCreativeTab(ModItems.tabMemeModItems);
    }

}
