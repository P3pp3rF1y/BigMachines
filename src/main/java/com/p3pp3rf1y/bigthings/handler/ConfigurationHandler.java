package com.p3pp3rf1y.bigthings.handler;

import com.p3pp3rf1y.bigthings.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by jjanecek on 8/13/2014.
 */
public class ConfigurationHandler {

    public static Configuration configuration;
    public static double machineSpeed;

    public static void init(File configFile) {

        if (configuration == null) {
            configuration = new Configuration(configFile);
        }
    }

    @SubscribeEvent
    public void onConfigurationChanged(ConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }

    public void loadConfiguration() {

        machineSpeed = configuration.get(Configuration.CATEGORY_GENERAL, "machineSpeed", 1.0, "machine speed multiplier").getDouble(1.0);

        if (configuration.hasChanged()){
            configuration.save();
        }
    }
}
