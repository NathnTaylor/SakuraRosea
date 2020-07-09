package rocks.sakira.sakurarosea.common.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SakuraLeafParticle extends SpriteTexturedParticle {
    protected SakuraLeafParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);

        this.particleScale *= 1.2f;

        int i = (int) (32.0D / (Math.random() * 0.8D + 0.2D));

        this.maxAge = (int) Math.max((float) i * 1.8F, 2.0F);
        this.particleAngle = (float) Math.random() * ((float) Math.PI * 2F);

        this.motionY = Math.min(this.motionY, -0.03d);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.move(this.motionX, this.motionY, this.motionZ);

            if (this.motionX <= 0.001d && -0.001d <= this.motionX) {
                this.motionX = 0;
            } else {
                if (this.motionX > 0) this.motionX -= 0.002F;
                else this.motionX += 0.002F;
            }

            if (this.motionZ <= 0.001d && -0.001d <= this.motionZ) {
                this.motionZ = 0;
            } else {
                if (this.motionZ > 0) this.motionZ -= 0.002F;
                else this.motionZ += 0.002F;
            }

            this.motionY -= 0.002F;
            this.motionY = Math.max(this.motionY, -0.03d);

            this.prevParticleAngle = this.particleAngle;

            if (!this.onGround) {
                this.particleAngle += (float) Math.PI * 0.02F;
            } else {
                this.motionY = 0.0D;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double r, double g, double b) {
            SakuraLeafParticle particle = new SakuraLeafParticle(
                    world,
                    x, y, z,

                    Math.max(Math.min(world.rand.nextDouble() - 0.5d, 0.05d), -0.05d),  // X speed
                    Math.max(Math.min(world.rand.nextDouble() - 0.5d, 0.05d), -0.05d),  // Y speed
                    0.03d   // Z speed
            );

            particle.setColor((float) r, (float) g, (float) b);
            particle.selectSpriteRandomly(this.spriteSet);

            return particle;
        }
    }
}
