package xen.modone.blocks.machines.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xen.modone.blocks.tileentity.TileEntityMemeProcessor;

public class SlotMemeProcessorFuel extends Slot {

    public SlotMemeProcessorFuel(IInventory inventory, int index, int x, int y){
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return TileEntityMemeProcessor.isItemFuel(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return super.getItemStackLimit(stack);
    }
}
