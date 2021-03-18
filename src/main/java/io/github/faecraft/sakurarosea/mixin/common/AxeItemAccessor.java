package io.github.faecraft.sakurarosea.mixin.common;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(AxeItem.class)
public interface AxeItemAccessor {
    @Accessor("STRIPPED_BLOCKS")
    static Map<Block, Block> getStrippedBlocks()  throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    @Accessor("STRIPPED_BLOCKS")
    static void setStrippedBlocks(Map<Block, Block> strippedBlocks)  throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
