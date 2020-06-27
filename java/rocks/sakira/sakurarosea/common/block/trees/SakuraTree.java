package rocks.sakira.sakurarosea.common.block.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;
import rocks.sakira.sakurarosea.common.block.Blocks;

import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends Tree {
    public static final TreeFeatureConfig SAKURA_TREE_CONFIG;
    public static final HugeTreeFeatureConfig HUGE_SAKURA_TREE_CONFIG;

    public static final TreeFeatureConfig ALT_SAKURA_TREE_CONFIG;
    public static final HugeTreeFeatureConfig HUGE_ALT_SAKURA_TREE_CONFIG;

    public static final TreeFeatureConfig WHITE_SAKURA_TREE_CONFIG;
    public static final HugeTreeFeatureConfig HUGE_WHITE_SAKURA_TREE_CONFIG;

    static {
        SAKURA_TREE_CONFIG = (
                new TreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.SAKURA_LEAVES_BLOCK.get().getDefaultState()),
                        new BlobFoliagePlacer(3, 0)
                )
                        .baseHeight(6)
                        .foliageHeight(3)
                        .heightRandA(3)
                        .ignoreVines()
                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        ALT_SAKURA_TREE_CONFIG = (
                new TreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.ALT_SAKURA_LEAVES_BLOCK.get().getDefaultState()),
                        new BlobFoliagePlacer(3, 0)
                )
                        .baseHeight(6)
                        .foliageHeight(3)
                        .heightRandA(3)
                        .ignoreVines()
                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        WHITE_SAKURA_TREE_CONFIG = (
                new TreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.WHITE_SAKURA_LEAVES_BLOCK.get().getDefaultState()),
                        new BlobFoliagePlacer(3, 0)
                )
                        .baseHeight(6)
                        .foliageHeight(3)
                        .heightRandA(3)
                        .ignoreVines()
                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        HUGE_SAKURA_TREE_CONFIG = (
                new HugeTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.SAKURA_LEAVES_BLOCK.get().getDefaultState())
                )
                        .baseHeight(5)
                        .crownHeight(5)
                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        HUGE_ALT_SAKURA_TREE_CONFIG = (
                new HugeTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.ALT_SAKURA_LEAVES_BLOCK.get().getDefaultState())
                )
                        .baseHeight(5)
                        .crownHeight(5)
                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();

        HUGE_WHITE_SAKURA_TREE_CONFIG = (
                new HugeTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.SAKURA_LOG_BLOCK.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.WHITE_SAKURA_LEAVES_BLOCK.get().getDefaultState())
                )
                        .baseHeight(5)
                        .crownHeight(5)
                        .setSapling((IPlantable) Blocks.SAKURA_SAPLING_BLOCK.get())
        ).build();
    }

    @Nullable
    @Override
    public ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        int val = randomIn.nextInt(3);

        switch (val) {
            case 0:
                return Feature.NORMAL_TREE.withConfiguration(SAKURA_TREE_CONFIG);
            case 1:
                return Feature.NORMAL_TREE.withConfiguration(ALT_SAKURA_TREE_CONFIG);
            default:
                return Feature.NORMAL_TREE.withConfiguration(WHITE_SAKURA_TREE_CONFIG);
        }
    }
}
