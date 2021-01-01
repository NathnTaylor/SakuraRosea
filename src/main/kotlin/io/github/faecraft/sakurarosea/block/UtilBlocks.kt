package io.github.faecraft.sakurarosea.block

import net.minecraft.block.*
import net.minecraft.block.sapling.SaplingGenerator

class UtilCakeBlock(settings: Settings) : CakeBlock(settings)
class UtilCraftingTableBlock(settings: Settings) : CraftingTableBlock(settings)
class UtilDoorBlock(settings: Settings) : DoorBlock(settings)
class UtilPressurePlate(type: ActivationRule, settings: Settings) : PressurePlateBlock(type, settings)
class UtilSaplingBlock(generator: SaplingGenerator, settings: Settings) : SaplingBlock(generator, settings)
class UtilStairsBlock(baseBlockState: BlockState, settings: Settings) : StairsBlock(baseBlockState, settings)
class UtilTrapdoorBlock(settings: Settings) : TrapdoorBlock(settings)
class UtilWoodenButtonBlock(settings: Settings) : WoodenButtonBlock(settings)
