package io.github.faecraft.sakurarosea.block

import net.minecraft.block.BlockState
import net.minecraft.block.SignBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.SignType
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World
import io.github.faecraft.sakurarosea.blockentity.SakuraSignBlockEntity

class SakuraSignBlock(settings: Settings, signType: SignType) : SignBlock(settings, signType) {
    override fun createBlockEntity(world: BlockView): BlockEntity = SakuraSignBlockEntity()

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        val result = super.onUse(state, world, pos, player, hand, hit)

        val entity = world.getBlockEntity(pos)

        println(entity?.isRemoved)

        return result
    }
}
