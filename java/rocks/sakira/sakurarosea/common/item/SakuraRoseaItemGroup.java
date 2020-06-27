package rocks.sakira.sakurarosea.common.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SakuraRoseaItemGroup extends ItemGroup {
    public SakuraRoseaItemGroup(String label) {
        super(label);
    }

    public SakuraRoseaItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.SAKURA_SAPLING_BLOCK_ITEM.get());
    }
}
