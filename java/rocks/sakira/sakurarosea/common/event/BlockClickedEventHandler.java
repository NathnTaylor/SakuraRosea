package rocks.sakira.sakurarosea.common.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rocks.sakira.sakurarosea.common.block.Blocks;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class BlockClickedEventHandler {
    @SubscribeEvent
    public static void onBlockClicked(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().getItem() instanceof AxeItem) {  // They're using an axe
            World world = event.getWorld();
            BlockPos pos = event.getPos();
            BlockState state = world.getBlockState(pos);
            Block clickedBlock = state.getBlock();

            Block block;

            if (clickedBlock == Blocks.SAKURA_LOG_BLOCK.get()) {
                block = Blocks.STRIPPED_SAKURA_LOG_BLOCK.get();
            } else {
                return;  // This isn't a block we deal with
            }

            PlayerEntity player = event.getPlayer();

            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1F, 1F);

            if (!world.isRemote) {  // We're on the server
                world.setBlockState(pos, block.getDefaultState()  // Set the block
                                .with(  // Make sure it's rotated properly
                                        RotatedPillarBlock.AXIS,
                                        state.get(RotatedPillarBlock.AXIS)
                                ),
                        11);  // Not quite sure what these flags are

                if (player != null) {  // Apparently this is possible
                    event.getItemStack().damageItem(1, player, (playerEntity) -> {
                        // Damage the player's axe, and then play the break animation if it broke
                        playerEntity.sendBreakAnimation(event.getHand());
                    });
                }
            }
        }
    }
}
