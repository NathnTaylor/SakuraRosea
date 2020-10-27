package rocks.sakira.sakurarosea.blockentity

import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.entity.SignBlockEntity
import rocks.sakira.sakurarosea.register.BlockEntities

class SakuraSignBlockEntity : SignBlockEntity() {
    override fun getType(): BlockEntityType<*> = BlockEntities.SAKURA_SIGN
}
