package rocks.sakira.sakurarosea.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import rocks.sakira.sakurarosea.common.block.trees.SakuraTree;

import java.util.Random;

public class SakuraSaplingBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;

    protected SakuraSaplingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);

        builder.add(STAGE);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (state.get(STAGE) == 0) {
            worldIn.setBlockState(
                    pos,
                    state.func_235896_a_(STAGE),  // cycle
                    4);
        } else {
            SakuraTree tree = new SakuraTree();
            tree.func_230339_a_(worldIn, worldIn.getChunkProvider().getChunkGenerator(), pos, state, rand);  // place
        }
    }
}
