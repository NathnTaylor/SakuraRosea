package rocks.sakira.sakurarosea.common.tileentities;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class SakuraSignTileEntity extends SignTileEntity {
    public final ITextComponent[] signText = new ITextComponent[]{new StringTextComponent(""), new StringTextComponent(""), new StringTextComponent(""), new StringTextComponent("")};
    private final String[] renderText = new String[4];
    private boolean isEditable = true;
    private PlayerEntity player;
    private DyeColor textColor = DyeColor.BLACK;

    public SakuraSignTileEntity() {
        super();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        for (int i = 0; i < 4; ++i) {
            String s = ITextComponent.Serializer.toJson(this.signText[i]);
            compound.putString("Text" + (i + 1), s);
        }

        compound.putString("Color", this.textColor.getTranslationKey());
        return compound;
    }

    @Override  // read
    public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        this.isEditable = false;
        super.func_230337_a_(p_230337_1_, p_230337_2_);
        this.textColor = DyeColor.byTranslationKey(p_230337_2_.getString("Color"), DyeColor.BLACK);

        for(int i = 0; i < 4; ++i) {
            String s = p_230337_2_.getString("Text" + (i + 1));
            ITextComponent itextcomponent = ITextComponent.Serializer.func_240643_a_(s.isEmpty() ? "\"\"" : s);
            if (this.world instanceof ServerWorld) {
                try {
                    this.signText[i] = TextComponentUtils.func_240645_a_(this.getCommandSource((ServerPlayerEntity)null), itextcomponent, (Entity)null, 0);
                } catch (CommandSyntaxException commandsyntaxexception) {
                    this.signText[i] = itextcomponent;
                }
            } else {
                this.signText[i] = itextcomponent;
            }

            this.renderText[i] = null;
        }

    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    @Override  // getText
    public ITextProperties func_235677_a_(int line, UnaryOperator<ITextProperties> p_235677_2_) {
        return this.signText[line];
    }

    @Override
    public void setText(int line, ITextComponent p_212365_2_) {
        this.signText[line] = p_212365_2_;
        this.renderText[line] = null;
    }

    /**
     * Retrieves packet to send to the client whenever this Tile Entity is resynced via World.notifyBlockUpdate. For
     * modded TE's, this packet comes back to you clientside in {@link #onDataPacket}
     */
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 9, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public boolean onlyOpsCanSetNbt() {
        return true;
    }

    @Override
    public boolean getIsEditable() {
        return this.isEditable;
    }

    /**
     * Sets the sign's isEditable flag to the specified parameter.
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public void setEditable(boolean isEditableIn) {
        this.isEditable = isEditableIn;
        if (!isEditableIn) {
            this.player = null;
        }

    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(PlayerEntity playerIn) {
        this.player = playerIn;
    }

    @Override
    public boolean executeCommand(PlayerEntity playerIn) {
        for (ITextComponent itextcomponent : this.signText) {
            Style style = itextcomponent == null ? null : itextcomponent.getStyle();
            if (style != null && style.getClickEvent() != null) {
                ClickEvent clickevent = style.getClickEvent();
                if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
                    playerIn.getServer().getCommandManager().handleCommand(this.getCommandSource((ServerPlayerEntity) playerIn), clickevent.getValue());
                }
            }
        }

        return true;
    }

    @Override
    public CommandSource getCommandSource(@Nullable ServerPlayerEntity playerIn) {
        String s = playerIn == null ? "Sign" : playerIn.getName().getString();
        ITextComponent itextcomponent = playerIn == null ? new StringTextComponent("Sign") : playerIn.getDisplayName();
        return new CommandSource(ICommandSource.DUMMY, new Vector3d((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D), Vector2f.ZERO, (ServerWorld) this.world, 2, s, itextcomponent, this.world.getServer(), playerIn);
    }

    @Override
    public DyeColor getTextColor() {
        return this.textColor;
    }

    @Override
    public boolean setTextColor(DyeColor newColor) {
        if (newColor != this.getTextColor()) {
            this.textColor = newColor;
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
            return true;
        } else {
            return false;
        }
    }
}
