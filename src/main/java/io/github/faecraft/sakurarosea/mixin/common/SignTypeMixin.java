package io.github.faecraft.sakurarosea.mixin.common;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignType.class)
public interface SignTypeMixin {
    @Invoker("register")
    static SignType register(SignType type) throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
