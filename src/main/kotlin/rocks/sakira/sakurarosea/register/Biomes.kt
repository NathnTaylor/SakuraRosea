package rocks.sakira.sakurarosea.register

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.biome.SpawnSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.placer.SimpleBlockPlacer
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders
import org.apache.commons.lang3.ArrayUtils
import rocks.sakira.sakurarosea.MOD_ID
import rocks.sakira.sakurarosea.mixin.common.BuiltinBiomesAccessor
import rocks.sakira.sakurarosea.mixin.common.SetBaseBiomesLayerMixin
import rocks.sakira.sakurarosea.mixin.common.VanillaLayeredBiomeSourceMixin
import rocks.sakira.sakurarosea.tree.SakuraSaplingGenerator

object Biomes {
    private val LILY_OF_THE_VALLEY_CONFIG = RandomPatchFeatureConfig.Builder(
        SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.defaultState),
        SimpleBlockPlacer()
    ).tries(64).build()

    private val PINK_TULIP_CONFIG = RandomPatchFeatureConfig.Builder(
        SimpleBlockStateProvider(Blocks.PINK_TULIP.defaultState),
        SimpleBlockPlacer()
    ).tries(64).build()

    private val WHITE_TULIP_CONFIG = RandomPatchFeatureConfig.Builder(
        SimpleBlockStateProvider(Blocks.WHITE_TULIP.defaultState),
        SimpleBlockPlacer()
    ).tries(64).build()

    val SAKURA_FOREST = createSakuraBiome()
    val SAKURA_FOREST_HILLS = createSakuraHillBiome()

    val SAKURA_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, Identifier(MOD_ID, "sakura_forest"))
    val SAKURA_FOREST_HILLS_KEY = RegistryKey.of(Registry.BIOME_KEY, Identifier(MOD_ID, "sakura_forest_hills"))

    fun createSakuraBiome(): Biome {
        val spawnSettings = SpawnSettings.Builder()
        val generationSettings = GenerationSettings.Builder()

        addGenerationFeatures(generationSettings)
        addSpawnFeatures(spawnSettings)

        return Biome.Builder()
            .category(Biome.Category.FOREST)
            .depth(0.1F)
            .downfall(0.8F)
            .generationSettings(generationSettings.build())
            .precipitation(Biome.Precipitation.RAIN)
            .scale(0.2F)
            .spawnSettings(spawnSettings.build())
            .temperature(0.7F)

            .effects(
                BiomeEffects.Builder()
                    .fogColor(0xFFFFFF)
                    .foliageColor(0xFF97C9)
                    .grassColor(0x77BD4F)
                    .skyColor(0x78A7FF)
                    .waterColor(0x3F76E4)
                    .waterFogColor(0x50533)
                    .build()
            )

            .build()
    }

    fun createSakuraHillBiome(): Biome {
        val spawnSettings = SpawnSettings.Builder()
        val generationSettings = GenerationSettings.Builder()

        addGenerationFeatures(generationSettings)
        addSpawnFeatures(spawnSettings)

        return Biome.Builder()
            .category(Biome.Category.FOREST)
            .depth(0.45F)
            .downfall(0.8F)
            .generationSettings(generationSettings.build())
            .precipitation(Biome.Precipitation.RAIN)
            .scale(0.3F)
            .spawnSettings(spawnSettings.build())
            .temperature(0.7F)

            .effects(
                BiomeEffects.Builder()
                    .fogColor(0xFFFFFF)
                    .foliageColor(0xFF97C9)
                    .grassColor(0x77BD4F)
                    .skyColor(0x78A7FF)
                    .waterColor(0x3F76E4)
                    .waterFogColor(0x50533)
                    .build()
            )

            .build()
    }

    private fun addSpawnFeatures(spawnSettings: SpawnSettings.Builder) {
        DefaultBiomeFeatures.addPlainsMobs(spawnSettings)

        spawnSettings.spawn(
            SpawnGroup.CREATURE,
            SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4)
        )

        spawnSettings.playerSpawnFriendly()
    }

    private fun addGenerationFeatures(generationSettings: GenerationSettings.Builder) {
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        DefaultBiomeFeatures.addDefaultLakes(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        DefaultBiomeFeatures.addDungeons(generationSettings)
        DefaultBiomeFeatures.addForestFlowers(generationSettings)
        DefaultBiomeFeatures.addForestGrass(generationSettings)
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings)
        DefaultBiomeFeatures.addLandCarvers(generationSettings)
        DefaultBiomeFeatures.addMineables(generationSettings)
        DefaultBiomeFeatures.addSprings(generationSettings)

        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings)
        generationSettings.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL)
        generationSettings.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)

        generationSettings.feature(
            GenerationStep.Feature.SURFACE_STRUCTURES,

            Feature.TREE
                .configure(SakuraSaplingGenerator.ALT_SAKURA_TREE_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally())
        )

        generationSettings.feature(
            GenerationStep.Feature.SURFACE_STRUCTURES,

            Feature.TREE
                .configure(SakuraSaplingGenerator.SAKURA_TREE_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally())
        )

        generationSettings.feature(
            GenerationStep.Feature.SURFACE_STRUCTURES,

            Feature.TREE
                .configure(SakuraSaplingGenerator.WHITE_SAKURA_TREE_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally())
        )

        generationSettings.feature(
            GenerationStep.Feature.SURFACE_STRUCTURES,

            Feature.FLOWER
                .configure(LILY_OF_THE_VALLEY_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE)
        )

        generationSettings.feature(
            GenerationStep.Feature.SURFACE_STRUCTURES,

            Feature.FLOWER
                .configure(PINK_TULIP_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE)
        )

        generationSettings.feature(
            GenerationStep.Feature.SURFACE_STRUCTURES,

            Feature.FLOWER
                .configure(WHITE_TULIP_CONFIG)
                .decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE)
        )
    }

    fun register() {
        register(SAKURA_FOREST, SAKURA_FOREST_KEY)
        register(SAKURA_FOREST_HILLS, SAKURA_FOREST_HILLS_KEY)

        addToVanillaGeneration()
        setBiomeLayers()
    }

    private fun register(biome: Biome, id: RegistryKey<Biome>) {
        Registry.register(BuiltinRegistries.BIOME, id.value, biome)
        BuiltinBiomesAccessor.getRawIdMap()[BuiltinRegistries.BIOME.getRawId(biome)] = id
    }

    private fun addToVanillaGeneration() {
        val biomes = VanillaLayeredBiomeSourceMixin.getBiomes().toMutableList()

        biomes.add(SAKURA_FOREST_KEY)
        biomes.add(SAKURA_FOREST_HILLS_KEY)

        VanillaLayeredBiomeSourceMixin.setBiomes(biomes)
    }

    private fun setBiomeLayers() {
        var temperate = SetBaseBiomesLayerMixin.getTemperateBiomes()

        temperate = ArrayUtils.add(temperate, BuiltinRegistries.BIOME.getRawId(SAKURA_FOREST))
        temperate = ArrayUtils.add(temperate, BuiltinRegistries.BIOME.getRawId(SAKURA_FOREST_HILLS))

        SetBaseBiomesLayerMixin.setTemperateBiomes(temperate)
    }
}
