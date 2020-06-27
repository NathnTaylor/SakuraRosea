package rocks.sakira.sakurarosea.common.tileentities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.tileentity.BannerTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;
import rocks.sakira.sakurarosea.common.item.Items;

import java.util.List;

import static rocks.sakira.sakurarosea.common.Materials.SAKURA_SHIELD_BASE;
import static rocks.sakira.sakurarosea.common.Materials.SAKURA_SHIELD_NO_PATTERN;

public class SakuraShieldRenderer extends ItemStackTileEntityRenderer {
    private final ShieldModel shieldModel = new ShieldModel();

    @Override
    public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Item item = itemStackIn.getItem();

        if (item == Items.SAKURA_SHIELD_ITEM.get()) {
            boolean flag = itemStackIn.getChildTag("BlockEntityTag") != null;

            matrixStackIn.push();
            matrixStackIn.scale(1.0F, -1.0F, -1.0F);
            Material material = flag ? SAKURA_SHIELD_BASE : SAKURA_SHIELD_NO_PATTERN;

            IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getBuffer(bufferIn, shieldModel.getRenderType(material.getAtlasLocation()), false, itemStackIn.hasEffect()));
            shieldModel.func_228294_b_().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);

            if (flag) {
                List<Pair<BannerPattern, DyeColor>> list = BannerTileEntity.func_230138_a_(ShieldItem.getColor(itemStackIn), BannerTileEntity.func_230139_a_(itemStackIn));
                BannerTileEntityRenderer.func_230180_a_(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, shieldModel.func_228293_a_(), material, false, list);
            } else {
                shieldModel.func_228293_a_().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
            }

            matrixStackIn.pop();
        }
    }
}
