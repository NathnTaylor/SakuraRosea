package rocks.sakira.sakurarosea.register

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.item.Item
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier
import net.minecraft.util.SignType
import net.minecraft.util.registry.Registry
import rocks.sakira.sakurarosea.MOD_ID
import rocks.sakira.sakurarosea.block.*
import rocks.sakira.sakurarosea.mixin.common.BlocksMixin
import rocks.sakira.sakurarosea.tree.SakuraSaplingGenerator
import java.util.function.Supplier

object Blocks {
    val CHERRY_PIE = UtilCakeBlock(FabricBlockSettings.copyOf(Blocks.CAKE))

    val SAKURA_BUTTON = createWoodButton(MaterialColor.MAGENTA)
    val SAKURA_PRESSURE_PLATE = createWoodPressurePlate(
        PressurePlateBlock.ActivationRule.EVERYTHING,
        MaterialColor.MAGENTA
    )

    val SAKURA_CRAFTING_TABLE  = createCraftingTable(Material.WOOD, MaterialColor.MAGENTA)

    val SAKURA_LEAVES = createLeaves(0xFFEDF1)
    val SAKURA_LEAVES_ALT = createLeaves(0xFFD4E2)
    val SAKURA_LEAVES_WHITE = createLeaves(0xFFFFFF)

    val SAKURA_LEAF_CARPET = createLeafCarpet()
    val SAKURA_LEAF_CARPET_ALT = createLeafCarpet()
    val SAKURA_LEAF_CARPET_WHITE = createLeafCarpet()

    val SAKURA_LOG: PillarBlock = createLogBlock(MaterialColor.MAGENTA)
    val SAKURA_LOG_STRIPPED: PillarBlock = createLogBlock(MaterialColor.MAGENTA)
    val SAKURA_WOOD = createWoodBlock(MaterialColor.MAGENTA)
    val SAKURA_WOOD_STRIPPED = createWoodBlock(MaterialColor.MAGENTA)

    val SAKURA_PLANKS = createBlock(
        Material.WOOD,
        MaterialColor.MAGENTA,
        2.0F,
        BlockSoundGroup.WOOD,
        FabricToolTags.AXES
    )

    val SAKURA_BARREL = createBarrel(SAKURA_PLANKS)
    val SAKURA_CHEST = createChest(SAKURA_PLANKS)

    val SAKURA_FENCE = createFence(SAKURA_PLANKS)
    val SAKURA_FENCE_GATE = createFenceGate(SAKURA_PLANKS)
    val SAKURA_SLAB = createSlab(SAKURA_PLANKS)
    val SAKURA_STAIRS = createStairs(SAKURA_PLANKS)

    val SAKURA_SIGN = createSign(SAKURA_PLANKS, SignTypes.SAKURA)
    val SAKURA_SIGN_WALL = createWallSign(SAKURA_PLANKS, SignTypes.SAKURA)

    val SAKURA_DOOR = createDoor(Material.WOOD, MaterialColor.MAGENTA, FabricToolTags.AXES)
    val SAKURA_TRAPDOOR = createTrapDoor(Material.WOOD, MaterialColor.MAGENTA, FabricToolTags.AXES)

    val PINK_CLAY_DOOR = createDoor(Material.STONE, MaterialColor.MAGENTA, FabricToolTags.PICKAXES)
    val WHITE_CLAY_DOOR = createDoor(Material.STONE, MaterialColor.WHITE, FabricToolTags.PICKAXES)

    val PINK_BRICKS = createBlock(
        Material.STONE,
        MaterialColor.PINK_TERRACOTTA,
        2.0F,
        6.0F,
        BlockSoundGroup.STONE,
        FabricToolTags.PICKAXES
    )

    val PINK_BRICKS_STAIRS = createStairs(PINK_BRICKS)
    val PINK_BRICKS_WALL = createWall(PINK_BRICKS)
    val PINK_BRICKS_SLAB = createSlab(PINK_BRICKS)

    val WHITE_BRICKS = createBlock(
        Material.STONE,
        MaterialColor.WHITE,
        2.0F,
        6.0F,
        BlockSoundGroup.STONE,
        FabricToolTags.PICKAXES
    )

    val WHITE_BRICKS_STAIRS = createStairs(WHITE_BRICKS)
    val WHITE_BRICKS_WALL = createWall(WHITE_BRICKS)
    val WHITE_BRICKS_SLAB = createSlab(WHITE_BRICKS)

    val SAKURA_SAPLING = createSapling(SakuraSaplingGenerator())
    val POTTED_SAKURA_SAPLING = FlowerPotBlock(SAKURA_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING))

    fun register() {
        register(CHERRY_PIE, "cherry_pie")

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
        register(SAKURA_SIGN_WALL, "sakura_wall_sign")

        register(SAKURA_DOOR, "sakura_door")
        register(SAKURA_TRAPDOOR, "sakura_trapdoor")

        register(PINK_CLAY_DOOR, "pink_clay_door")
        register(WHITE_CLAY_DOOR, "white_clay_door")

        register(PINK_BRICKS, "pink_bricks")
        register(PINK_BRICKS_STAIRS, "pink_bricks_stairs")
        register(PINK_BRICKS_WALL, "pink_bricks_wall")
        register(PINK_BRICKS_SLAB, "pink_bricks_slab")

        register(WHITE_BRICKS, "white_bricks")
        register(WHITE_BRICKS_STAIRS, "white_bricks_stairs")
        register(WHITE_BRICKS_WALL, "white_bricks_wall")
        register(WHITE_BRICKS_SLAB, "white_bricks_slab")

        register(SAKURA_SAPLING, "sakura_sapling")
        register(POTTED_SAKURA_SAPLING, "potted_sakura_sapling")
    }

    private fun register(block: Block, id: String) {
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, id), block)
    }

    // region: Internal builder functions

    private fun createBarrel(block: Block) = SakuraBarrelBlock(FabricBlockSettings.copyOf(block))

    private fun createBlock(material: Material, color: MaterialColor, strength: Float, sound: BlockSoundGroup) = Block(
        FabricBlockSettings.of(material, color)
            .strength(strength)
            .sounds(sound)
            .breakByHand(true)
    )

    private fun createBlock(
        material: Material,
        color: MaterialColor,
        strength: Float,
        sound: BlockSoundGroup,
        tool: Tag<Item>
    ) = Block(
        FabricBlockSettings.of(material, color)
            .strength(strength)
            .sounds(sound)
            .breakByTool(tool, 0)
    )

    private fun createBlock(
        material: Material,
        color: MaterialColor,
        strength: Float,
        sound: BlockSoundGroup,
        tool: Tag<Item>,
        toolLevel: Int
    ) = Block(
        FabricBlockSettings.of(material, color)
            .strength(strength)
            .sounds(sound)
            .breakByTool(tool, toolLevel)
    )

    private fun createBlock(
        material: Material, color: MaterialColor,
        hardness: Float, resistance: Float,
        sound: BlockSoundGroup
    ) = Block(
        FabricBlockSettings.of(material, color)
            .strength(hardness, resistance)
            .sounds(sound)
            .breakByHand(true)
    )

    private fun createBlock(
        material: Material, color: MaterialColor,
        hardness: Float, resistance: Float,
        sound: BlockSoundGroup,
        tool: Tag<Item>
    ) = Block(
        FabricBlockSettings.of(material, color)
            .strength(hardness, resistance)
            .sounds(sound)
            .breakByTool(tool, 0)
    )

    private fun createBlock(
        material: Material, color: MaterialColor,
        hardness: Float, resistance: Float,
        sound: BlockSoundGroup,
        tool: Tag<Item>,
        toolLevel: Int
    ) = Block(
        FabricBlockSettings.of(material, color)
            .strength(hardness, resistance)
            .sounds(sound)
            .breakByTool(tool, toolLevel)
    )

    private fun createChest(block: Block) = SakuraChestBlock(
        FabricBlockSettings.copyOf(block),
        Supplier { BlockEntities.SAKURA_CHEST }
    )

    private fun createCraftingTable(material: Material, color: MaterialColor) = UtilCraftingTableBlock(
        FabricBlockSettings.of(material, color)
            .strength(2.5F)
            .nonOpaque()
            .breakByTool(FabricToolTags.AXES)
            .sounds(BlockSoundGroup.WOOD)
    )

    private fun createDoor(material: Material, color: MaterialColor, tool: Tag<Item>) = UtilDoorBlock(
        FabricBlockSettings.of(material, color)
            .strength(3.0F)
            .nonOpaque()
            .breakByTool(tool, 0)
    )

    private fun createFence(block: Block) = FenceBlock(FabricBlockSettings.copyOf(block))
    private fun createFenceGate(block: Block) = FenceGateBlock(FabricBlockSettings.copyOf(block))

    private fun createLeafCarpet() = LeafCarpetBlock(
        FabricBlockSettings.of(Material.LEAVES)
            .noCollision()
            .strength(0.2F)
            .sounds(BlockSoundGroup.GRASS)
            .breakByHand(true)
    )

    private fun createLeaves(colour: Int) = SakuraLeavesBlock(
        colour,

        FabricBlockSettings.of(Material.LEAVES, MaterialColor.MAGENTA)
            .strength(0.2F)
            .nonOpaque()
            .sounds(BlockSoundGroup.GRASS)
    )

    private fun createLogBlock(colour: MaterialColor) = BlocksMixin.createLogBlock(colour, colour)

    private fun createSapling(generator: SaplingGenerator) = UtilSaplingBlock(
        generator,

        FabricBlockSettings.of(Material.PLANT, MaterialColor.MAGENTA)
            .noCollision()
            .strength(0F)
            .sounds(BlockSoundGroup.GRASS)
            .ticksRandomly()
            .breakByHand(true)
    )

    private fun createSign(block: Block, type: SignType) = SakuraSignBlock(
        FabricBlockSettings.copyOf(block).noCollision(), type
    )

    private fun createWallSign(block: Block, type: SignType) = SakuraWallSignBlock(
        FabricBlockSettings.copyOf(block).noCollision(), type
    )

    private fun createSlab(block: Block) = SlabBlock(FabricBlockSettings.copyOf(block))
    private fun createStairs(block: Block) = UtilStairsBlock(block.defaultState, FabricBlockSettings.copyOf(block))

    private fun createTrapDoor(material: Material, color: MaterialColor, tool: Tag<Item>) = UtilTrapdoorBlock(
        FabricBlockSettings.of(material, color)
            .strength(3.0F)
            .nonOpaque()
            .breakByTool(tool)
    )

    private fun createWall(block: Block) = WallBlock(FabricBlockSettings.copyOf(block))

    private fun createWoodBlock(color: MaterialColor) = PillarBlock(
        FabricBlockSettings.of(Material.WOOD, color)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .breakByTool(FabricToolTags.AXES)
    )

    private fun createWoodButton(color: MaterialColor) = UtilWoodenButtonBlock(
        FabricBlockSettings.of(Material.WOOD, color)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .breakByTool(FabricToolTags.AXES)
    )

    private fun createWoodPressurePlate(activation: PressurePlateBlock.ActivationRule, colour: MaterialColor) =
        UtilPressurePlate(
            activation,

            FabricBlockSettings.of(Material.WOOD, colour)
                .strength(2.0F)
                .noCollision()
                .sounds(BlockSoundGroup.WOOD)
                .breakByTool(FabricToolTags.AXES)
        )

    // endregion
}
