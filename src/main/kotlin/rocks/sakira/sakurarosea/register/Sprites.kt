package rocks.sakira.sakurarosea.register

import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback
import net.minecraft.block.enums.ChestType
import net.minecraft.client.render.TexturedRenderLayers
import net.minecraft.client.render.model.SpriteAtlasManager
import net.minecraft.client.util.SpriteIdentifier
import net.minecraft.util.Identifier
import rocks.sakira.sakurarosea.MOD_ID

object Sprites {
    val CHEST_ATLAS: Identifier = TexturedRenderLayers.CHEST_ATLAS_TEXTURE
    val SIGN_ATLAS: Identifier = TexturedRenderLayers.SIGNS_ATLAS_TEXTURE

    val SAKURA_CHEST_MATERIAL = SpriteIdentifier(CHEST_ATLAS, Identifier(MOD_ID, "entity/chest/sakura"))
    val SAKURA_CHEST_MATERIAL_LEFT = SpriteIdentifier(CHEST_ATLAS, Identifier(MOD_ID, "entity/chest/sakura_left"))
    val SAKURA_CHEST_MATERIAL_RIGHT = SpriteIdentifier(CHEST_ATLAS, Identifier(MOD_ID, "entity/chest/sakura_right"))

    val SAKURA_SIGN_MATERIAL = SpriteIdentifier(SIGN_ATLAS, Identifier(MOD_ID, "entity/signs/sakura"))

    fun getChestSpriteIdentifier(type: ChestType): SpriteIdentifier = when(type) {
        ChestType.LEFT -> SAKURA_CHEST_MATERIAL_LEFT
        ChestType.RIGHT -> SAKURA_CHEST_MATERIAL_RIGHT

        else -> SAKURA_CHEST_MATERIAL
    }

    fun registerChestMaterials(registry: ClientSpriteRegistryCallback.Registry) {
        registry.register(SAKURA_CHEST_MATERIAL.textureId)
        registry.register(SAKURA_CHEST_MATERIAL_LEFT.textureId)
        registry.register(SAKURA_CHEST_MATERIAL_RIGHT.textureId)
    }

    fun registerSignMaterials(registry: ClientSpriteRegistryCallback.Registry) {
        registry.register(SAKURA_SIGN_MATERIAL.textureId)

//        TexturedRenderLayers.WOOD_TYPE_TEXTURES[SignTypes.SAKURA] = SAKURA_SIGN_MATERIAL
    }
}
