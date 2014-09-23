package com.p3pp3rf1y.bigmachines.handler;

import com.p3pp3rf1y.bigmachines.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{

    public static Configuration configuration;
    public static double machineSpeed;

    public static void init(File configFile)
    {

        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChanged(ConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {

        machineSpeed = configuration.get(Configuration.CATEGORY_GENERAL, "machineSpeed", 1.0, "machine speed multiplier").getDouble(1.0);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
