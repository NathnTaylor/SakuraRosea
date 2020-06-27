package rocks.sakira.sakurarosea.common.block;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import rocks.sakira.sakurarosea.common.tileentities.SakuraBarrelTileEntity;
import rocks.sakira.sakurarosea.common.tileentities.TileEntities;

import javax.annotation.Nullable;
import java.util.Random;

public class SakuraBarrelBlock extends BarrelBlock {
    private final LazyValue<TileEntityType<SakuraBarrelTileEntity>> tileEntityType;

    public SakuraBarrelBlock() {
        super(Block.Properties.from(Blocks.BARREL));

        this.tileEntityType = new LazyValue<>(TileEntities.SAKURA_BARREL_ENTITY);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand handIn, BlockRayTraceResult hit) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(blockPos);

            if (tileEntity instanceof SakuraBarrelTileEntity) {
                playerEntity.openContainer((SakuraBarrelTileEntity) tileEntity);
                playerEntity.addStat(Stats.OPEN_BARREL);
            }
        }

        return ActionResultType.SUCCESS;
    }

    public TileEntityType<SakuraBarrelTileEntity> getTileEntityType() {
        return this.tileEntityType.getValue();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return this.getTileEntityType().create();
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos blockPos, Random rand) {
        TileEntity tileEntity = world.getTileEntity(blockPos);
        if (tileEntity instanceof SakuraBarrelTileEntity) {
            ((SakuraBarrelTileEntity) tileEntity).tick();
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasDisplayName()) {
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (tileEntity instanceof SakuraBarrelTileEntity) {
                ((SakuraBarrelTileEntity) tileEntity).setCustomName(itemStack.getDisplayName());
            }
        }
    }
}
