package rocks.sakira.sakurarosea.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import rocks.sakira.sakurarosea.common.block.trees.SakuraTree;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierSaplingBlock extends SaplingBlock {
    private final Supplier<Tree> treeSupplier;

    protected SupplierSaplingBlock(Supplier<Tree> treeSupplier, Properties properties) {
        super(null, properties);

        this.treeSupplier = treeSupplier;
    }

    @Override
    public void func_226942_a_(ServerWorld p_226942_1_, BlockPos p_226942_2_, BlockState p_226942_3_, Random p_226942_4_) {
        if (p_226942_3_.get(STAGE) == 0) {
            p_226942_1_.setBlockState(p_226942_2_, p_226942_3_.cycle(STAGE), 4);
        } else {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(p_226942_1_, p_226942_4_, p_226942_2_)) return;
            this.treeSupplier.get().place(p_226942_1_, p_226942_1_.getChunkProvider().getChunkGenerator(), p_226942_2_, p_226942_3_, p_226942_4_);
        }
    }
}
