package rocks.sakira.sakurarosea.mixin.client;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.SignType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rocks.sakira.sakurarosea.register.SignTypes;
import rocks.sakira.sakurarosea.register.Sprites;

@Mixin(SignBlockEntityRenderer.class)
public class SignBlockEntityRendererMixin {
    private static final Logger logger = LogManager.getLogger("SignBlockEntityRendererMixin");

    @Inject(at = @At("HEAD"), cancellable = true, method = "getModelTexture")
    private static void getModelTexture(Block block, CallbackInfoReturnable<SpriteIdentifier> callback) {
        if (block instanceof AbstractSignBlock) {
            SignType signType = ((AbstractSignBlock) block) .getSignType();

            if (signType.equals(SignTypes.INSTANCE.getSAKURA())) {
                callback.setReturnValue(Sprites.INSTANCE.getSAKURA_SIGN_MATERIAL());
            }
        }
    }
}
