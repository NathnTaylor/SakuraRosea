package rocks.sakira.sakurarosea.block

import net.minecraft.block.WallSignBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.SignType
import net.minecraft.world.BlockView
import rocks.sakira.sakurarosea.blockentity.SakuraSignBlockEntity

class SakuraWallSignBlock(settings: Settings, signType: SignType) : WallSignBlock(settings, signType) {
    override fun createBlockEntity(world: BlockView): BlockEntity = SakuraSignBlockEntity()
}
