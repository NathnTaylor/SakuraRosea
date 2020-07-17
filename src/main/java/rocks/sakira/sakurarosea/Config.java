package rocks.sakira.sakurarosea;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final String CATEGORY_GENERATION = "generation";

    public static ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.IntValue BIOME_SPAWN_WEIGHT;

    static {
        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        BUILDER.comment("Biome generation settings")
                .push(CATEGORY_GENERATION);

        BIOME_SPAWN_WEIGHT = BUILDER
                .comment("Sakura biome spawn weight. Set to 0 to disable biome generation.")
                .defineInRange("biome_spawn_weight", 10, 0, Integer.MAX_VALUE);

        BUILDER.pop();

        CONFIG = BUILDER.build();
    }
}
