package rocks.sakira.sakurarosea.mixin.common;

import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ComposterBlock.class)
public interface ComposterBlockMixin {
    @Invoker("registerCompostableItem")
    static void registerCompostableItem(float levelIncreaseChance, ItemConvertible item) throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
