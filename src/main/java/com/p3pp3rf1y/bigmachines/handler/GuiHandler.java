package com.p3pp3rf1y.bigmachines.handler;

import com.p3pp3rf1y.bigmachines.client.gui.inventory.GuiFurnaceModule;
import com.p3pp3rf1y.bigmachines.inventory.ContainerFurnaceModule;
import com.p3pp3rf1y.bigmachines.reference.GUIs;
import com.p3pp3rf1y.bigmachines.tileentity.IMultiblockGuiHandler;
import com.p3pp3rf1y.bigmachines.tileentity.modules.TileEntityFurnaceModule;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {

        TileEntity te = world.getTileEntity(x, y, z);
        if(te == null) {
            return null;
        }
        else if(te instanceof IMultiblockGuiHandler) {
            return ((IMultiblockGuiHandler)te).getContainer(player.inventory);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te == null) {
            return null;
        }

        if(te instanceof IMultiblockGuiHandler) {
            IMultiblockGuiHandler part = (IMultiblockGuiHandler)te;
            return part.getGuiElement(player.inventory);
        }

        return null;
    }
}