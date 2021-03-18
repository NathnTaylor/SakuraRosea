package io.github.faecraft.sakurarosea.blockentity

import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.entity.SignBlockEntity
import io.github.faecraft.sakurarosea.register.BlockEntities

class SakuraSignBlockEntity : SignBlockEntity() {
    override fun getType(): BlockEntityType<*> = BlockEntities.SAKURA_SIGN
}
