package com.p3pp3rf1y.bigmachines.client.gui.inventory;

import com.p3pp3rf1y.bigmachines.inventory.ContainerFurnaceModule;
import com.p3pp3rf1y.bigmachines.reference.Textures;
import com.p3pp3rf1y.bigmachines.tileentity.modules.TileEntityFurnaceModule;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiFurnaceModule extends GuiContainer
{
    private TileEntityFurnaceModule tileFurnaceModule;

    public GuiFurnaceModule(InventoryPlayer inventoryPlayer, TileEntityFurnaceModule tileEntityFurnaceModule)
    {
        super(new ContainerFurnaceModule(inventoryPlayer, tileEntityFurnaceModule));
        this.tileFurnaceModule = tileEntityFurnaceModule;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        //TODO: figure out if this is needed, seems to be needed for custom name only
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.FURNACE_MODULE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if (this.tileFurnaceModule.isBurning())
        {
            int i1 = this.tileFurnaceModule.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            i1 = this.tileFurnaceModule.getCookProgressScaled(24);
            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }
    }
}
