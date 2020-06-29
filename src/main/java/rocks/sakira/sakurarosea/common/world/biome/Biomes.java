package rocks.sakira.sakurarosea.common.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

public class Biomes {
    public static final DeferredRegister<Biome> REGISTER = new DeferredRegister<>(ForgeRegistries.BIOMES, MOD_ID);

    public static final RegistryObject<Biome> SAKURA_FOREST_HILLS_BIOME = REGISTER.register(
            "sakura_forest_hills",

            SakuraForestHillsBiome::new
    );

    public static final RegistryObject<Biome> SAKURA_FOREST_BIOME = REGISTER.register(
            "sakura_forest",

            SakuraForestBiome::new
    );

    public static void addFeatures() {
        ModBiomeFeatures.addSakuraFeatures(SAKURA_FOREST_HILLS_BIOME.get());
        ModBiomeFeatures.addSakuraFeatures(SAKURA_FOREST_BIOME.get());
    }

    public static void registerSpawn() {
        BiomeManager.addSpawnBiome(SAKURA_FOREST_BIOME.get());
    }

    public static void registerTypes() {
        BiomeDictionary.addTypes(
                SAKURA_FOREST_BIOME.get(),
                BiomeDictionary.Type.FOREST,

                BiomeDictionary.Type.OVERWORLD
        );

        BiomeDictionary.addTypes(
                SAKURA_FOREST_HILLS_BIOME.get(),
                BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS,

                BiomeDictionary.Type.OVERWORLD
        );
    }

    public static void registerEntries() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAKURA_FOREST_BIOME.get(), 10));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAKURA_FOREST_HILLS_BIOME.get(), 10));
    }
}
