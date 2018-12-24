package xen.modone.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import xen.modone.ModOne;
import xen.modone.blocks.machines.ContainerMemeProcessor;
import xen.modone.blocks.machines.GuiMemeProcessor;
import xen.modone.blocks.tileentity.TileEntityMemeProcessor;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == ModOne.guiMemeProcessor) return new ContainerMemeProcessor(player.inventory, (TileEntityMemeProcessor)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == ModOne.guiMemeProcessor) return new GuiMemeProcessor(player.inventory, (TileEntityMemeProcessor)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}
