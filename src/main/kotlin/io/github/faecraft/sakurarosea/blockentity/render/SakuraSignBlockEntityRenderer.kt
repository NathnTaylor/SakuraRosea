package io.github.faecraft.sakurarosea.blockentity.render

import net.minecraft.block.AbstractSignBlock
import net.minecraft.block.Block
import net.minecraft.block.SignBlock
import net.minecraft.block.WallSignBlock
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.TexturedRenderLayers
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer
import net.minecraft.client.texture.NativeImage
import net.minecraft.client.util.SpriteIdentifier
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.util.math.Vector3f
import net.minecraft.text.OrderedText
import net.minecraft.util.Identifier
import net.minecraft.util.SignType
import io.github.faecraft.sakurarosea.blockentity.SakuraSignBlockEntity
import io.github.faecraft.sakurarosea.register.Sprites
import java.util.function.Function

class SakuraSignBlockEntityRenderer(blockEntityRenderDispatcher: BlockEntityRenderDispatcher) :
    BlockEntityRenderer<SakuraSignBlockEntity>(blockEntityRenderDispatcher) {
    private val model = SignBlockEntityRenderer.SignModel()

    companion object {
        fun getTexture(block: Block): SpriteIdentifier {
            return if (block is AbstractSignBlock) {
                Sprites.SAKURA_SIGN_MATERIAL
            } else {
                TexturedRenderLayers.getSignTextureId(SignType.OAK)
            }
        }
    }

    override fun render(
        entity: SakuraSignBlockEntity,
        ticks: Float,
        stack: MatrixStack,
        consumerProvider: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        val state = entity.cachedState

        stack.push()

        if (state.block is SignBlock) {
            val h = -((state.get(SignBlock.ROTATION) * 360) / 16F)

            stack.translate(0.5, 0.5, 0.5)
            stack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(h))

            model.foot.visible = true
        } else {
            val h = -(state.get(WallSignBlock.FACING).asRotation())

            stack.translate(0.5, 0.5, 0.5)
            stack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(h))
            stack.translate(0.0, -0.3125, -0.4375)

            model.foot.visible = false
        }

        stack.push()
        stack.scale(0.6666667F, -0.6666667F, -0.6666667F)

        val identifier = getTexture(state.block)

        val consumer =
            identifier.getVertexConsumer(consumerProvider, Function<Identifier, RenderLayer> { a: Identifier ->
                model.getLayer(a)
            })

        model.field.render(stack, consumer, light, overlay)
        model.foot.render(stack, consumer, light, overlay)

        stack.pop()

        val textRenderer = dispatcher.textRenderer

        val l = 0.010416667f

        stack.translate(0.0, 0.3333333432674408, 0.046666666865348816)
        stack.scale(0.010416667F, -0.010416667F, 0.010416667F)

        val signColour = entity.textColor.signColor

        val red = (NativeImage.getRed(signColour) * 0.4).toInt()
        val green = (NativeImage.getGreen(signColour) * 0.4).toInt()
        val blue = (NativeImage.getBlue(signColour) * 0.4).toInt()

        val colour = NativeImage.getAbgrColor(0, blue, green, red)

        repeat(4) {
            val orderedText = entity.getTextBeingEditedOnRow(it) { text ->
                val list = textRenderer.wrapLines(text, 90)

                if (list.isEmpty()) {
                    OrderedText.EMPTY
                } else {
                    list[0]
                }
            }

            if (orderedText != null) {
                val width = (-textRenderer.getWidth(orderedText) / 2).toFloat()

                textRenderer.draw(
                    orderedText,
                    width,
                    (it * 10 - 20).toFloat(),
                    colour,
                    false,
                    stack.peek().model,
                    consumerProvider,
                    false,
                    0,
                    light
                )
            }
        }

        stack.pop()
    }
}
