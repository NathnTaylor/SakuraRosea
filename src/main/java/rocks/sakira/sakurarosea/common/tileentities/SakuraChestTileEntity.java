package rocks.sakira.sakurarosea.common.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.WoodType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import rocks.sakira.sakurarosea.Constants;
import rocks.sakira.sakurarosea.common.block.SakuraChestBlock;

import javax.annotation.Nullable;

public class SakuraChestTileEntity extends ChestTileEntity implements IChestLid, ITickableTileEntity {
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);
    private float lidAngle;
    private float prevLidAngle;
    private int numPlayersUsing;
    private int ticksSinceSync;
    private LazyOptional<IItemHandlerModifiable> chestHandler;

    public SakuraChestTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public static int getNumPlayerUsing(World world, LockableTileEntity lockableTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayerUsing) {
        if (!world.isRemote && numPlayerUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0) {
            numPlayerUsing = getNumPlayerUsingImpl(world, lockableTileEntity, x, y, z);
        }

        return numPlayerUsing;
    }

    public static int getNumPlayerUsingImpl(World world, LockableTileEntity lockableTileEntity, int x, int y, int z) {
        int i = 0;
        for (PlayerEntity playerentity : world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((float) x - 5.0F, (float) y - 5.0F, (float) z - 5.0F, (float) (x + 1) + 5.0F, (float) (y + 1) + 5.0F, (float) (z + 1) + 5.0F))) {
            if (playerentity.openContainer instanceof ChestContainer) {
                IInventory inventory = ((ChestContainer) playerentity.openContainer).getLowerChestInventory();
                if (inventory == lockableTileEntity || inventory instanceof DoubleSidedInventory && ((DoubleSidedInventory) inventory).isPartOfLargeChest(lockableTileEntity)) {
                    ++i;
                }
            }
        }
        return i;
    }

    public ResourceLocation getChestResourceLocation(boolean notSingle, @Nullable WoodType woodType) {
        return notSingle ?
                new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/double/sakura.png") :
                new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/sakura.png");
    }

    @Override  // read
    public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.func_230337_a_(p_230337_1_, p_230337_2_);
        this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(p_230337_2_)) {
            ItemStackHelper.loadAllItems(p_230337_2_, this.chestContents);
        }

    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }

        return compound;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.chestContents = nonNullList;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.sakurarosea.sakura_chest");
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.add(-1, 0, -1), pos.add(2, 2, 2));
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return ChestContainer.createGeneric9X3(id, playerInventory, this);
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.chestContents) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getLidAngle(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    @Override
    public void tick() {
        ++this.ticksSinceSync;
        if (this.world != null) {
            this.numPlayersUsing = getNumPlayerUsing(this.world, this, this.ticksSinceSync, this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.numPlayersUsing);
        }
        this.prevLidAngle = this.lidAngle;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            this.playSound(SoundEvents.BLOCK_CHEST_OPEN);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;
            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            } else {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.5F && f1 >= 0.5F) {
                this.playSound(SoundEvents.BLOCK_CHEST_CLOSE);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    private void playSound(SoundEvent soundIn) {
        ChestType chesttype = this.getBlockState().get(SakuraChestBlock.TYPE);
        if (chesttype != ChestType.LEFT) {
            double d0 = (double) this.pos.getX() + 0.5D;
            double d1 = (double) this.pos.getY() + 0.5D;
            double d2 = (double) this.pos.getZ() + 0.5D;
            if (chesttype == ChestType.RIGHT) {
                Direction direction = SakuraChestBlock.getDirectionToAttached(this.getBlockState());
                d0 += (double) direction.getXOffset() * 0.5D;
                d2 += (double) direction.getZOffset() * 0.5D;
            }

            if (this.world != null) {
                this.world.playSound(null, d0, d1, d2, soundIn, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }

    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }

    }

    public void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof ChestBlock) {
            if (this.world != null) {
                this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
                this.world.notifyNeighborsOfStateChange(this.pos, block);
            }
        }
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.chestHandler != null) {
            this.chestHandler.invalidate();
            this.chestHandler = null;
        }
    }

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
        if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (this.chestHandler == null) {
                this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
            }
            return this.chestHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
        BlockState state = this.getBlockState();
        if (!(state.getBlock() instanceof SakuraChestBlock)) {
            return new net.minecraftforge.items.wrapper.InvWrapper(this);
        }
        ChestType type = state.get(SakuraChestBlock.TYPE);
        if (type != ChestType.SINGLE) {
            BlockPos opos = this.getPos().offset(SakuraChestBlock.getDirectionToAttached(state));
            BlockState ostate = this.getWorld().getBlockState(opos);
            if (state.getBlock() == ostate.getBlock()) {
                ChestType otype = ostate.get(SakuraChestBlock.TYPE);
                if (otype != ChestType.SINGLE && type != otype && state.get(SakuraChestBlock.FACING) == ostate.get(SakuraChestBlock.FACING)) {
                    TileEntity ote = this.getWorld().getTileEntity(opos);
                    if (ote instanceof SakuraChestTileEntity) {
                        IInventory top = type == ChestType.RIGHT ? this : (IInventory) ote;
                        IInventory bottom = type == ChestType.RIGHT ? (IInventory) ote : this;
                        return new net.minecraftforge.items.wrapper.CombinedInvWrapper(
                                new net.minecraftforge.items.wrapper.InvWrapper(top),
                                new net.minecraftforge.items.wrapper.InvWrapper(bottom));
                    }
                }
            }
        }
        return new net.minecraftforge.items.wrapper.InvWrapper(this);
    }

    @Override
    public void remove() {
        super.remove();
        if (chestHandler != null)
            chestHandler.invalidate();
    }
}
