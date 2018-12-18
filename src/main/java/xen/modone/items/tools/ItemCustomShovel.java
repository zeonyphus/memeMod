package xen.modone.items.tools;

import net.minecraft.item.ItemSpade;

public class ItemCustomShovel extends ItemSpade {

    public ItemCustomShovel(String name, ToolMaterial material){
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

}
