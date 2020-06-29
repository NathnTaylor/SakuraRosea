package rocks.sakira.sakurarosea.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import rocks.sakira.sakurarosea.common.tileentities.SakuraChestTileEntity;
import rocks.sakira.sakurarosea.common.tileentities.TileEntities;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class SakuraChestBlock extends ChestBlock {
    private static final SakuraChestBlock.InventoryFactory<IInventory> I_INVENTORY_FACTORY;
    private static final SakuraChestBlock.InventoryFactory<INamedContainerProvider> I_NAMED_CONTAINER_PROVIDER_FACTORY;

    static {
        I_INVENTORY_FACTORY = new SakuraChestBlock.InventoryFactory<IInventory>() {
            @Override
            public IInventory forDouble(SakuraChestTileEntity SakuraChestTileEntity, SakuraChestTileEntity SakuraChestTileEntity1) {
                return new DoubleSidedInventory(SakuraChestTileEntity, SakuraChestTileEntity1);
            }

            @Override
            public IInventory forSingle(SakuraChestTileEntity SakuraChestTileEntity) {
                return SakuraChestTileEntity;
            }
        };
        I_NAMED_CONTAINER_PROVIDER_FACTORY = new SakuraChestBlock.InventoryFactory<INamedContainerProvider>() {
            @Override
            public INamedContainerProvider forDouble(final SakuraChestTileEntity SakuraChestTileEntity, final SakuraChestTileEntity SakuraChestTileEntity1) {
                final IInventory lvt_3_1_ = new DoubleSidedInventory(SakuraChestTileEntity, SakuraChestTileEntity1);
                return new INamedContainerProvider() {
                    @Nullable
                    @Override
                    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                        if (SakuraChestTileEntity.canOpen(p_createMenu_3_) && SakuraChestTileEntity1.canOpen(p_createMenu_3_)) {
                            SakuraChestTileEntity.fillWithLoot(p_createMenu_2_.player);
                            SakuraChestTileEntity1.fillWithLoot(p_createMenu_2_.player);
                            return ChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_, lvt_3_1_);
                        } else {
                            return null;
                        }
                    }

                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("container.sakurarosea.sakura_double_chest");
                    }
                };
            }

            @Override
            public INamedContainerProvider forSingle(SakuraChestTileEntity SakuraChestTileEntity) {
                return SakuraChestTileEntity;
            }
        };
    }

    private final LazyValue<TileEntityType<SakuraChestTileEntity>> tileEntityType;

    public SakuraChestBlock(Supplier<TileEntityType<SakuraChestTileEntity>> tileEntityType) {
        super(
                Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD),
                TileEntities.SAKURA_CHEST_ENTITY::get
        );

        this.tileEntityType = new LazyValue<>(tileEntityType);
    }

    @Nullable
    public static IInventory getInventory(BlockState blockState, World world, BlockPos blockPos, boolean p_220105_3_) {
        return invokeFactory(blockState, world, blockPos, p_220105_3_, I_INVENTORY_FACTORY);
    }

    public static boolean isBlocked(IWorld world, BlockPos blockPos) {
        return isBelowSolidBlock(world, blockPos) || isCatSittingOn(world, blockPos);
    }

    private static boolean isBelowSolidBlock(IBlockReader blockReader, BlockPos blockPos) {
        BlockPos up = blockPos.up();
        return blockReader.getBlockState(up).isNormalCube(blockReader, up);
    }

    @Nullable
    private static <T> T invokeFactory(BlockState blockState, IWorld world, BlockPos blockPos, boolean p_220106_3_, SakuraChestBlock.InventoryFactory<T> inventoryFactory) {
        TileEntity tileEntity = world.getTileEntity(blockPos);
        if (!(tileEntity instanceof SakuraChestTileEntity)) {
            return null;
        } else if (!p_220106_3_ && isBlocked(world, blockPos)) {
            return null;
        } else {
            SakuraChestTileEntity SakuraChestTileEntity = (SakuraChestTileEntity) tileEntity;
            ChestType chestType = blockState.get(TYPE);
            if (chestType == ChestType.SINGLE) {
                return inventoryFactory.forSingle(SakuraChestTileEntity);
            } else {
                BlockPos offset = blockPos.offset(getDirectionToAttached(blockState));
                BlockState offsetBlockState = world.getBlockState(offset);
                if (offsetBlockState.getBlock() == blockState.getBlock()) {
                    ChestType offsetChestType = offsetBlockState.get(TYPE);
                    if (offsetChestType != ChestType.SINGLE && chestType != offsetChestType && offsetBlockState.get(FACING) == blockState.get(FACING)) {
                        if (!p_220106_3_ && isBlocked(world, offset)) {
                            return null;
                        }

                        TileEntity offsetTileEntity = world.getTileEntity(offset);
                        if (offsetTileEntity instanceof SakuraChestTileEntity) {
                            return inventoryFactory.forDouble(
                                    chestType == ChestType.RIGHT ? SakuraChestTileEntity : (SakuraChestTileEntity) offsetTileEntity,
                                    chestType == ChestType.RIGHT ? (SakuraChestTileEntity) offsetTileEntity : SakuraChestTileEntity
                            );
                        }
                    }
                }
                return inventoryFactory.forSingle(SakuraChestTileEntity);
            }
        }
    }

    private static boolean isCatSittingOn(IWorld world, BlockPos blockPos) {
        List<CatEntity> catEntities = world.getEntitiesWithinAABB(CatEntity.class, new AxisAlignedBB(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), blockPos.getX() + 1, blockPos.getY() + 2, blockPos.getZ() + 1));
        for (CatEntity catEntity : catEntities) {
            if (catEntity.isSitting()) return true;
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        if (itemStack.hasDisplayName()) {
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (tileEntity instanceof SakuraChestTileEntity) {
                ((SakuraChestTileEntity) tileEntity).setCustomName(itemStack.getDisplayName());
            }
        }

    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World world, BlockPos blockPos) {
        return Container.calcRedstoneFromInventory(getInventory(blockState, world, blockPos, false));
    }

    @Override
    @Nullable
    public INamedContainerProvider getContainer(BlockState blockState, World world, BlockPos blockPos) {
        return invokeFactory(blockState, world, blockPos, false, I_NAMED_CONTAINER_PROVIDER_FACTORY);
    }

    public TileEntityType<SakuraChestTileEntity> getTileEntityType() {
        return this.tileEntityType.getValue();
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader blockReader) {
        return this.getTileEntityType().create();
    }

    interface InventoryFactory<T> {
        T forDouble(SakuraChestTileEntity var1, SakuraChestTileEntity var2);

        T forSingle(SakuraChestTileEntity var1);
    }
}
