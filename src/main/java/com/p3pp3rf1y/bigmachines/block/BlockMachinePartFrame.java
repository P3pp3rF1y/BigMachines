package com.p3pp3rf1y.bigmachines.block;

import com.p3pp3rf1y.beefcore.common.CoordTriplet;
import com.p3pp3rf1y.beefcore.multiblock.IMultiblockPart;
import com.p3pp3rf1y.beefcore.multiblock.MultiblockControllerBase;
import com.p3pp3rf1y.bigmachines.BigMachines;
import com.p3pp3rf1y.bigmachines.creativetab.CreativeTabBigMachines;
import com.p3pp3rf1y.bigmachines.multiblock.MultiblockMachine;
import com.p3pp3rf1y.bigmachines.reference.GUIs;
import com.p3pp3rf1y.bigmachines.reference.Textures;
import com.p3pp3rf1y.bigmachines.tileentity.TileEntityMachinePartBase;
import com.p3pp3rf1y.bigmachines.tileentity.TileEntityMachinePartFrame;
import com.p3pp3rf1y.bigmachines.utility.DirectionHelper;
import com.p3pp3rf1y.bigmachines.utility.TileEntityHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

//TODO: come up with better name than Frame
public class BlockMachinePartFrame extends BlockContainerBigMachines {

    public static final int METADATA_HOUSING = 0;
    public static final int METADATA_CONTROLLER = 1;

    private static final int SUBICON_NONE = -1;
    private static final int SUBICON_HOUSING_FRAME_TOP = 0;
    private static final int SUBICON_HOUSING_FRAME_BOTTOM = 1;
    private static final int SUBICON_HOUSING_FRAME_LEFT = 2;
    private static final int SUBICON_HOUSING_FRAME_RIGHT = 3;
    private static final int SUBICON_HOUSING_FACE = 4;
    private static final int SUBICON_HOUSING_CORNER = 5;
    private static final int SUBICON_CONTROLLER_IDLE = 6;
    private static final int SUBICON_CONTROLLER_ACTIVE = 7;

    private static final String[] _subBlocks = new String[] { "housing", "controller" };

    //TODO: add textures
    private static final String[] _subIconNames = new String[] {
            "housing.edge.0",
            "housing.edge.1",
            "housing.edge.2",
            "housing.edge.3",
            "housing.face",
            "housing.corner",
            "controller.idle",
            "controller.active",
    };

    public static boolean isHousing(int metadata) { return metadata == METADATA_HOUSING; }
    public static boolean isController(int metadata) { return metadata == METADATA_CONTROLLER; }

    private IIcon[] _icons = new IIcon[_subBlocks.length];
    private IIcon[] _subIcons = new IIcon[_subIconNames.length];

    public BlockMachinePartFrame(Material material) {
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
            _icons[i] = par1IconRegister.registerIcon(String.format("%s.%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()), _subBlocks[i]));
        }

        for(int i = 0; i < _subIcons.length; i++) {
            _subIcons[i] = par1IconRegister.registerIcon(String.format("%s.%s",  getUnwrappedUnlocalizedName(this.getUnlocalizedName()), _subIconNames[i]));
        }

        this.blockIcon = _icons[0];
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntity te = blockAccess.getTileEntity(x, y, z);
        int metadata = blockAccess.getBlockMetadata(x,y,z);

        if(te instanceof TileEntityMachinePartBase) {
            TileEntityMachinePartBase part = (TileEntityMachinePartBase)te;
            MultiblockMachine machine = part.getMachine();

            if(!part.isConnected() || machine == null || !machine.isAssembled()) {
                return getIcon(side, metadata);
            }
            else {
                int subIcon = SUBICON_NONE;
                if(metadata == METADATA_HOUSING) {
                    subIcon = getSubIconForHousing(blockAccess, x, y, z, machine, side);
                }
                else if(part.getOutwardsDir().ordinal() == side) {
                    // Only put the fancy icon on one side of the machine. Other parts will use the base.
                    if(metadata == METADATA_CONTROLLER) {
                        if(machine.getActive()) {
                            subIcon = SUBICON_CONTROLLER_ACTIVE;
                        }
                        else {
                            subIcon = SUBICON_CONTROLLER_IDLE;
                        }
                    }
//TODO:finish when additional parts implemented
//                    else if(metadata == METADATA_POWERTAP) {
//                        if(te instanceof TileEntityTurbinePowerTap && ((TileEntityTurbinePowerTap)te).isAttachedToPowerNetwork()) {
//                            subIcon = SUBICON_POWERTAP_ACTIVE;
//                        }
//                    }
                }
                else {
                    // Assembled non-housing parts use the face texture so it's all smooth on the inside
                    subIcon = SUBICON_HOUSING_FACE;
                }

                if(subIcon == SUBICON_NONE) {
                    return getIcon(side, metadata);
                }
                else {
                    return _subIcons[subIcon];
                }
            }
        }

        // Not a "proper" TE, so just pass through
        return getIcon(side, metadata);
    }

    private int getSubIconForHousing(IBlockAccess blockAccess, int x, int y, int z, MultiblockMachine machine, int side) {
        CoordTriplet minCoord, maxCoord;
        minCoord = machine.getMinimumCoord();
        maxCoord = machine.getMaximumCoord();

        if(minCoord == null || maxCoord == null) {
            return SUBICON_NONE;
        }

        int extremes = 0;
        boolean xExtreme, yExtreme, zExtreme;
        xExtreme = yExtreme = zExtreme = false;

        if(x == minCoord.x) { extremes++; xExtreme = true; }
        if(y == minCoord.y) { extremes++; yExtreme = true; }
        if(z == minCoord.z) { extremes++; zExtreme = true; }

        if(x == maxCoord.x) { extremes++; xExtreme = true; }
        if(y == maxCoord.y) { extremes++; yExtreme = true; }
        if(z == maxCoord.z) { extremes++; zExtreme = true; }

        if(extremes >= 3) {
            return SUBICON_HOUSING_CORNER;
        }
        else if(extremes <= 0) {
            return SUBICON_NONE;
        }
        else if(extremes == 1) {
            return SUBICON_HOUSING_FACE;
        }
        else {
            ForgeDirection[] dirsToCheck = DirectionHelper.neighborsBySide[side];
            ForgeDirection dir;

            Block myBlock = blockAccess.getBlock(x, y, z);
            int iconIdx = -1;

            for(int i = 0; i < dirsToCheck.length; i++) {
                dir = dirsToCheck[i];

                Block neighborBlock = blockAccess.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
                // See if we're a machine part
                if(neighborBlock != myBlock) {
                    // One of these things is not like the others...
                    iconIdx = i;
                    break;
                }
            }

            return iconIdx + SUBICON_HOUSING_FRAME_TOP;
        }
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        metadata = Math.max(0, Math.min(metadata, _subBlocks.length-1));
        return _icons[metadata];
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityMachinePartFrame();
    }
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        TileEntity te = TileEntityHelper.getTileEntityUnsafe(world, x, y, z);

        // Signal power taps when their neighbors change, etc.
        if(te instanceof INeighborUpdatableEntity) {
            ((INeighborUpdatableEntity)te).onNeighborBlockChange(world, x, y, z, neighborBlock);
        }
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int neighborX, int neighborY, int neighborZ) {
        TileEntity te = TileEntityHelper.getTileEntityUnsafe(world, x, y, z);

        // Signal power taps when their neighbors change, etc.
        if(te instanceof INeighborUpdatableEntity) {
            ((INeighborUpdatableEntity)te).onNeighborTileChange(world, x, y, z, neighborX, neighborY, neighborZ);
        }
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) {
            return false;
        }

        int metadata = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);
        IMultiblockPart part = null;
        MultiblockControllerBase controller = null;

        if(te instanceof IMultiblockPart) {
            part = (IMultiblockPart)te;
            controller = part.getMultiblockController();
        }

        if(isHousing(metadata)) {
            // If the player's hands are empty and they rightclick on a multiblock, they get a
            // multiblock-debugging message if the machine is not assembled.
            if(player.getCurrentEquippedItem() == null) {
                if(controller != null) {
                    Exception e = controller.getLastValidationException();
                    if(e != null) {
                        player.addChatMessage(new ChatComponentText(e.getMessage()));
                        return true;
                    }
                }
                else {
                    player.addChatMessage(new ChatComponentText("Block is not connected to a machine. This could be due to lag, or a bug. If the problem persists, try breaking and re-placing the block.")); //TODO Localize
                    return true;
                }
            }

            // If nonempty, or there was no error, just fall through
            return false;
        }

        // Don't open the controller GUI if the machine isn't assembled
        if(isController(metadata) && (controller == null || !controller.isAssembled())) { return false; }

        if(!world.isRemote) {
//TODO: change GUI from furnace to controller
            player.openGui(BigMachines.instance, GUIs.FURNACE_MODULE.ordinal(), world, x, y, z);
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    public ItemStack getItemStack(String name) {
        int metadata = -1;
        for(int i = 0; i < _subBlocks.length; i++) {
            if(_subBlocks[i].equals(name)) {
                metadata = i;
                break;
            }
        }

        if(metadata < 0) {
            throw new IllegalArgumentException("Unable to find a block with the name " + name);
        }

        return new ItemStack(this, 1, metadata);
    }

    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < _subBlocks.length; i++) {
            par3List.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        // Drop everything inside inventory blocks
        TileEntity te = world.getTileEntity(x, y, z);
        if(te instanceof IInventory)
        {
            IInventory inventory = ((IInventory)te);
            inv:		for(int i = 0; i < inventory.getSizeInventory(); i++)
            {
                ItemStack itemstack = inventory.getStackInSlot(i);
                if(itemstack == null)
                {
                    continue;
                }
                float xOffset = world.rand.nextFloat() * 0.8F + 0.1F;
                float yOffset = world.rand.nextFloat() * 0.8F + 0.1F;
                float zOffset = world.rand.nextFloat() * 0.8F + 0.1F;
                do
                {
                    if(itemstack.stackSize <= 0)
                    {
                        continue inv;
                    }
                    int amountToDrop = world.rand.nextInt(21) + 10;
                    if(amountToDrop > itemstack.stackSize)
                    {
                        amountToDrop = itemstack.stackSize;
                    }
                    itemstack.stackSize -= amountToDrop;
                    EntityItem entityitem = new EntityItem(world, (float)x + xOffset, (float)y + yOffset, (float)z + zOffset, new ItemStack(itemstack.getItem(), amountToDrop, itemstack.getItemDamage()));
                    if(itemstack.getTagCompound() != null)
                    {
                        entityitem.getEntityItem().setTagCompound(itemstack.getTagCompound());
                    }
                    float motionMultiplier = 0.05F;
                    entityitem.motionX = (float)world.rand.nextGaussian() * motionMultiplier;
                    entityitem.motionY = (float)world.rand.nextGaussian() * motionMultiplier + 0.2F;
                    entityitem.motionZ = (float)world.rand.nextGaussian() * motionMultiplier;
                    world.spawnEntityInWorld(entityitem);
                } while(true);
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    //TODO: implement random display tick for controller
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
        return false;
    }


}
