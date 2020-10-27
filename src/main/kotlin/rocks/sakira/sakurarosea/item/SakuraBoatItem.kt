package rocks.sakira.sakurarosea.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.hit.HitResult
import net.minecraft.world.RaycastContext
import net.minecraft.world.World

class SakuraBoatItem(private val factory: (World, Double, Double, Double, Float) -> BoatEntity, settings: Settings) :
    Item(settings) {
    companion object {
        private val RIDERS = EntityPredicates.EXCEPT_SPECTATOR.and { it.collides() }
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        val result = raycast(world, user, RaycastContext.FluidHandling.ANY)

        if (result.type == HitResult.Type.MISS) {
            return TypedActionResult.pass(stack)
        }

        val vector = user.getRotationVec(1F)
        val entities = world.getOtherEntities(user, user.boundingBox.stretch(vector.multiply(5.0)).expand(1.0), RIDERS)

        if (entities.isNotEmpty()) {
            val cameraVector = user.getCameraPosVec(1F)
            val iterator = entities.iterator()

            for (entity in entities) {
                val box = entity.boundingBox.expand(entity.targetingMargin.toDouble())

                if (box.contains(cameraVector)) {
                    return TypedActionResult.pass(stack)
                }
            }
        }

        if (result.type == HitResult.Type.BLOCK) {
            val entity = createBoat(world, result.pos.x, result.pos.y, result.pos.z, user.yaw)

            if (!world.isSpaceEmpty(entity, entity.boundingBox.expand(-0.1))) {
                return TypedActionResult.fail(stack)
            }

            if (!world.isClient) {
                world.spawnEntity(entity)

                if (!user.isCreative) {
                    stack.decrement(1)
                }
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this))

            return TypedActionResult.success(stack, world.isClient)
        }

        return TypedActionResult.pass(stack)
    }

    fun createBoat(world: World, x: Double, y: Double, z: Double, yaw: Float): BoatEntity {
        val entity = factory.invoke(world, x, y, z, yaw)

        entity.yaw = yaw

        return entity
    }
}
