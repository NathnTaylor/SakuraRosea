package rocks.sakira.sakurarosea.block

import net.minecraft.block.ChestBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.entity.ChestBlockEntity
import net.minecraft.world.BlockView
import rocks.sakira.sakurarosea.blockentity.SakuraChestBlockEntity
import java.util.function.Supplier

class SakuraChestBlock(settings: Settings, supplier: Supplier<BlockEntityType<out ChestBlockEntity>>) :
    ChestBlock(settings, supplier) {

    override fun createBlockEntity(world: BlockView?): BlockEntity = SakuraChestBlockEntity()
}
