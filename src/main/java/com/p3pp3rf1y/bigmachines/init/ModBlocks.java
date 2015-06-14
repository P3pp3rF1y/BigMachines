package com.p3pp3rf1y.bigmachines.init;

import com.p3pp3rf1y.bigmachines.block.BlockContainerBigMachines;
import com.p3pp3rf1y.bigmachines.block.BlockMachinePartFrame;
import com.p3pp3rf1y.bigmachines.block.modules.BlockFurnaceModule;
import com.p3pp3rf1y.bigmachines.item.ItemBlockBigMachines;
import com.p3pp3rf1y.bigmachines.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockContainerBigMachines furnaceModule = new BlockFurnaceModule();
    public static final BlockMachinePartFrame machinePart = new BlockMachinePartFrame(Material.iron);


    public static void init()
    {
        GameRegistry.registerBlock(furnaceModule, "furnaceModule");
        GameRegistry.registerBlock(machinePart, ItemBlockBigMachines.class, "machinePart");
    }
}
