package rocks.sakira.sakurarosea.mixin.common;

import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(FlowerPotBlock.class)
public interface FlowerPotBlockMixin {
    @Accessor("CONTENT_TO_POTTED")
    Map<Block, Block> getPots();

    @Accessor("CONTENT_TO_POTTED")
    void setPots(Map<Block, Block> pots);
}
