package com.p3pp3rf1y.bigmachines.multiblock;

import com.p3pp3rf1y.beefcore.common.CoordTriplet;
import com.p3pp3rf1y.beefcore.multiblock.IMultiblockPart;
import com.p3pp3rf1y.beefcore.multiblock.MultiblockControllerBase;
import com.p3pp3rf1y.beefcore.multiblock.MultiblockValidationException;
import com.p3pp3rf1y.beefcore.multiblock.rectangular.RectangularMultiblockControllerBase;
import com.p3pp3rf1y.bigmachines.tileentity.IActivateable;
import com.p3pp3rf1y.bigmachines.utility.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class MultiblockMachine extends RectangularMultiblockControllerBase implements IActivateable {

    public MultiblockMachine(World world) {
        super(world);
    }

    @Override
    public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data) {
        readFromNBT(data);
    }

    @Override
    protected void onBlockAdded(IMultiblockPart newPart) {
//  Example
//  -------
//        if(newPart instanceof TileEntityTurbineRotorBearing) {
//            this.attachedRotorBearings.add((TileEntityTurbineRotorBearing)newPart);
//        }
//
//        if(newPart instanceof TileEntityTurbinePowerTap) {
//            attachedPowerTaps.add((TileEntityTurbinePowerTap)newPart);
//        }
//
//        if(newPart instanceof ITickableMultiblockPart) {
//            attachedTickables.add((ITickableMultiblockPart)newPart);
//        }
//
//        if(newPart instanceof TileEntityTurbineRotorPart) {
//            TileEntityTurbineRotorPart turbinePart = (TileEntityTurbineRotorPart)newPart;
//            if(turbinePart.isRotorShaft()) {
//                attachedRotorShafts.add(turbinePart);
//            }
//
//            if(turbinePart.isRotorBlade()) {
//                attachedRotorBlades.add(turbinePart);
//            }
//        }
//
//        if(newPart instanceof TileEntityTurbinePartGlass) {
//            attachedGlass.add((TileEntityTurbinePartGlass)newPart);
//        }
    }

    @Override
    protected void onBlockRemoved(IMultiblockPart oldPart) {
//  Example
//  -------
//        if(oldPart instanceof TileEntityTurbineRotorBearing) {
//            this.attachedRotorBearings.remove(oldPart);
//        }
//
//        if(oldPart instanceof TileEntityTurbinePowerTap) {
//            attachedPowerTaps.remove((TileEntityTurbinePowerTap)oldPart);
//        }
//
//        if(oldPart instanceof ITickableMultiblockPart) {
//            attachedTickables.remove((ITickableMultiblockPart)oldPart);
//        }
//
//        if(oldPart instanceof TileEntityTurbineRotorPart) {
//            TileEntityTurbineRotorPart turbinePart = (TileEntityTurbineRotorPart)oldPart;
//            if(turbinePart.isRotorShaft()) {
//                attachedRotorShafts.remove(turbinePart);
//            }
//
//            if(turbinePart.isRotorBlade()) {
//                attachedRotorBlades.remove(turbinePart);
//            }
//        }
//
//        if(oldPart instanceof TileEntityTurbinePartGlass) {
//            attachedGlass.remove((TileEntityTurbinePartGlass)oldPart);
//        }
    }

    @Override
    protected void onMachineAssembled() {
        recalculateMachineStats();
    }

    @Override
    protected void onMachineRestored() {
        recalculateMachineStats();
    }

    @Override
    protected void onMachinePaused() {

    }

    @Override
    protected void onMachineDisassembled() {
        //TODO: add code to reset values to 0
    }

    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine() {
        // 3*3*3 housing with a single machine module inside
        return 27;
    }

    @Override
    protected int getMaximumXSize() {
        return 0;
    }

    @Override
    protected int getMaximumZSize() {
        return 0;
    }

    @Override
    protected int getMaximumYSize() {
        return 0;
    }

    @Override
    protected int getMinimumXSize() { return 3; }

    @Override
    protected int getMinimumYSize() { return 3; }

    @Override
    protected int getMinimumZSize() { return 3; }

    @Override
    protected void onAssimilate(MultiblockControllerBase assimilated) {
        if(!(assimilated instanceof MultiblockMachine)) {
            LogHelper.warn("[%s] Machine @ %s is attempting to assimilate an invalid machine! That machine's data will be lost!", worldObj.isRemote ? "CLIENT" : "SERVER", getReferenceCoord());
            return;
        }
    }

    @Override
    protected void onAssimilated(MultiblockControllerBase assimilator) {
        //TODO: add code to clear collections of machine parts
    }

    @Override
    protected boolean updateServer() {
        //TODO: add code that will run module logic using machine stats and settings

        return false;
    }

    @Override
    protected void updateClient() {

    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        //TODO: add code to store storage / processing status
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        //TODO: add code to load storage / processing status
    }

    @Override
    public void formatDescriptionPacket(NBTTagCompound data) {
        writeToNBT(data);
    }

    @Override
    public void decodeDescriptionPacket(NBTTagCompound data) {
        readFromNBT(data);
    }

    private void recalculateMachineStats() {
        //TODO: implement
    }

    // Network Serialization
    /**
     * Used when dispatching update packets from the server.
     * @param buf ByteBuf into which the machine's full status should be written
     */
    public void serialize(ByteBuf buf) {
        //TODO: add code to serialize and MachineUpdateMessage that will use the data
    }

    /**
     * Used when a status packet arrives on the client.
     * @param buf ByteBuf containing serialized machine data
     */
    public void deserialize(ByteBuf buf) {
        //TODO: add code to deserialize
    }

    @Override
    public boolean getActive() {
        //TODO: implement once redstone module is implemented
        return true;
    }

    @Override
    public void setActive(boolean active) {
        //TODO: implement once redstone module is implemented
    }

    public String getDebugInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Assembled: ").append(Boolean.toString(isAssembled())).append("\n");
        sb.append("Attached Blocks: ").append(Integer.toString(connectedParts.size())).append("\n");
        if(getLastValidationException() != null) {
            sb.append("Validation Exception:\n").append(getLastValidationException().getMessage()).append("\n");
        }

        if(isAssembled()) {
            sb.append("\nActive: ").append(Boolean.toString(getActive()));
        }

        //TODO: add generic machine values when implemented
        //TODO: add call to machine modules to get their stats

        return sb.toString();
    }

    @Override
    protected void isBlockGoodForInterior(World world, int x, int y, int z) throws MultiblockValidationException {
        // Air is ok
        if(world.isAirBlock(x, y, z)) { return; }

        // Everything else, gtfo
        throw new MultiblockValidationException(String.format("%d, %d, %d is invalid for a machine interior. Only rotor parts, metal blocks and empty space are allowed.", x, y, z));
    }

}


