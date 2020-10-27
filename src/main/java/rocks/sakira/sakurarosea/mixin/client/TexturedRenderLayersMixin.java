package rocks.sakira.sakurarosea.mixin.client;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rocks.sakira.sakurarosea.blockentity.SakuraChestBlockEntity;
import rocks.sakira.sakurarosea.register.SignTypes;
import rocks.sakira.sakurarosea.register.Sprites;

@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
    private static boolean signMethodCalled = false;

    @Inject(at = @At("HEAD"), cancellable = true, method = "getChestTexture(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/block/enums/ChestType;Z)Lnet/minecraft/client/util/SpriteIdentifier;")
    private static void getChestTexture(BlockEntity blockEntity, ChestType type, boolean christmas, CallbackInfoReturnable<SpriteIdentifier> callback) {
        if (blockEntity instanceof SakuraChestBlockEntity) {
            callback.setReturnValue(Sprites.INSTANCE.getChestSpriteIdentifier(type));
        }
    }

//    @Inject(at = @At("HEAD"), cancellable = true, method = "getSignTextureId")
//    private static void getSignTextureId(SignType type, CallbackInfoReturnable<SpriteIdentifier> callback) {
//        if (signMethodCalled) {
//            if (type.equals(SignTypes.INSTANCE.getSAKURA())) {
//                callback.setReturnValue(Sprites.INSTANCE.getSAKURA_SIGN_MATERIAL());
//            }
//        } else {
//            signMethodCalled = true;
//        }
//    }
}
