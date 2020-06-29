package rocks.sakira.sakurarosea.common.feature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rocks.sakira.sakurarosea.common.block.trees.SakuraTree;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

public class Features {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, MOD_ID);

    public static final RegistryObject<Feature<?>> SAKURA_TREE_FEATURE = REGISTER.register(
            "sakura_tree_feature",

            () -> new TreeFeature(BaseTreeFeatureConfig.field_236676_a_.withDefault(SakuraTree.SAKURA_TREE_CONFIG))
    );
}
