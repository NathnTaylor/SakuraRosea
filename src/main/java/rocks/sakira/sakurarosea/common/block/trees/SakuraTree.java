package rocks.sakira.sakurarosea.common.block.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import rocks.sakira.sakurarosea.common.block.Blocks;

import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends Tree {
    public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG;
//    public static final BaseTreeFeatureConfig HUGE_SAKURA_TREE_CONFIG;

    public static final BaseTreeFeatureConfig ALT_SAKURA_TREE_CONFIG;
//    public static final BaseTreeFeatureConfig HUGE_ALT_SAKURA_TREE_CONFIG;

    public static final BaseTreeFeatureConfig WHITE_SAKURA_TREE_CONFIG;
//    public static final BaseTreeFeatureConfig HUGE_WHITE_SAKURA_TREE_CONFIG;

    static {
        SAKURA_TREE_CONFIG = (
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.SAKURA_LEAVES_BLOCK.get().getDefaultState()),

                        new BlobFoliagePlacer(2, 0, 0, 0, 3),
                        new StraightTrunkPlacer(3, 3, 5),  // TODO: Still a bit weird
                        new TwoLayerFeature(2, 1, 2)
                )
//                        .baseHeight(6)
//                        .foliageHeight(3)
//                        .heightRandA(3)
//                        .ignoreVines()
//                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        ALT_SAKURA_TREE_CONFIG = (
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.ALT_SAKURA_LEAVES_BLOCK.get().getDefaultState()),

                        new BlobFoliagePlacer(2, 0, 0, 0, 3),
                        new StraightTrunkPlacer(3, 3, 5),  // TODO: Still a bit weird
                        new TwoLayerFeature(2, 1, 2)
                )
//                        .baseHeight(6)
//                        .foliageHeight(3)
//                        .heightRandA(3)
//                        .ignoreVines()
//                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        WHITE_SAKURA_TREE_CONFIG = (
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.WHITE_SAKURA_LEAVES_BLOCK.get().getDefaultState()),

                        new BlobFoliagePlacer(2, 0, 0, 0, 3),
                        new StraightTrunkPlacer(3, 3, 5),  // TODO: Still a bit weird
                        new TwoLayerFeature(2, 1, 2)
                )
//                        .baseHeight(6)
//                        .foliageHeight(3)
//                        .heightRandA(3)
//                        .ignoreVines()
//                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        // TODO: Figure these out when Forge fixes more things, there are no good examples yet
        // TODO: (and besides we already weren't using these)

//        HUGE_SAKURA_TREE_CONFIG = (
//                new HugeTreeFeatureConfig.Builder(
//                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
//                        new SimpleBlockStateProvider(Blocks.SAKURA_LEAVES_BLOCK.get().getDefaultState())
//                )
//                        .baseHeight(5)
//                        .crownHeight(5)
//                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
//        ).build();
//
//        HUGE_ALT_SAKURA_TREE_CONFIG = (
//                new HugeTreeFeatureConfig.Builder(
//                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
//                        new SimpleBlockStateProvider(Blocks.ALT_SAKURA_LEAVES_BLOCK.get().getDefaultState())
//                )
//                        .baseHeight(5)
//                        .crownHeight(5)
//                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
//        ).build();
//
//        HUGE_WHITE_SAKURA_TREE_CONFIG = (
//                new HugeTreeFeatureConfig.Builder(
//                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
//                        new SimpleBlockStateProvider(Blocks.WHITE_SAKURA_LEAVES_BLOCK.get().getDefaultState())
//                )
//                        .baseHeight(5)
//                        .crownHeight(5)
//                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
//        ).build();
    }

    @Nullable
    @Override
    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        int val = randomIn.nextInt(3);

        switch (val) {
            case 0:
                return Feature.field_236291_c_.withConfiguration(SAKURA_TREE_CONFIG);
            case 1:
                return Feature.field_236291_c_.withConfiguration(ALT_SAKURA_TREE_CONFIG);
            default:
                return Feature.field_236291_c_.withConfiguration(WHITE_SAKURA_TREE_CONFIG);
        }
    }
}
