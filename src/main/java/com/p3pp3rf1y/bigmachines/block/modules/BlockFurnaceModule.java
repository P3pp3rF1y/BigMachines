package com.p3pp3rf1y.bigmachines.block.modules;

import com.p3pp3rf1y.bigmachines.init.ModBlocks;
import com.p3pp3rf1y.bigmachines.tileentity.modules.TileEntityFurnaceModule;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFurnaceModule extends BlockMachineModule implements ITileEntityProvider
{
    private static boolean updatingBlock;

    public BlockFurnaceModule() {
        super();
        this.setBlockName("furnaceModule");
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.furnaceModule);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityFurnaceModule();
    }

    @Override
    public void breakBlock (World world, int x, int y, int z, Block block, int meta)
    {
        if(hasTileEntity(meta))
        {
            world.removeTileEntity(x, y, z);
        }

    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventData)
    {
        super.onBlockEventReceived(world, x, y, z, eventId, eventData);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(eventId, eventData) : false;

    }
}
