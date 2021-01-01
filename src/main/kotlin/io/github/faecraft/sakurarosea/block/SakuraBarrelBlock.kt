package io.github.faecraft.sakurarosea.block

import net.minecraft.block.BarrelBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.PiglinBrain
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.stat.Stats
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World
import io.github.faecraft.sakurarosea.blockentity.SakuraBarrelBlockEntity
import java.util.*

class SakuraBarrelBlock(settings: Settings) : BarrelBlock(settings) {
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        return if (world.isClient) {
            ActionResult.SUCCESS
        } else {
            val blockEntity = world.getBlockEntity(pos)

            if (blockEntity is SakuraBarrelBlockEntity) {
                player.openHandledScreen(blockEntity)
                player.incrementStat(Stats.OPEN_BARREL)

                PiglinBrain.onGuardedBlockInteracted(player, true)
            }

            ActionResult.SUCCESS
        }
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        val blockEntity = world.getBlockEntity(pos)

        if (blockEntity is SakuraBarrelBlockEntity) {
            blockEntity.tick()
        }
    }

    override fun createBlockEntity(world: BlockView): BlockEntity? {
        return SakuraBarrelBlockEntity()
    }

    override fun onPlaced(
        world: World,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        itemStack: ItemStack
    ) {
        if (itemStack.hasCustomName()) {
            val blockEntity = world.getBlockEntity(pos)

            if (blockEntity is SakuraBarrelBlockEntity) {
                blockEntity.customName = itemStack.name
            }
        }
    }
}
