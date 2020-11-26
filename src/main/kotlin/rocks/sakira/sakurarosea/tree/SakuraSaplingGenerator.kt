package rocks.sakira.sakurarosea.tree

import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.UniformIntDistribution
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import rocks.sakira.sakurarosea.MOD_ID
import rocks.sakira.sakurarosea.register.Blocks
import java.util.*

class SakuraSaplingGenerator : SaplingGenerator() {
    companion object {
        val SAKURA_TREE_CONFIG: TreeFeatureConfig = TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(Blocks.SAKURA_LOG.defaultState),
            SimpleBlockStateProvider(Blocks.SAKURA_LEAVES.defaultState),
            BlobFoliagePlacer(
                UniformIntDistribution.of(3, 0),
                UniformIntDistribution.of(0, 0),
                3
            ),
            StraightTrunkPlacer(3, 3, 5),
            TwoLayersFeatureSize(2, 1, 2)
        ).build()

        val ALT_SAKURA_TREE_CONFIG: TreeFeatureConfig = TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(Blocks.SAKURA_LOG.defaultState),
            SimpleBlockStateProvider(Blocks.SAKURA_LEAVES_ALT.defaultState),
            BlobFoliagePlacer(
                UniformIntDistribution.of(3, 0),
                UniformIntDistribution.of(0, 0),
                3
            ),
            StraightTrunkPlacer(3, 3, 5),
            TwoLayersFeatureSize(2, 1, 2)
        ).build()

        val WHITE_SAKURA_TREE_CONFIG: TreeFeatureConfig = TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(Blocks.SAKURA_LOG.defaultState),
            SimpleBlockStateProvider(Blocks.SAKURA_LEAVES_WHITE.defaultState),
            BlobFoliagePlacer(
                UniformIntDistribution.of(3, 0),
                UniformIntDistribution.of(0, 0),
                3
            ),
            StraightTrunkPlacer(3, 3, 5),
            TwoLayersFeatureSize(2, 1, 2)
        ).build()

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
