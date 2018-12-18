package xen.modone.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.inventory.EntityEquipmentSlot;
import xen.modone.init.ModEnchantments;

public class EnchantmentThunder extends Enchantment {

    public EnchantmentThunder(String name){
        super(Rarity.UNCOMMON, ModEnchantments.WEAPONS, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});

    }

    @Override
    public int getMaxLevel(){
        return 5;
    }

    @Override
    public int getMinLevel(){
        return 1;
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level){
        user.world.addWeatherEffect(new EntityLightningBolt(user.world, target.posX, target.posY, target.posZ, false));
    }
}