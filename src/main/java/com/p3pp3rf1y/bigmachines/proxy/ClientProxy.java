package com.p3pp3rf1y.bigmachines.proxy;

import com.p3pp3rf1y.beefcore.multiblock.MultiblockClientTickHandler;
import com.p3pp3rf1y.bigmachines.client.settings.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.empower);
    }

    @Override
    public void registerTickHandlers()
    {
        super.registerTickHandlers();

        FMLCommonHandler.instance().bus().register(new MultiblockClientTickHandler());
    }
}
