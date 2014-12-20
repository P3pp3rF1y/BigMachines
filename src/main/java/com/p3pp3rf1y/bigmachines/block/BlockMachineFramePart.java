package com.p3pp3rf1y.bigmachines.block;

import com.p3pp3rf1y.bigmachines.creativetab.CreativeTabBigMachines;
import com.p3pp3rf1y.bigmachines.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by jjanecek on 12/12/2014.
 */
public class BlockMachineFramePart extends BlockBigMachines {

    public BlockMachineFramePart(Material material) {
        super(material);

        setStepSound(soundTypeMetal);
        setHardness(2.0f);
        setBlockName("blockMachineFramePart");
        this.setBlockTextureName(Textures.RESOURCE_PREFIX + "blockMachineFramePart");
        setCreativeTab(CreativeTabBigMachines.BIG_MACHINES_TAB);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        // Base icons
        for(int i = 0; i < _subBlocks.length; ++i) {
            _icons[i] = par1IconRegister.registerIcon(BigReactors.TEXTURE_NAME_PREFIX + getUnlocalizedName() + "." + _subBlocks[i]);
        }

        for(int i = 0; i < _subIcons.length; i++) {
            _subIcons[i] = par1IconRegister.registerIcon(BigReactors.TEXTURE_NAME_PREFIX + getUnlocalizedName() + "." + _subIconNames[i]);
        }

        this.blockIcon = _icons[0];
    }}
