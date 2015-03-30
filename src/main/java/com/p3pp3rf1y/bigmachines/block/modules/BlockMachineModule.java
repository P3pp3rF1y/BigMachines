package com.p3pp3rf1y.bigmachines.block.modules;

import cofh.core.render.IconRegistry;
import com.p3pp3rf1y.bigmachines.block.BlockBigMachines;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockMachineModule extends BlockBigMachines
{
    public BlockMachineModule() {
        super(Material.iron);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 3) {
            return IconRegistry.getIcon("ModulePulverizer");
        }

        return IconRegistry.getIcon("ModuleSide");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        IconRegistry.addIcon("ModuleSide", "bigmachines:ModuleSide", iconRegister);
        IconRegistry.addIcon("ModulePulverizer", "bigmachines:ModulePulverizer", iconRegister );
    }

}
