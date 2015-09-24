package com.p3pp3rf1y.bigmachines.tileentity;

import com.p3pp3rf1y.beefcore.multiblock.MultiblockValidationException;
import com.p3pp3rf1y.bigmachines.block.BlockMachinePartFrame;
import com.p3pp3rf1y.bigmachines.client.gui.inventory.GuiFurnaceModule;
import com.p3pp3rf1y.bigmachines.inventory.ContainerBigMachines;
import com.p3pp3rf1y.bigmachines.inventory.ContainerFurnaceModule;
import com.p3pp3rf1y.bigmachines.tileentity.modules.TileEntityFurnaceModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class TileEntityMachinePartFrame extends TileEntityMachinePartBase {

    public TileEntityMachinePartFrame() {
        super();
    }

    @Override
    public void isGoodForFrame() throws MultiblockValidationException {
        if(getBlockMetadata() != BlockMachinePartFrame.METADATA_HOUSING) {
            throw new MultiblockValidationException(String.format("%d, %d, %d - only machine frame may be used as part of the machine's frame", xCoord, yCoord, zCoord));
        }
    }

    @Override
    public void isGoodForSides() {
    }

    @Override
    public void isGoodForTop() {
    }

    @Override
    public void isGoodForBottom() {
    }

    @Override
    public void isGoodForInterior() throws MultiblockValidationException {
        if(getBlockMetadata() != BlockMachinePartFrame.METADATA_HOUSING) {
            throw new MultiblockValidationException(String.format("%d, %d, %d - this part is not valid for the interior of a machine", xCoord, yCoord, zCoord));
        }
    }

    @Override
    public Object getContainer(InventoryPlayer inventoryPlayer) {
        if(!this.isConnected()) {
            return null;
        }

        if(getBlockMetadata() == BlockMachinePartFrame.METADATA_CONTROLLER) {
            return new ContainerFurnaceModule(inventoryPlayer, new TileEntityFurnaceModule());
        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Object getGuiElement(InventoryPlayer inventoryPlayer) {
        if(!this.isConnected()) {
            return null;
        }

        if(getBlockMetadata() == BlockMachinePartFrame.METADATA_CONTROLLER) {
            return new GuiFurnaceModule(inventoryPlayer, new TileEntityFurnaceModule());
        }
        return null;
    }

    //TODO:figure out if these are needed as machine will likely not have on/off switch but rather activate when it has power and something to process

    @Override
    public void onMachineActivated() {
        // Re-render controller as active state has changed
        if(worldObj.isRemote && getBlockMetadata() == BlockMachinePartFrame.METADATA_CONTROLLER) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public void onMachineDeactivated() {
        // Re-render controller as active state has changed
        if(worldObj.isRemote && getBlockMetadata() == BlockMachinePartFrame.METADATA_CONTROLLER) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }
}
