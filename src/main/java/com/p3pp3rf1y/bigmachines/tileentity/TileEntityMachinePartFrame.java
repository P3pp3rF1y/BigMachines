package com.p3pp3rf1y.bigmachines.tileentity;

import com.p3pp3rf1y.beefcore.multiblock.MultiblockValidationException;
import com.p3pp3rf1y.bigmachines.block.BlockMachinePartFrame;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;

public class TileEntityMachinePartFrame extends TileEntityMachinePartBase {

    public TileEntityMachinePartFrame() {
        super();
    }

    @Override
    public void isGoodForFrame() throws MultiblockValidationException {
        if(getBlockMetadata() != BlockMachinePartFrame.METADATA_HOUSING) {
            throw new MultiblockValidationException(String.format("%d, %d, %d - only turbine housing may be used as part of the turbine's frame", xCoord, yCoord, zCoord));
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
            throw new MultiblockValidationException(String.format("%d, %d, %d - this part is not valid for the interior of a turbine", xCoord, yCoord, zCoord));
        }
    }

    @Override
    public Object getContainer(InventoryPlayer inventoryPlayer) {
        if(!this.isConnected()) {
            return null;
        }

//TODO: add return of correct container when controller implemented, may not be slotless
//        if(getBlockMetadata() == BlockMachineFramePart.METADATA_CONTROLLER) {
//            return (Object)(new ContainerSlotless(getMachine(), inventoryPlayer.player));
//        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Object getGuiElement(InventoryPlayer inventoryPlayer) {
        if(!this.isConnected()) {
            return null;
        }

//TODO: add controller Gui when implemented
//        if(getBlockMetadata() == BlockTurbinePart.METADATA_CONTROLLER) {
//            return new GuiTurbineController((Container)getContainer(inventoryPlayer), this);
//        }
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
