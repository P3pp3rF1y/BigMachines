package com.p3pp3rf1y.bigmachines.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(ModItems.wrench), "A A", "AAA", " A ", 'A', new ItemStack(Items.iron_ingot));
    }
}
