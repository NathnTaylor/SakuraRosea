package rocks.sakira.sakurarosea.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rocks.sakira.sakurarosea.common.particle.Particles;

import java.util.Random;

public class SakuraLeavesBlock extends LeavesBlock {
    private final int leafColour;

    public SakuraLeavesBlock(int leafColour, Properties properties) {
        super(properties);

        this.leafColour = leafColour;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        super.animateTick(state, world, pos, rand);

        if (rand.nextInt(35) == 0) {
            BlockPos lowerPos = pos.down();

            if (world.isAirBlock(lowerPos)) {
                double r = (leafColour >> 16 & 255) / 255.0F;
                double g = (leafColour >> 8 & 255) / 255.0F;
                double b = (leafColour & 255) / 255.0F;

                double x = ((float) pos.getX() + rand.nextFloat());
                double y = pos.getY() - 0.05D;
                double z = ((float) pos.getZ() + rand.nextFloat());

                world.addParticle(Particles.SAKURA_LEAF, x, y, z, r, g, b);
            }
        }
    }
}
