package com.p3pp3rf1y.bigthings;

import com.p3pp3rf1y.bigthings.handler.ConfigurationHandler;
import com.p3pp3rf1y.bigthings.proxy.IProxy;
import com.p3pp3rf1y.bigthings.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid= Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class BigThings {
    @Mod.Instance(Reference.MOD_ID)
    public static BigThings instance;

    @SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
