package com.p3pp3rf1y.bigmachines.block;

import com.p3pp3rf1y.bigmachines.creativetab.CreativeTabBigMachines;
import com.p3pp3rf1y.bigmachines.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockBigMachines extends Block
{

    public BlockBigMachines(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabBigMachines.BIG_THINGS_TAB);
    }

    public BlockBigMachines()
    {
        this(Material.iron);
    }

/*    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s:%s", Reference.MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(this.getUnlocalizedName()));
    }*/

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
