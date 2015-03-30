package com.p3pp3rf1y.bigmachines.tileentity.modules;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityFurnaceModule extends TileEntityMachinePartModule implements IInventory
{
    public static final int INVENTORY_SIZE = 2;
    public static final int INPUT_INVENTORY_INDEX = 0;
    public static final int OUTPUT_INVENTORY_INDEX = 1;
    public static final int ITEM_COOK_TIME = 20;

    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] furnaceItemStacks = new ItemStack[INVENTORY_SIZE];

    /**
     * The number of ticks that the furnace will keep burning
     */
    public int furnaceBurnTime;
    /**
     * The number of ticks that the current item has been cooking for
     */
    public int furnaceCookTime;

    public TileEntityFurnaceModule()
    {
        super();

        furnaceBurnTime = 10000; //TODO: fix once electricity implemented
    }


    @Override
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.furnaceItemStacks[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        if (this.furnaceItemStacks[slotIndex] != null)
        {
            ItemStack itemstack;

            if (this.furnaceItemStacks[slotIndex].stackSize <= decrementAmount)
            {
                itemstack = this.furnaceItemStacks[slotIndex];
                this.furnaceItemStacks[slotIndex] = null;
                return itemstack;
            } else
            {
                itemstack = this.furnaceItemStacks[slotIndex].splitStack(decrementAmount);

                if (this.furnaceItemStacks[slotIndex].stackSize == 0)
                {
                    this.furnaceItemStacks[slotIndex] = null;
                }

                return itemstack;
            }
        } else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        //TODO: make sure that this doesn't drop what's in the furnace
        if (this.furnaceItemStacks[slotIndex] != null)
        {
            ItemStack itemstack = this.furnaceItemStacks[slotIndex];
            this.furnaceItemStacks[slotIndex] = null;
            return itemstack;
        } else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        this.furnaceItemStacks[slotIndex] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return "container.furnace";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = nbtTagCompound.getShort("BurnTime");
        this.furnaceCookTime = nbtTagCompound.getShort("CookTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("BurnTime", (short) this.furnaceBurnTime);
        nbtTagCompound.setShort("CookTime", (short) this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
        {
            if (this.furnaceItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTagCompound.setTag("Items", nbttaglist);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return slot == OUTPUT_INVENTORY_INDEX ? false : true;
    }

    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        return 10000; //TODO: fix with correct value based on charge
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.furnaceCookTime * p_145953_1_ / ITEM_COOK_TIME;
    }

    @Override
    public void updateEntity()
    {
        boolean stillBurns = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if (this.furnaceBurnTime > 0)
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.furnaceBurnTime != 0 || this.furnaceItemStacks[INPUT_INVENTORY_INDEX] != null)
            {
                if (this.isBurning() && this.canSmelt())
                {
                    ++this.furnaceCookTime;

                    if (this.furnaceCookTime == ITEM_COOK_TIME)
                    {
                        this.furnaceCookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                } else
                {
                    this.furnaceCookTime = 0;
                }
            }

            if (stillBurns != this.furnaceBurnTime > 0)
            {
                flag1 = true;
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.furnaceItemStacks[INPUT_INVENTORY_INDEX] == null)
        {
            return false;
        } else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[INPUT_INVENTORY_INDEX]);
            if (itemstack == null) return false;
            if (this.furnaceItemStacks[OUTPUT_INVENTORY_INDEX] == null) return true;
            if (!this.furnaceItemStacks[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) return false;
            int result = furnaceItemStacks[OUTPUT_INVENTORY_INDEX].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[OUTPUT_INVENTORY_INDEX].getMaxStackSize();
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[INPUT_INVENTORY_INDEX]);

            if (this.furnaceItemStacks[OUTPUT_INVENTORY_INDEX] == null)
            {
                this.furnaceItemStacks[OUTPUT_INVENTORY_INDEX] = itemstack.copy();
            } else if (this.furnaceItemStacks[OUTPUT_INVENTORY_INDEX].getItem() == itemstack.getItem())
            {
                this.furnaceItemStacks[OUTPUT_INVENTORY_INDEX].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.furnaceItemStacks[INPUT_INVENTORY_INDEX].stackSize;

            if (this.furnaceItemStacks[INPUT_INVENTORY_INDEX].stackSize <= 0)
            {
                this.furnaceItemStacks[INPUT_INVENTORY_INDEX] = null;
            }
        }
    }
}
