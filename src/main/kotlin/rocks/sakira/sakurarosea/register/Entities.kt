package rocks.sakira.sakurarosea.register

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import rocks.sakira.sakurarosea.MOD_ID
import rocks.sakira.sakurarosea.entity.SakuraBoatEntity

object Entities {
    val SAKURA_BOAT: EntityType<SakuraBoatEntity> = FabricEntityTypeBuilder
        .create(SpawnGroup.MISC, ::createSakuraBoat)
        .dimensions(EntityDimensions.fixed(1.375f, 0.5625f))
        .build()

    fun register() {
        register(SAKURA_BOAT, "sakura_boat")
    }

    private fun register(entityType: EntityType<*>, id: String) {
        Registry.register(Registry.ENTITY_TYPE, Identifier(MOD_ID, id), entityType)
    }

    // region: Factory functions

    fun sakuraBoatFactory(world: World, x: Double, y: Double, z: Double, yaw: Float): SakuraBoatEntity =
        SakuraBoatEntity(world, x, y, z, yaw)

    // endregion

    // region: Internal builder functions

    private fun createSakuraBoat(entityType: EntityType<out SakuraBoatEntity>, world: World): SakuraBoatEntity {
        return SakuraBoatEntity(SAKURA_BOAT, world)
    }

    // endregion
}
