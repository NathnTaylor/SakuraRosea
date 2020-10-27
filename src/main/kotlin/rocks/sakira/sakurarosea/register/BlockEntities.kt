package rocks.sakira.sakurarosea.register

import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import rocks.sakira.sakurarosea.MOD_ID
import rocks.sakira.sakurarosea.blockentity.SakuraBarrelBlockEntity
import rocks.sakira.sakurarosea.blockentity.SakuraChestBlockEntity
import rocks.sakira.sakurarosea.blockentity.SakuraSignBlockEntity
import java.util.function.Supplier

object BlockEntities {
    val SAKURA_BARREL: BlockEntityType<SakuraBarrelBlockEntity> = BlockEntityType.Builder.create(
        Supplier { SakuraBarrelBlockEntity() },
        Blocks.SAKURA_BARREL
    ).build(null)

    val SAKURA_CHEST: BlockEntityType<SakuraChestBlockEntity> = BlockEntityType.Builder.create(
        Supplier { SakuraChestBlockEntity() },
        Blocks.SAKURA_CHEST
    ).build(null)

    val SAKURA_SIGN: BlockEntityType<SakuraSignBlockEntity> = BlockEntityType.Builder.create(
        Supplier { SakuraSignBlockEntity() },
        Blocks.SAKURA_SIGN,
        Blocks.SAKURA_SIGN_WALL
    ).build(null)

    fun register() {
        register(SAKURA_BARREL, "sakura_barrel")
        register(SAKURA_CHEST, "sakura_chest")
        register(SAKURA_SIGN, "sakura_sign")
    }

    private fun register(entity: BlockEntityType<*>, id: String) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Identifier(MOD_ID, id), entity)
    }
}
