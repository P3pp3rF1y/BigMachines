package com.p3pp3rf1y.bigmachines.block;

import com.p3pp3rf1y.bigmachines.BigMachines;
import com.p3pp3rf1y.bigmachines.creativetab.CreativeTabBigMachines;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by jjanecek on 12/12/2014.
 */
public class BlockMachineFramePart extends BlockBigMachines {

    public BlockMachineFramePart(Material material) {
        super(material);

        setStepSound(soundTypeMetal);
        setHardness(2.0f);
        setBlockName("blockMachineFramePart");
        this.setBlockTextureName(Textures.TEXTURE_NAME_PREFIX + "blockMachineFramePart");
        setCreativeTab(CreativeTabBigMachines.BIG_THINGS_TAB);
    }
}
