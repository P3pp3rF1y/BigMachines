package com.p3pp3rf1y.bigmachines.init;

import com.p3pp3rf1y.bigmachines.item.ItemBigMachines;
import com.p3pp3rf1y.bigmachines.item.Wrench;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
    public static final ItemBigMachines wrench = new Wrench();

    public static void init()
    {
        GameRegistry.registerItem(wrench, "wrench");
    }
}
