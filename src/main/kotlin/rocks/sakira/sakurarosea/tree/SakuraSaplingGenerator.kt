package rocks.sakira.sakurarosea.tree

import net.minecraft.block.BlockState
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.UniformIntDistribution
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import rocks.sakira.sakurarosea.MOD_ID
import rocks.sakira.sakurarosea.register.Blocks
import java.util.*

class SakuraSaplingGenerator : SaplingGenerator() {
    companion object {
        private fun createConfig(leaves: BlockState): TreeFeatureConfig = TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(Blocks.SAKURA_LOG.defaultState),
            SimpleBlockStateProvider(leaves),
            LargeOakFoliagePlacer(
                UniformIntDistribution.of(3, 0),
                UniformIntDistribution.of(3, 0),
                4
            ),
            LargeOakTrunkPlacer(7, 7, 11),
            ThreeLayersFeatureSize(6, 3, 3, 2, 1, OptionalInt.empty())
        ).build()

        val SAKURA_TREE_CONFIG: TreeFeatureConfig = createConfig(Blocks.SAKURA_LEAVES.defaultState)
        val ALT_SAKURA_TREE_CONFIG: TreeFeatureConfig = createConfig(Blocks.SAKURA_LEAVES_ALT.defaultState)
        val WHITE_SAKURA_TREE_CONFIG: TreeFeatureConfig = createConfig(Blocks.SAKURA_LEAVES_WHITE.defaultState)

        val SAKURA_CONFIG = Feature.TREE.configure(SAKURA_TREE_CONFIG)
        val ALT_SAKURA_CONFIG = Feature.TREE.configure(ALT_SAKURA_TREE_CONFIG)
        val WHITE_SAKURA_CONFIG = Feature.TREE.configure(WHITE_SAKURA_TREE_CONFIG)

        fun register() {
            Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                Identifier(MOD_ID, "sakura_tree"),
                SAKURA_CONFIG
            )

            Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                Identifier(MOD_ID, "alt_sakura_tree"),
                ALT_SAKURA_CONFIG
            )

            Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                Identifier(MOD_ID, "white_sakura_tree"),
                WHITE_SAKURA_CONFIG
            )
        }
    }

    override fun createTreeFeature(random: Random, bl: Boolean): ConfiguredFeature<TreeFeatureConfig, *> =
        when (random.nextInt(3)) {  // Randomly pick a tree type
            0 -> SAKURA_CONFIG
            1 -> ALT_SAKURA_CONFIG

            else -> WHITE_SAKURA_CONFIG
        }
}
