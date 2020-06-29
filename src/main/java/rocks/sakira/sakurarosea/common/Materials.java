package rocks.sakira.sakurarosea.common;

import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;
import rocks.sakira.sakurarosea.Constants;

public class Materials {
    public static final RenderMaterial SAKURA_CHEST_MATERIAL = getChestMaterial("sakura");
    public static final RenderMaterial SAKURA_CHEST_LEFT_MATERIAL = getChestMaterial("sakura_left");
    public static final RenderMaterial SAKURA_CHEST_RIGHT_MATERIAL = getChestMaterial("sakura_right");

    public static final RenderMaterial SAKURA_SIGN_MATERIAL = getSignMaterial("sakura");

    public static final RenderMaterial SAKURA_SHIELD_BASE = getShieldBaseMaterial("sakura");
    public static final RenderMaterial SAKURA_SHIELD_NO_PATTERN = getShieldNoPatternMaterial("sakura");

    private static RenderMaterial getChestMaterial(String chestType) {
        return new RenderMaterial(Atlases.CHEST_ATLAS, new ResourceLocation("entity/chest/" + chestType));
    }

    private static RenderMaterial getSignMaterial(String signType) {
        return new RenderMaterial(Atlases.SIGN_ATLAS, new ResourceLocation("entity/signs/" + signType));
    }

    private static RenderMaterial getShieldBaseMaterial(String shieldType) {
        return new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(Constants.MOD_ID, "entity/" + shieldType + "_shield_base"));
    }

    private static RenderMaterial getShieldNoPatternMaterial(String shieldType) {
        return new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(Constants.MOD_ID, "entity/" + shieldType + "_shield_base_nopattern"));
    }

    public static RenderMaterial getChestMaterial(ChestType chestType) {
        return getChestMaterial(chestType, SAKURA_CHEST_MATERIAL, SAKURA_CHEST_LEFT_MATERIAL, SAKURA_CHEST_RIGHT_MATERIAL);
    }

    private static RenderMaterial getChestMaterial(ChestType chestType, RenderMaterial single, RenderMaterial left, RenderMaterial right) {
        switch (chestType) {
            case LEFT:
                return left;
            case RIGHT:
                return right;
            case SINGLE:
            default:
                return single;
        }
    }
}
