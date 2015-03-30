package com.p3pp3rf1y.bigmachines.tileentity.modules;

import com.p3pp3rf1y.beefcore.multiblock.MultiblockValidationException;
import com.p3pp3rf1y.bigmachines.tileentity.TileEntityMachinePartBase;

public class TileEntityMachinePartInternal extends TileEntityMachinePartBase {
    @Override
    public void isGoodForFrame() throws MultiblockValidationException {
        throw new MultiblockValidationException("Rotor parts may only be placed in the turbine interior");
    }

    @Override
    public void isGoodForSides() throws MultiblockValidationException {
        throw new MultiblockValidationException("Rotor parts may only be placed in the turbine interior");
    }

    @Override
    public void isGoodForTop() throws MultiblockValidationException {
        throw new MultiblockValidationException("Rotor parts may only be placed in the turbine interior");
    }

    @Override
    public void isGoodForBottom() throws MultiblockValidationException {
        throw new MultiblockValidationException("Rotor parts may only be placed in the turbine interior");
    }

    @Override
    public void isGoodForInterior() throws MultiblockValidationException {
    }
}
