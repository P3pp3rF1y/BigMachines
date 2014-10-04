package com.p3pp3rf1y.bigmachines.block;

import com.p3pp3rf1y.bigmachines.BigMachines;
import com.p3pp3rf1y.bigmachines.init.ModBlocks;
import com.p3pp3rf1y.bigmachines.reference.GUIs;
import com.p3pp3rf1y.bigmachines.tileentity.TileEntityFurnaceModule;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFurnaceModule extends BlockMachineModule implements ITileEntityProvider
{
    private static boolean updatingBlock;

    public BlockFurnaceModule() {
        super();
        this.setBlockName("furnaceModule");
    }

    //TODO: possibly move to BlockMachineModule
    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.furnaceModule);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityFurnaceModule();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getTileEntity(xCoord, yCoord, zCoord) instanceof TileEntityFurnaceModule)
                {
                    player.openGui(BigMachines.instance, GUIs.FURNACE_MODULE.ordinal(), world, xCoord, yCoord, zCoord);
                }
            }

            return true;
        }
    }

    //TODO: implement block break

}
