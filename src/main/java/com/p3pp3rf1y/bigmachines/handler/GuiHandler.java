package com.p3pp3rf1y.bigmachines.handler;

import com.p3pp3rf1y.bigmachines.client.gui.inventory.GuiFurnaceModule;
import com.p3pp3rf1y.bigmachines.inventory.ContainerFurnaceModule;
import com.p3pp3rf1y.bigmachines.reference.GUIs;
import com.p3pp3rf1y.bigmachines.tileentity.modules.TileEntityFurnaceModule;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GUIs.FURNACE_MODULE.ordinal())
        {
            TileEntityFurnaceModule tileEntityFurnaceModule = (TileEntityFurnaceModule) world.getTileEntity(x, y, z);
            return new ContainerFurnaceModule(entityPlayer.inventory, tileEntityFurnaceModule);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GUIs.FURNACE_MODULE.ordinal())
        {
            TileEntityFurnaceModule tileEntityFurnaceModule = (TileEntityFurnaceModule) world.getTileEntity(x, y, z);
            return new GuiFurnaceModule(entityPlayer.inventory, tileEntityFurnaceModule);
        }
        return null;
    }
}