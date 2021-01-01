package io.github.faecraft.sakurarosea.blockentity

import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.entity.ChestBlockEntity
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import io.github.faecraft.sakurarosea.register.BlockEntities

class SakuraChestBlockEntity : ChestBlockEntity {
    constructor() : this(BlockEntities.SAKURA_CHEST)
    constructor(type: BlockEntityType<*>) : super(type)

    override fun getContainerName(): Text = TranslatableText("container.sakurarosea.sakura_chest")
}
