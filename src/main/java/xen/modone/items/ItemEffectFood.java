package xen.modone.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEffectFood extends ItemCustomFood {

    public ItemEffectFood(String name, int amount, float saturation, boolean isWolfFood){
        super(name, amount, saturation, isWolfFood);
        setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player){
        if(!worldIn.isRemote){
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 30*20, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 20*20, 5, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 30*20, 5, false, false));
        }
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack){
        return true;
    }

}
