package com.p3pp3rf1y.bigmachines.proxy;

import com.p3pp3rf1y.bigmachines.client.settings.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.empower);
    }
}
