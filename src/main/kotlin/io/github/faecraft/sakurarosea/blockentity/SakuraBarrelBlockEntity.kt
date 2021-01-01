package io.github.faecraft.sakurarosea.blockentity

import net.minecraft.block.BarrelBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.entity.ChestBlockEntity
import net.minecraft.block.entity.LootableContainerBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.screen.GenericContainerScreenHandler
import net.minecraft.screen.ScreenHandler
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.TranslatableText
import net.minecraft.util.collection.DefaultedList
import io.github.faecraft.sakurarosea.register.Blocks
import io.github.faecraft.sakurarosea.register.BlockEntities

class SakuraBarrelBlockEntity : LootableContainerBlockEntity {
    private var inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(27, ItemStack.EMPTY)
    private var viewers = 0

    constructor() : this(BlockEntities.SAKURA_BARREL)
    constructor(type: BlockEntityType<*>) : super(type)

    override fun size() = 27
    override fun getContainerName() = TranslatableText("container.sakurarosea.sakura_barrel")
    override fun getInvStackList(): DefaultedList<ItemStack> = this.inventory
    override fun setInvStackList(list: DefaultedList<ItemStack>) { this.inventory = list }

    override fun createScreenHandler(syncId: Int, playerInventory: PlayerInventory): ScreenHandler =
        GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this)

    override fun toTag(tag: CompoundTag): CompoundTag {
        super.toTag(tag)

        if (!this.serializeLootTable(tag)) {
            Inventories.toTag(tag, this.inventory)
        }

        return tag
    }

    override fun fromTag(state: BlockState, tag: CompoundTag) {
        super.fromTag(state, tag)

        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY)

        if (!this.deserializeLootTable(tag)) {
            Inventories.fromTag(tag, this.inventory)
        }
    }

    override fun onOpen(player: PlayerEntity) {
        if (!player.isSpectator) {
            if (viewers < 0) viewers = 0

            viewers += 1

            val blockState = this.cachedState
            val isOpen = blockState.get(BarrelBlock.OPEN)

            if (!isOpen) {
                playSound(blockState, SoundEvents.BLOCK_BARREL_OPEN)
                setOpen(blockState, true)
            }

            this.scheduleUpdate()
        }
    }

    override fun onClose(player: PlayerEntity) {
        if (!player.isSpectator) viewers -= 1
    }

    fun tick() {
        viewers = ChestBlockEntity.countViewers(world, this, pos.x, pos.y, pos.z)

        if (viewers > 0) {
            scheduleUpdate()
        } else {
            val blockState = cachedState

            if (blockState.block != Blocks.SAKURA_BARREL) {
                markRemoved()
            } else {
                val isOpen = blockState.get(BarrelBlock.OPEN)

                if (isOpen) {
                    playSound(blockState, SoundEvents.BLOCK_BARREL_CLOSE)
                    setOpen(blockState, false)
                }
            }
        }
    }

    private fun scheduleUpdate() = world!!.blockTickScheduler.schedule(this.pos, this.cachedState.block, 5)

    private fun setOpen(state: BlockState, isOpen: Boolean) =
        world!!.setBlockState(this.pos, state.with(BarrelBlock.OPEN, isOpen), 3)

    private fun playSound(state: BlockState, sound: SoundEvent) {
        val vector = state.get(BarrelBlock.FACING).vector

        val x = pos.x + 0.5 + vector.x / 2.0
        val y = pos.y + 0.5 + vector.y / 2.0
        val z = pos.z + 0.5 + vector.z / 2.0

        world!!.playSound(
            null,
            x, y, z,
            sound, SoundCategory.BLOCKS,
            0.5F, world!!.random.nextFloat() * 0.1F + 0.9F
        )
    }
}
