package com.p3pp3rf1y.bigmachines.inventory;

import com.p3pp3rf1y.bigmachines.tileentity.TileEntityFurnaceModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFurnaceModule extends ContainerBigMachines
{
    private TileEntityFurnaceModule tileEntityFurnaceModule;

    private int lastFurnaceBurnTime;
    private int lastFurnaceCookTime;

    //TODO: make sure that all progress bars are included

    public ContainerFurnaceModule(InventoryPlayer inventoryPlayer, TileEntityFurnaceModule tileEntityFurnaceModule)
    {
        this.tileEntityFurnaceModule = tileEntityFurnaceModule;

        // Add the input slot to the container
        this.addSlotToContainer(new Slot(tileEntityFurnaceModule, TileEntityFurnaceModule.INPUT_INVENTORY_INDEX, 56, 17));

        // Add the output results slot to the container
        this.addSlotToContainer(new SlotFurnaceModule(tileEntityFurnaceModule, TileEntityFurnaceModule.OUTPUT_INVENTORY_INDEX, 116, 35));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 84 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityFurnaceModule.furnaceBurnTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityFurnaceModule.furnaceCookTime);
    }


    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters)
        {
            ICrafting icrafting = (ICrafting) crafter;

            if (this.lastFurnaceBurnTime != this.tileEntityFurnaceModule.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityFurnaceModule.furnaceBurnTime);
            }

            if (this.lastFurnaceCookTime != this.tileEntityFurnaceModule.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityFurnaceModule.furnaceCookTime);
            }
        }

        this.lastFurnaceBurnTime = this.tileEntityFurnaceModule.furnaceBurnTime;
        this.lastFurnaceCookTime = this.tileEntityFurnaceModule.furnaceCookTime;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {

            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            /**
             * Shift click out of furnace
             */
            if (slotIndex < TileEntityFurnaceModule.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(slotItemStack, TileEntityFurnaceModule.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                /**
                 * Shift click into furnace
                 */
                if (!this.mergeItemStack(slotItemStack, TileEntityFurnaceModule.INPUT_INVENTORY_INDEX, TileEntityFurnaceModule.OUTPUT_INVENTORY_INDEX, false))
                {
                    return null;
                }
            }

            if (slotItemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityFurnaceModule.furnaceBurnTime = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityFurnaceModule.furnaceCookTime = updatedValue;
        }

    }
}
