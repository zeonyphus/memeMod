package xen.modone.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipies {

    public static void init(){

        GameRegistry.addSmelting(ModItems.memeDust, new ItemStack(ModItems.memeIngot, 1), 5.0f);

    }

}
