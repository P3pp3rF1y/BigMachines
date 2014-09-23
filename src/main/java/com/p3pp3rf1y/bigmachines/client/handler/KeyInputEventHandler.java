package com.p3pp3rf1y.bigmachines.client.handler;

import com.p3pp3rf1y.bigmachines.client.settings.KeyBindings;
import com.p3pp3rf1y.bigmachines.reference.Key;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler
{
    private static Key getPressedKeyBinding()
    {
        if (KeyBindings.empower.isPressed())
        {
            return Key.EMPOWER;
        }
        return Key.UNKNOWN;
    }


    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {

    }
}
