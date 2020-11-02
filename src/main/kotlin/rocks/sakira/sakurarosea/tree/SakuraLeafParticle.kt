package rocks.sakira.sakurarosea.tree

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import kotlin.math.max
import kotlin.math.min

class SakuraLeafParticle(
    clientWorld: ClientWorld?,
    x: Double,
    y: Double,
    z: Double,
    velocityX: Double,
    velocityY: Double,
    velocityZ: Double
) : SpriteBillboardParticle(clientWorld, x, y, z, velocityX, velocityY, velocityZ) {

    init {
        scale *= 1.2F

        val num = (128.0 / (Math.random() * 0.8 + 0.2))

        this.maxAge = (num * 3.8).coerceAtLeast(6.0).toInt()
        this.angle = (Math.random() * (Math.PI * 2)).toFloat()
        this.velocityY = this.velocityY.coerceAtMost(-0.05)
    }

    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    override fun tick() {
        prevPosX = x
        prevPosY = y
        prevPosZ = z

        if (age++ >= maxAge) {
            markDead()
        } else {
            move(velocityX, velocityY, velocityZ)

            if (velocityX <= 0.001 && -0.001 <= velocityX) {
                velocityX = 0.0
            } else {
                if (velocityX > 0) this.velocityX -= 0.002
                else this.velocityX += 0.002
            }

            if (velocityZ <= 0.001 && -0.001 <= velocityZ) {
                velocityZ = 0.0
            } else {
                if (velocityZ > 0) this.velocityZ -= 0.002
                else this.velocityZ += 0.002
            }

            velocityY -= 0.002
            velocityY = velocityY.coerceAtLeast(-0.05)

            prevAngle = angle

            if (!onGround) angle += (Math.PI * 0.02).toFloat()
            else velocityY = 0.0
        }
    }

    @Environment(EnvType.CLIENT)
    class Factory(val spriteSet: SpriteProvider) : ParticleFactory<DefaultParticleType> {
        override fun createParticle(
            parameters: DefaultParticleType,
            world: ClientWorld,

            x: Double,
            y: Double,
            z: Double,

            velocityX: Double,
            velocityY: Double,
            velocityZ: Double
        ): Particle {
            val particle = SakuraLeafParticle(
                world,
                x, y, z,
                max(min(world.random.nextDouble() - 0.5, 0.05), -0.05),  // X velocity
                max(min(world.random.nextDouble() - 0.5, 0.05), -0.05),  // Y velocity
                0.05  // | velocity
            )

            // NOTE: We did this in the Java version and it works for some reason.
            // No, we don't know why. Thanks, Mojang!
            particle.setColor(velocityX.toFloat(), velocityY.toFloat(), velocityZ.toFloat())
            particle.setSprite(spriteSet)

            return particle
        }

    }
}
