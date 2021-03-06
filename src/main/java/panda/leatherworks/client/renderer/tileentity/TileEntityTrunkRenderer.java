package panda.leatherworks.client.renderer.tileentity;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.leatherworks.common.tileentity.TileEntityTrunk;

@SideOnly(Side.CLIENT)
public class TileEntityTrunkRenderer extends TileEntitySpecialRenderer<TileEntityTrunk>
{
    protected static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
    		new ResourceLocation("leatherworks:textures/models/trunk_red.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_orange.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_magenta.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_light_blue.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_yellow.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_lime.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_pink.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_gray.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_silver.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_cyan.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_purple.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_blue.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_normal.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_green.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_red.png"),
    		new ResourceLocation("leatherworks:textures/models/trunk_black.png")};

    private final ModelChest model = new ModelChest();
    
    
    @Override
    public void render(TileEntityTrunk te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    	if (te.isInvalid())
        {
            return;
        }

        EnumFacing facing = te.getFacing();
        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4F, 4F, 1F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(TEXTURES[te.getColor().getMetadata()]);
        }

        GlStateManager.pushMatrix();

        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.translate((float) x, (float) y + 1F, (float) z + 1F);
        GlStateManager.scale(1F, -1F, -1F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);

        switch (facing)
        {
        case NORTH:
        	GlStateManager.rotate(180F, 0F, 1F, 0F);
        	break;
        case SOUTH:
        	GlStateManager.rotate(0F, 0F, 1F, 0F);
        	break;
        case WEST:     
        	GlStateManager.rotate(90F, 0F, 1F, 0F);
        	break;
        case EAST:       
        	GlStateManager.rotate(270F, 0F, 1F, 0F);
        	break;
        default:
        	GlStateManager.rotate(0F, 0F, 1F, 0F);
        	break;
        }

        GlStateManager.translate(-0.5F, -0.5F, -0.5F);

        float lidangle = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;

        lidangle = 1F - lidangle;
        lidangle = 1F - lidangle * lidangle * lidangle;

        this.model.chestLid.rotateAngleX = (float) (-lidangle * Math.PI/2);
        // Render the chest itself
        this.model.renderAll();

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

        GlStateManager.popMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);
    }
}