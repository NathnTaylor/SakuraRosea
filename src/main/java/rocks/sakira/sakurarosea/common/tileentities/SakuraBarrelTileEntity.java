package rocks.sakira.sakurarosea.common.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import rocks.sakira.sakurarosea.common.block.SakuraBarrelBlock;

public class SakuraBarrelTileEntity extends LockableLootTileEntity {
    private NonNullList<ItemStack> barrelContents;
    private int openCount = 0;

    public SakuraBarrelTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
        this.barrelContents = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.barrelContents);
        }

        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        this.barrelContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.barrelContents);
        }
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.barrelContents) {
            if (!itemStack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.barrelContents.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        return ItemStackHelper.getAndSplit(this.barrelContents, slot, amount);
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        return ItemStackHelper.getAndRemove(this.barrelContents, slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.barrelContents.set(slot, itemStack);
        if (itemStack.getCount() > this.getInventoryStackLimit()) {
            itemStack.setCount(this.getInventoryStackLimit());
        }
    }

    @Override
    public void clear() {
        this.barrelContents.clear();
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.barrelContents;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.barrelContents = items;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.sakurarosea.sakura_barrel");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return ChestContainer.createGeneric9X3(id, playerInventory, this);
    }

    @Override
    public void openInventory(PlayerEntity playerEntity) {
        if (!playerEntity.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            BlockState blockState = this.getBlockState();
            boolean open = blockState.get(SakuraBarrelBlock.PROPERTY_OPEN);
            if (!open) {
                this.playSoundEvent(blockState, SoundEvents.BLOCK_BARREL_OPEN);
                this.setOpenProperty(blockState, true);
            }

            this.scheduleTick();
        }

    }

    private void scheduleTick() {
        if (this.world != null) {
            this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
        }
    }

    public void tick() {
        if (this.world != null) {
            this.openCount = ChestTileEntity.calculatePlayersUsing(this.world, this, this.pos.getX(), this.pos.getY(), this.pos.getZ());
        }
        if (this.openCount > 0) {
            this.scheduleTick();
        } else {
            BlockState blockState = this.getBlockState();
            if (!(blockState.getBlock() instanceof SakuraBarrelBlock)) {
                this.remove();
                return;
            }

            boolean open = blockState.get(SakuraBarrelBlock.PROPERTY_OPEN);
            if (open) {
                this.playSoundEvent(blockState, SoundEvents.BLOCK_BARREL_CLOSE);
                this.setOpenProperty(blockState, false);
            }
        }

    }

    @Override
    public void closeInventory(PlayerEntity playerEntity) {
        if (!playerEntity.isSpectator()) {
            --this.openCount;
        }

    }

    private void setOpenProperty(BlockState blockState, boolean open) {
        if (this.world != null) {
            this.world.setBlockState(this.getPos(), blockState.with(SakuraBarrelBlock.PROPERTY_OPEN, open), 3);
        }
    }

    private void playSoundEvent(BlockState blockState, SoundEvent soundEvent) {
        if (this.world != null) {
            Vec3i vec3i = blockState.get(SakuraBarrelBlock.PROPERTY_FACING).getDirectionVec();
            this.world.playSound(
                    null,
                    (double) this.pos.getX() + 0.5D + (double) vec3i.getX() / 2.0D,
                    (double) this.pos.getY() + 0.5D + (double) vec3i.getY() / 2.0D,
                    (double) this.pos.getZ() + 0.5D + (double) vec3i.getZ() / 2.0D,
                    soundEvent,
                    SoundCategory.BLOCKS,
                    0.5F,
                    this.world.rand.nextFloat() * 0.1F + 0.9F
            );
        }
    }
}
