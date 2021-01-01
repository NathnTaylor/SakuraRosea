package io.github.faecraft.sakurarosea.mixin.common;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(VanillaLayeredBiomeSource.class)
public interface VanillaLayeredBiomeSourceMixin {
    @Accessor("BIOMES")
    static List<RegistryKey<Biome>> getBiomes() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
    @Accessor("BIOMES")
    static void setBiomes(List<RegistryKey<Biome>> biomes) throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
