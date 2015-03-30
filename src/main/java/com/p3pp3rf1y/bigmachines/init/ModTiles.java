package com.p3pp3rf1y.bigmachines.init;

import com.p3pp3rf1y.bigmachines.tileentity.modules.TileEntityFurnaceModule;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityFurnaceModule.class, "tileEntityFurnaceModule");
    }

}
