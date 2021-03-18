package io.github.faecraft.sakurarosea.item.render

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import io.github.faecraft.sakurarosea.block.SakuraChestBlock

class SakuraChestItemRenderer : BuiltinItemRendererRegistry.DynamicItemRenderer {
    override fun render(
        stack: ItemStack,
        mode: ModelTransformation.Mode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        val block = Block.getBlockFromItem(stack.item)

        if (block is SakuraChestBlock) {
            val entity = block.createBlockEntity(MinecraftClient.getInstance().world)
            val renderer = BlockEntityRenderDispatcher.INSTANCE.get(entity)

            if (renderer is ChestBlockEntityRenderer) {
                renderer.render(entity, 0F, matrices, vertexConsumers, light, overlay)
            }
        }
    }
}
