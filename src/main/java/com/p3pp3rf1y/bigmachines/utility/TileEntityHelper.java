package com.p3pp3rf1y.bigmachines.utility;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class TileEntityHelper {
    public static TileEntity getTileEntityUnsafe(IBlockAccess iba, int x, int y, int z) {
        TileEntity te = null;

        if(iba instanceof World) {
            // We don't want to trigger tile entity loads in this method
            te = getTileEntityUnsafe((World)iba, x, y, z);
        }
        else {
            // Should never happen, generally
            te = iba.getTileEntity(x, y, z);
        }

        return te;
    }

    public static TileEntity getTileEntityUnsafe(World world, int x, int y, int z) {
        TileEntity te = null;

        Chunk chunk = world.getChunkFromBlockCoords(x, z);
        if(chunk != null) {
            te = chunk.getTileEntityUnsafe(x & 0x0F, y, z & 0x0F);
        }

        return te;
    }
}
