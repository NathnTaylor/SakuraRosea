package rocks.sakira.sakurarosea.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndRodBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class RopeBlock extends EndRodBlock {
    protected static final VoxelShape ROPE_VERTICAL_AABB = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    protected static final VoxelShape ROPE_NS_AABB = Block.makeCuboidShape(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 16.0D);
    protected static final VoxelShape ROPE_EW_AABB = Block.makeCuboidShape(0.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);

    protected RopeBlock(Properties builder) {
        super(builder);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        // Don't animate.
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING).getAxis()) {
            case X:
            default:
                return ROPE_EW_AABB;
            case Z:
                return ROPE_NS_AABB;
            case Y:
                return ROPE_VERTICAL_AABB;
        }
    }
}
