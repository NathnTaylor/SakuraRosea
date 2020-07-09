package rocks.sakira.sakurarosea.common.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import rocks.sakira.sakurarosea.Constants;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Particles {
    public static final BasicParticleType SAKURA_LEAF = new BasicParticleType(false);

    @OnlyIn(Dist.CLIENT)
    public static void registerFactories() {
        Minecraft.getInstance().particles.registerFactory(SAKURA_LEAF, SakuraLeafParticle.Factory::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        SAKURA_LEAF.setRegistryName(new ResourceLocation(Constants.MOD_ID, "sakura_leaf"));
        ForgeRegistries.PARTICLE_TYPES.register(SAKURA_LEAF);
    }
}
