package com.p3pp3rf1y.bigmachines;

import com.p3pp3rf1y.beefcore.multiblock.MultiblockClientTickHandler;
import com.p3pp3rf1y.beefcore.multiblock.MultiblockEventHandler;
import com.p3pp3rf1y.beefcore.multiblock.MultiblockServerTickHandler;
import com.p3pp3rf1y.bigmachines.client.handler.KeyInputEventHandler;
import com.p3pp3rf1y.bigmachines.handler.ConfigurationHandler;
import com.p3pp3rf1y.bigmachines.handler.GuiHandler;
import com.p3pp3rf1y.bigmachines.init.ModBlocks;
import com.p3pp3rf1y.bigmachines.init.ModItems;
import com.p3pp3rf1y.bigmachines.init.ModTiles;
import com.p3pp3rf1y.bigmachines.init.Recipes;
import com.p3pp3rf1y.bigmachines.proxy.IProxy;
import com.p3pp3rf1y.bigmachines.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

//TODO: add pulverizer block texture / test
//TODO: test creative tab including it's translated name
//TODO: test wrench recipe
//TODO: test key binding

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class BigMachines
{
    @Mod.Instance(Reference.MOD_ID)
    public static BigMachines instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    private MultiblockEventHandler multiblockEventHandler;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler()); //TODO: should this be here - take a look at EE3 cod

        multiblockEventHandler = new MultiblockEventHandler();
        MinecraftForge.EVENT_BUS.register(multiblockEventHandler);

        proxy.registerKeyBindings();

        ModItems.init();

        ModBlocks.init();

        ModTiles.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        proxy.registerTickHandlers();

        Recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
