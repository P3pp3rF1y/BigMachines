package com.p3pp3rf1y.bigmachines.init;

import com.p3pp3rf1y.bigmachines.block.BlockBigMachines;
import com.p3pp3rf1y.bigmachines.block.BlockPulverizerModule;
import com.p3pp3rf1y.bigmachines.tileentity.TileEntityBigMachines;
import com.p3pp3rf1y.bigmachines.tileentity.TileEntityFurnaceModule;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityFurnaceModule.class, "tileEntityFurnaceModule");
    }

}
