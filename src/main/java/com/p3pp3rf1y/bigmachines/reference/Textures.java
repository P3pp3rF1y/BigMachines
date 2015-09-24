package com.p3pp3rf1y.bigmachines.reference;

import com.p3pp3rf1y.bigmachines.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class Gui
    {
        private static final String GUI_SHEET_LOCATION = "textures/gui/";
        public static final ResourceLocation FURNACE_MODULE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "furnaceModule.png");
        public static final ResourceLocation CONTROLLER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "controller.png");
    }
}
