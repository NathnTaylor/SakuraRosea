package rocks.sakira.sakurarosea.tree

import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.world.gen.UniformIntDistribution
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
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
    }

    override fun createTreeFeature(random: Random, bl: Boolean): ConfiguredFeature<TreeFeatureConfig, *> =
        when (random.nextInt(3)) {  // Randomly pick a tree type
            0 -> Feature.TREE.configure(SAKURA_TREE_CONFIG)
            1 -> Feature.TREE.configure(ALT_SAKURA_TREE_CONFIG)

            else -> Feature.TREE.configure(WHITE_SAKURA_TREE_CONFIG)
        }
}
