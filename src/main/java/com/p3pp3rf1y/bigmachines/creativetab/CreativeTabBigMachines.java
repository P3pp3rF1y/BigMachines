package com.p3pp3rf1y.bigmachines.creativetab;

import com.p3pp3rf1y.bigmachines.init.ModItems;
import com.p3pp3rf1y.bigmachines.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabBigMachines
{
    public static final CreativeTabs BIG_THINGS_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.wrench;
        }
    };
}
