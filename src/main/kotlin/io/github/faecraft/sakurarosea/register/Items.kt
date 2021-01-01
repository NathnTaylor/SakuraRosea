package io.github.faecraft.sakurarosea.register

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.SignBlock
import net.minecraft.block.WallSignBlock
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import io.github.faecraft.sakurarosea.MOD_ID
import io.github.faecraft.sakurarosea.item.SakuraBoatItem
import io.github.faecraft.sakurarosea.item.SakuraTeaItem
import io.github.faecraft.sakurarosea.register.Entities.sakuraBoatFactory

object Items {
    val GROUP: ItemGroup = FabricItemGroupBuilder
        .create(Identifier(MOD_ID, "sakurarosea"))
        .icon { ItemStack(SAKURA_SAPLING) }
        .build()

    val CHERRY = createFoodItem(FoodComponents.APPLE)
    val CHERRY_PIE = createBlockItem(Blocks.CHERRY_PIE)

    val SAKURA_BLOSSOM = createItem()
    val SAKURA_SAPLING = createBlockItem(Blocks.SAKURA_SAPLING)

    val SAKURA_BUTTON = createBlockItem(Blocks.SAKURA_BUTTON)
    val SAKURA_PRESSURE_PLATE = createBlockItem(Blocks.SAKURA_PRESSURE_PLATE)

    val SAKURA_CRAFTING_TABLE = createBlockItem(Blocks.SAKURA_CRAFTING_TABLE)

    val SAKURA_LEAVES = createBlockItem(Blocks.SAKURA_LEAVES)
    val SAKURA_LEAVES_ALT = createBlockItem(Blocks.SAKURA_LEAVES_ALT)
    val SAKURA_LEAVES_WHITE = createBlockItem(Blocks.SAKURA_LEAVES_WHITE)

    val SAKURA_LEAF_CARPET = createBlockItem(Blocks.SAKURA_LEAF_CARPET)
    val SAKURA_LEAF_CARPET_ALT = createBlockItem(Blocks.SAKURA_LEAF_CARPET_ALT)
    val SAKURA_LEAF_CARPET_WHITE = createBlockItem(Blocks.SAKURA_LEAF_CARPET_WHITE)

    val SAKURA_LOG = createBlockItem(Blocks.SAKURA_LOG)
    val SAKURA_LOG_STRIPPED = createBlockItem(Blocks.SAKURA_LOG_STRIPPED)
    val SAKURA_WOOD = createBlockItem(Blocks.SAKURA_WOOD)
    val SAKURA_WOOD_STRIPPED = createBlockItem(Blocks.SAKURA_WOOD_STRIPPED)

    val SAKURA_PLANKS = createBlockItem(Blocks.SAKURA_PLANKS)

    val SAKURA_BARREL = createBlockItem(Blocks.SAKURA_BARREL)
    val SAKURA_CHEST = createBlockItem(Blocks.SAKURA_CHEST)

    val SAKURA_FENCE = createBlockItem(Blocks.SAKURA_FENCE)
    val SAKURA_FENCE_GATE = createBlockItem(Blocks.SAKURA_FENCE_GATE)
    val SAKURA_SLAB = createBlockItem(Blocks.SAKURA_SLAB)
    val SAKURA_STAIRS = createBlockItem(Blocks.SAKURA_STAIRS)

    val SAKURA_SIGN = createSignItem(Blocks.SAKURA_SIGN, Blocks.SAKURA_SIGN_WALL)

    val SAKURA_DOOR = createBlockItem(Blocks.SAKURA_DOOR)
    val SAKURA_TRAPDOOR = createBlockItem(Blocks.SAKURA_TRAPDOOR)

    val PINK_BRICK = createItem()
    val PINK_CLAY = createItem()
    val PINK_CLAY_DOOR = createBlockItem(Blocks.PINK_CLAY_DOOR)

    val WHITE_BRICK = createItem()
    val WHITE_CLAY = createItem()
    val WHITE_CLAY_DOOR = createBlockItem(Blocks.WHITE_CLAY_DOOR)

    val PINK_BRICKS = createBlockItem(Blocks.PINK_BRICKS)
    val PINK_BRICKS_STAIRS = createBlockItem(Blocks.PINK_BRICKS_STAIRS)
    val PINK_BRICKS_WALL = createBlockItem(Blocks.PINK_BRICKS_WALL)
    val PINK_BRICKS_SLAB = createBlockItem(Blocks.PINK_BRICKS_SLAB)

    val WHITE_BRICKS = createBlockItem(Blocks.WHITE_BRICKS)
    val WHITE_BRICKS_STAIRS = createBlockItem(Blocks.WHITE_BRICKS_STAIRS)
    val WHITE_BRICKS_WALL = createBlockItem(Blocks.WHITE_BRICKS_WALL)
    val WHITE_BRICKS_SLAB = createBlockItem(Blocks.WHITE_BRICKS_SLAB)

//    Fabric Shield Lib has no docs or usable examples, so we're just not going to have a shield for now
//    val SAKURA_SHIELD = createShield()

    val SAKURA_TEA = SakuraTeaItem(
        FabricItemSettings().group(GROUP)
            .maxCount(1)
            .food(FoodComponents.BAKED_POTATO)
    )

    val SAKURA_BOAT = createBoat(::sakuraBoatFactory)

    fun register() {
        register(CHERRY, "cherry")
        register(CHERRY_PIE, "cherry_pie")

        register(SAKURA_BLOSSOM, "sakura_blossom")
        register(SAKURA_SAPLING, "sakura_sapling")

        register(SAKURA_BUTTON, "sakura_button")
        register(SAKURA_PRESSURE_PLATE, "sakura_pressure_plate")

        register(SAKURA_CRAFTING_TABLE, "sakura_crafting_table")

        register(SAKURA_LEAVES, "sakura_leaves")
        register(SAKURA_LEAVES_ALT, "alt_sakura_leaves")
        register(SAKURA_LEAVES_WHITE, "white_sakura_leaves")

        register(SAKURA_LEAF_CARPET, "sakura_leaf_carpet")
        register(SAKURA_LEAF_CARPET_ALT, "alt_sakura_leaf_carpet")
        register(SAKURA_LEAF_CARPET_WHITE, "white_sakura_leaf_carpet")

        register(SAKURA_LOG, "sakura_log")
        register(SAKURA_LOG_STRIPPED, "stripped_sakura_log")
        register(SAKURA_WOOD, "sakura_wood")
        register(SAKURA_WOOD_STRIPPED, "stripped_sakura_wood")

        register(SAKURA_PLANKS, "sakura_planks")

        register(SAKURA_BARREL, "sakura_barrel")
        register(SAKURA_CHEST, "sakura_chest")

        register(SAKURA_FENCE, "sakura_fence")
        register(SAKURA_FENCE_GATE, "sakura_fence_gate")
        register(SAKURA_SLAB, "sakura_slab")
        register(SAKURA_STAIRS, "sakura_stairs")

        register(SAKURA_SIGN, "sakura_sign")

        register(SAKURA_DOOR, "sakura_door")
        register(SAKURA_TRAPDOOR, "sakura_trapdoor")

        register(PINK_BRICK, "pink_brick")
        register(PINK_CLAY, "pink_clay")
        register(PINK_CLAY_DOOR, "pink_clay_door")

        register(WHITE_BRICK, "white_brick")
        register(WHITE_CLAY, "white_clay")
        register(WHITE_CLAY_DOOR, "white_clay_door")

        register(PINK_BRICKS, "pink_bricks")
        register(PINK_BRICKS_STAIRS, "pink_bricks_stairs")
        register(PINK_BRICKS_WALL, "pink_bricks_wall")
        register(PINK_BRICKS_SLAB, "pink_bricks_slab")

        register(WHITE_BRICKS, "white_bricks")
        register(WHITE_BRICKS_STAIRS, "white_bricks_stairs")
        register(WHITE_BRICKS_WALL, "white_bricks_wall")
        register(WHITE_BRICKS_SLAB, "white_bricks_slab")

//        register(SAKURA_SHIELD, "sakura_shield")

        register(SAKURA_TEA, "sakura_tea")

        register(SAKURA_BOAT, "sakura_boat")
    }

    private fun register(item: Item, id: String) {
        Registry.register(Registry.ITEM, Identifier(MOD_ID, id), item)
    }

    // region: Internal builder functions

    private fun createBoat(factory: (World, Double, Double, Double, Float) -> BoatEntity) =
        SakuraBoatItem(factory, FabricItemSettings().group(GROUP))

    private fun createBlockItem(block: Block) = BlockItem(
        block,

        FabricItemSettings().group(GROUP)
    )

    private fun createBlockItem(block: Block, maxCount: Int) = BlockItem(
        block,

        FabricItemSettings()
            .group(GROUP)
            .maxCount(maxCount)
    )

    private fun createFoodItem(food: FoodComponent) = Item(
        FabricItemSettings()
            .group(GROUP)
            .food(food)
    )

    private fun createItem() = Item(FabricItemSettings().group(GROUP))

//    private fun createShield() = BasicShield(
//        FabricItemSettings().group(GROUP).maxCount(1),
//        100,
//        336,
//        SAKURA_PLANKS
//    )

    private fun createSignItem(standingBlock: SignBlock, wallBlock: WallSignBlock) = SignItem(
        FabricItemSettings()
            .group(GROUP)
            .maxCount(16),
        standingBlock,
        wallBlock
    )

    // endregion
}
