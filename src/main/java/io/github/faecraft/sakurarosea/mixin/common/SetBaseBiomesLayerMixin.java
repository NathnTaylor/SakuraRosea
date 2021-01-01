package io.github.faecraft.sakurarosea.mixin.common;

import net.minecraft.world.biome.layer.SetBaseBiomesLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SetBaseBiomesLayer.class)
public interface SetBaseBiomesLayerMixin {
    @Accessor("TEMPERATE_BIOMES")
    static int[] getTemperateBiomes() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @Accessor("TEMPERATE_BIOMES")
    static void setTemperateBiomes(int[] biomes) throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
