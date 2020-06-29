package rocks.sakira.sakurarosea.common;

import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;
import rocks.sakira.sakurarosea.Constants;

public class Materials {
    public static final Material SAKURA_CHEST_MATERIAL = getChestMaterial("sakura");
    public static final Material SAKURA_CHEST_LEFT_MATERIAL = getChestMaterial("sakura_left");
    public static final Material SAKURA_CHEST_RIGHT_MATERIAL = getChestMaterial("sakura_right");

    public static final Material SAKURA_SIGN_MATERIAL = getSignMaterial("sakura");

    public static final Material SAKURA_SHIELD_BASE = getShieldBaseMaterial("sakura");
    public static final Material SAKURA_SHIELD_NO_PATTERN = getShieldNoPatternMaterial("sakura");

    private static Material getChestMaterial(String chestType) {
        return new Material(Atlases.CHEST_ATLAS, new ResourceLocation("entity/chest/" + chestType));
    }

    private static Material getSignMaterial(String signType) {
        return new Material(Atlases.SIGN_ATLAS, new ResourceLocation("entity/signs/" + signType));
    }

    private static Material getShieldBaseMaterial(String shieldType) {
        return new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(Constants.MOD_ID, "entity/" + shieldType + "_shield_base"));
    }

    private static Material getShieldNoPatternMaterial(String shieldType) {
        return new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(Constants.MOD_ID, "entity/" + shieldType + "_shield_base_nopattern"));
    }

    public static Material getChestMaterial(ChestType chestType) {
        return getChestMaterial(chestType, SAKURA_CHEST_MATERIAL, SAKURA_CHEST_LEFT_MATERIAL, SAKURA_CHEST_RIGHT_MATERIAL);
    }

    private static Material getChestMaterial(ChestType chestType, Material single, Material left, Material right) {
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
