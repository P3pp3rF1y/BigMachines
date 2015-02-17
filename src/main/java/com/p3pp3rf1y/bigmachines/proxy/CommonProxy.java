package com.p3pp3rf1y.bigmachines.proxy;

import com.p3pp3rf1y.beefcore.multiblock.MultiblockServerTickHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public abstract class CommonProxy implements IProxy
{
    @Override
    public void registerTickHandlers()
    {
        FMLCommonHandler.instance().bus().register(new MultiblockServerTickHandler());
    }
}
