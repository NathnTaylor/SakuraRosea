package rocks.sakira.sakurarosea.common.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import rocks.sakira.sakurarosea.common.block.trees.SakuraTree;

public class ModBiomeFeatures {
    private static final BlockState LILY_OF_THE_VALLEY = Blocks.LILY_OF_THE_VALLEY.getDefaultState();
    public static final BlockClusterFeatureConfig LILY_OF_THE_VALLEY_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(LILY_OF_THE_VALLEY), new SimpleBlockPlacer())).tries(64).build();
    private static final BlockState PINK_TULIP = Blocks.PINK_TULIP.getDefaultState();
    public static final BlockClusterFeatureConfig PINK_TULIP_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(PINK_TULIP), new SimpleBlockPlacer())).tries(64).build();
    private static final BlockState WHITE_TULIP = Blocks.WHITE_TULIP.getDefaultState();
    public static final BlockClusterFeatureConfig WHITE_TULIP_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WHITE_TULIP), new SimpleBlockPlacer())).tries(64).build();

    public static void addSakuraFeatures(Biome biomeIn) {
        biomeIn.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,

                Feature.field_236291_c_
                        .withConfiguration(SakuraTree.SAKURA_TREE_CONFIG)
                        .withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(2)))
        );

        biomeIn.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,

                Feature.field_236291_c_
                        .withConfiguration(SakuraTree.ALT_SAKURA_TREE_CONFIG)
                        .withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(2)))
        );

        biomeIn.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,

                Feature.field_236291_c_
                        .withConfiguration(SakuraTree.WHITE_SAKURA_TREE_CONFIG)
                        .withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(2)))
        );

        biomeIn.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,

                Feature.FLOWER.withConfiguration(PINK_TULIP_CONFIG)
                        .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2)))
        );

        biomeIn.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,

                Feature.FLOWER.withConfiguration(WHITE_TULIP_CONFIG)
                        .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2)))
        );

        biomeIn.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,

                Feature.FLOWER.withConfiguration(LILY_OF_THE_VALLEY_CONFIG)
                        .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2)))
        );
    }
}
