package com.p3pp3rf1y.bigmachines.utility;

import com.p3pp3rf1y.bigmachines.reference.Reference;
import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class LogHelper
{
    public static void log(Level logLevel, String format, Object... object) {
        FMLLog.log(Reference.MOD_NAME, logLevel, format, object);
    }

    public static void all(String format, Object... object)
    {
        log(Level.ALL, format, object);
    }

    public static void debug(String format, Object... object)
    {
        log(Level.DEBUG, format, object);
    }

    public static void error(String format, Object... object)
    {
        log(Level.ERROR, format, object);
    }

    public static void fatal(String format, Object... object)
    {
        log(Level.FATAL, format, object);
    }

    public static void info(String format, Object... object)
    {
        log(Level.INFO, format, object);
    }

    public static void off(String format, Object... object)
    {
        log(Level.OFF, format, object);
    }

    public static void trace(String format, Object... object)
    {
        log(Level.TRACE, format, object);
    }

    public static void warn(String format, Object... object)
    {
        log(Level.WARN, format, object);
    }
}
