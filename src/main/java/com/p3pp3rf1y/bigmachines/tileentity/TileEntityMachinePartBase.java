package com.p3pp3rf1y.bigmachines.tileentity;

import com.p3pp3rf1y.beefcore.common.CoordTriplet;
import com.p3pp3rf1y.beefcore.multiblock.MultiblockControllerBase;
import com.p3pp3rf1y.beefcore.multiblock.rectangular.RectangularMultiblockTileEntityBase;
import com.p3pp3rf1y.bigmachines.multiblock.MultiblockMachine;
import com.p3pp3rf1y.bigmachines.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;

public abstract class TileEntityMachinePartBase extends RectangularMultiblockTileEntityBase implements IMultiblockGuiHandler,
        IActivateable, IBeefDebuggableTile {

    @Override
    public MultiblockControllerBase createNewMultiblock() {
        return new MultiblockMachine(worldObj);
    }

    @Override
    public Class<? extends MultiblockControllerBase> getMultiblockControllerType() {
        return MultiblockMachine.class;
    }

    @Override
    public void onMachineAssembled(MultiblockControllerBase controller) {
        super.onMachineAssembled(controller);

        // Re-render this block on the client
        if(worldObj.isRemote) {
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public void onMachineBroken() {
        super.onMachineBroken();

        // Re-render this block on the client
        if(worldObj.isRemote) {
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public void onMachineActivated() {
    }

    @Override
    public void onMachineDeactivated() {
    }

    /// GUI Support - IMultiblockGuiHandler
    /**
     * @return The Container object for use by the GUI. Null if there isn't any.
     */
    @Override
    public Object getContainer(InventoryPlayer inventoryPlayer) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Object getGuiElement(InventoryPlayer inventoryPlayer) {
        return null;
    }

    public MultiblockMachine getMachine() {
        return (MultiblockMachine)getMultiblockController();
    }

    // IActivateable
    @Override
    public CoordTriplet getReferenceCoord() {
        if(isConnected()) {
            return getMultiblockController().getReferenceCoord();
        }
        else {
            return new CoordTriplet(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public boolean getActive() {
        if(isConnected()) {
            return getMachine().getActive();
        }
        else {
            return false;
        }
    }

    @Override
    public void setActive(boolean active) {
        if(isConnected()) {
            getMachine().setActive(active);
        }
        else {
            LogHelper.error("Received a setActive command at %d, %d, %d, but not connected to a multiblock controller!", xCoord, yCoord, zCoord);
        }
    }

    @Override
    public String getDebugInfo() {
        MultiblockMachine t = getMachine();
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().toString()).append("\n");
        if(t == null) {
            sb.append("Not attached to controller!");
            return sb.toString();
        }
        sb.append(t.getDebugInfo());
        return sb.toString();
    }
}
