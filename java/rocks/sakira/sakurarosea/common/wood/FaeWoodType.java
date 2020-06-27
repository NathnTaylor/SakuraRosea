package rocks.sakira.sakurarosea.common.wood;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.block.WoodType;

import java.util.Set;

public class FaeWoodType extends WoodType {
    private static final Set<WoodType> VALUES = new ObjectArraySet<>();
    public static final WoodType SAKURA = register(new FaeWoodType("sakura"));

    public FaeWoodType(String nameIn) {
        super(nameIn);
    }

    private static WoodType register(WoodType woodTypeIn) {
        VALUES.add(woodTypeIn);
        return woodTypeIn;
    }
}
