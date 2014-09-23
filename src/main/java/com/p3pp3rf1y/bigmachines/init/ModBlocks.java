package com.p3pp3rf1y.bigmachines.init;

import com.p3pp3rf1y.bigmachines.block.BlockBigMachines;
import com.p3pp3rf1y.bigmachines.block.BlockPulverizerModule;
import com.p3pp3rf1y.bigmachines.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockBigMachines pulverizerModule = new BlockPulverizerModule();

    public static void init()
    {
        GameRegistry.registerBlock(pulverizerModule, "pulverizerModule");


    }
}
