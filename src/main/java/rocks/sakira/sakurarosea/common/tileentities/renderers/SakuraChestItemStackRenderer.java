package rocks.sakira.sakurarosea.common.tileentities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import rocks.sakira.sakurarosea.common.block.SakuraChestBlock;

public class SakuraChestItemStackRenderer extends ItemStackTileEntityRenderer {
    @Override
    public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Block block = Block.getBlockFromItem(itemStackIn.getItem());

        if (block instanceof SakuraChestBlock) {
            SakuraChestBlock chestBlock = (SakuraChestBlock) block;
            TileEntity entity = chestBlock.getTileEntityType().create();
            TileEntityRenderer entityRenderer = TileEntityRendererDispatcher.instance.getRenderer(entity);

            if (entityRenderer instanceof SakuraChestTileEntityRenderer) {
                SakuraChestTileEntityRenderer sakuraRenderer = (SakuraChestTileEntityRenderer) entityRenderer;
                sakuraRenderer.render(entity, 0F, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
            }
        } else {
            super.render(itemStackIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        }
    }
}
