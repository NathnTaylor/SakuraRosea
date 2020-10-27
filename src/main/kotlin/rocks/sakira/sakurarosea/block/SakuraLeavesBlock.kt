package rocks.sakira.sakurarosea.block

import net.minecraft.block.BlockState
import net.minecraft.block.LeavesBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import rocks.sakira.sakurarosea.register.Particles
import java.util.*

class SakuraLeavesBlock(val leafColour: Int, settings: Settings) : LeavesBlock(settings) {
    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        super.randomDisplayTick(state, world, pos, random)

        if (random.nextInt(35) == 0) {
            val lowerPos = pos.down()

            if (world.isAir(lowerPos)) {
                val velocityX: Double = (leafColour shr 16 and 255) / 255.0f.toDouble()
                val velocityY: Double = (leafColour shr 8 and 255) / 255.0f.toDouble()
                val velocityZ: Double = (leafColour and 255) / 255.0f.toDouble()

                val x: Double = (pos.x.toFloat() + random.nextFloat()).toDouble()
                val y = pos.y - 0.05
                val z: Double = (pos.z.toFloat() + random.nextFloat()).toDouble()

                world.addParticle(Particles.SAKURA_LEAF, x, y, z, velocityX, velocityY, velocityZ)
            }
        }
    }
}
