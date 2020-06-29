package rocks.sakira.sakurarosea.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelItem extends BlockItem {
    private final int burnTime;

    public FuelItem(Block block, Item.Properties properties, int burnTimeIn) {
        super(block, properties);
        burnTime = burnTimeIn;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return burnTime;
    }
}
