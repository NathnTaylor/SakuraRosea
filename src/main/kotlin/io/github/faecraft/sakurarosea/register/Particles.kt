package io.github.faecraft.sakurarosea.register

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import io.github.faecraft.sakurarosea.MOD_ID
import io.github.faecraft.sakurarosea.tree.SakuraLeafParticle

object Particles {
    val SAKURA_LEAF: DefaultParticleType = FabricParticleTypes.simple()

    fun register() {
        register(SAKURA_LEAF, Identifier(MOD_ID, "sakura_leaf")) { SakuraLeafParticle.Factory(it) }
    }

    fun register(particle: ParticleType<DefaultParticleType>, id: Identifier, factory: (FabricSpriteProvider) -> (ParticleFactory<DefaultParticleType>)) {
        Registry.register(Registry.PARTICLE_TYPE, id, particle)

        ParticleFactoryRegistry.getInstance().register(particle, factory)
    }
}
