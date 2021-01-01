package io.github.faecraft.sakurarosea.entity.renderer

import io.github.faecraft.sakurarosea.MOD_ID
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.model.BoatEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.client.util.math.Vector3f
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Quaternion
import io.github.faecraft.sakurarosea.entity.SakuraBoatEntity

class SakuraBoatEntityRenderer(entityRenderDispatcher: EntityRenderDispatcher) :
    EntityRenderer<SakuraBoatEntity>(entityRenderDispatcher) {

    companion object {
        private val TEXTURE = Identifier(MOD_ID, "textures/entity/boat/sakura.png")
    }

    private val boatModel = BoatEntityModel()

    override fun render(
        entity: SakuraBoatEntity,
        yaw: Float,
        ticks: Float,
        matrixStack: MatrixStack,
        vertex: VertexConsumerProvider,
        light: Int
    ) {
        matrixStack.push()
        matrixStack.translate(0.0, 0.375, 0.0)
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180F - yaw))

        val wobbleTicks = entity.damageWobbleTicks - ticks
        var wobbleStrength = entity.damageWobbleStrength - ticks

        if (wobbleStrength < 0F) {
            wobbleStrength = 0F
        }

        if (wobbleTicks > 0F) matrixStack.multiply(
            Vector3f.POSITIVE_X.getDegreesQuaternion(
                MathHelper.sin(wobbleTicks) * wobbleTicks * wobbleStrength / 10f * entity.damageWobbleSide.toFloat()
            )
        )

        val wobbleBubble = entity.interpolateBubbleWobble(ticks)

        if (!MathHelper.approximatelyEquals(wobbleBubble, 0F)) matrixStack.multiply(
            Quaternion(
                Vector3f(1F, 0F, 1F),
                wobbleBubble,  // entity.interpolateBubbleWobble(ticks),
                true
            )
        )

        matrixStack.scale(-1F, -1F, 1F)
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90F))

        val consumer = vertex.getBuffer(boatModel.getLayer(getTexture(entity)))

        boatModel.setAngles(entity, ticks, 0F, -0.1F, 0F, 0F)
        boatModel.render(matrixStack, consumer, light, OverlayTexture.DEFAULT_UV, 1F, 1F, 1F, 1F)

        if (!entity.isSubmergedInWater) {
            val waterConsumer = vertex.getBuffer(RenderLayer.getWaterMask())

            boatModel.bottom.render(matrixStack, waterConsumer, light, OverlayTexture.DEFAULT_UV)
        }

        matrixStack.pop()

        super.render(entity, yaw, ticks, matrixStack, vertex, light)
    }

    override fun getTexture(boatEntity: SakuraBoatEntity): Identifier = TEXTURE
}
