package rocks.sakira.sakurarosea

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer
import net.minecraft.client.texture.SpriteAtlasTexture
import rocks.sakira.sakurarosea.blockentity.SakuraSignBlockEntity
import rocks.sakira.sakurarosea.blockentity.render.SakuraSignBlockEntityRenderer
import rocks.sakira.sakurarosea.entity.renderer.SakuraBoatEntityRenderer
import rocks.sakira.sakurarosea.item.render.SakuraChestItemRenderer
import rocks.sakira.sakurarosea.mixin.common.AxeItemAccessor
import rocks.sakira.sakurarosea.mixin.common.ComposterBlockMixin
import rocks.sakira.sakurarosea.networking.packets.CustomEntitySpawnS2CPacket
import rocks.sakira.sakurarosea.register.*
import rocks.sakira.sakurarosea.tree.SakuraSaplingGenerator

const val MOD_ID = "sakurarosea"

fun init() {
    Biomes.register()
    Blocks.register()
    BlockEntities.register()
    Items.register()
    Entities.register()

    registerCompostableItems()
    registerSignTypes()
    registerStrippedLogs()

    SakuraSaplingGenerator.register()
}

fun clientInit() {
    Particles.register()

    registerBlockEntityRenderers()
    registerEntityRenderers()
    registerNetworkHandlers()
    registerRenderLayers()
    registerSprites()
}

fun registerCompostableItems() {
    ComposterBlockMixin.registerCompostableItem(0.3F, Items.CHERRY)

    ComposterBlockMixin.registerCompostableItem(0.3F, Items.SAKURA_BLOSSOM)
    ComposterBlockMixin.registerCompostableItem(0.3F, Items.SAKURA_SAPLING)
    ComposterBlockMixin.registerCompostableItem(0.3F, Items.SAKURA_LEAVES)
    ComposterBlockMixin.registerCompostableItem(0.3F, Items.SAKURA_LEAVES_ALT)
    ComposterBlockMixin.registerCompostableItem(0.3F, Items.SAKURA_LEAVES_WHITE)
}

fun registerStrippedLogs() {
    val blocks = AxeItemAccessor.getStrippedBlocks().toMutableMap()

    blocks[Blocks.SAKURA_LOG] = Blocks.SAKURA_LOG_STRIPPED
    blocks[Blocks.SAKURA_WOOD] = Blocks.SAKURA_WOOD_STRIPPED

    AxeItemAccessor.setStrippedBlocks(blocks.toMap())

//    for (item: Item in Registry.ITEM.stream()) {
//        if (item is AxeItem) {
//            val mixin = item as AxeItemAccessor
//            val blocks = mixin.strippedBlocks.toMutableMap()
//
//            blocks[Blocks.SAKURA_LOG] = Blocks.SAKURA_LOG_STRIPPED
//            blocks[Blocks.SAKURA_WOOD] = Blocks.SAKURA_WOOD_STRIPPED
//
//            mixin.strippedBlocks = blocks
//        }
//    }
}

fun registerRenderLayers() {
    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SAKURA_DOOR, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SAKURA_TRAPDOOR, RenderLayer.getCutout())

    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.PINK_CLAY_DOOR, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.WHITE_CLAY_DOOR, RenderLayer.getCutout())

    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SAKURA_SAPLING, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.POTTED_SAKURA_SAPLING, RenderLayer.getCutout())

    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SAKURA_LEAF_CARPET, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SAKURA_LEAF_CARPET_ALT, RenderLayer.getCutout())
    BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SAKURA_LEAF_CARPET_WHITE, RenderLayer.getCutout())
}

fun registerBlockEntityRenderers() {
    BlockEntityRendererRegistry.INSTANCE.register(BlockEntities.SAKURA_CHEST) {
        ChestBlockEntityRenderer(it)
    }

    BuiltinItemRendererRegistry.INSTANCE.register(Items.SAKURA_CHEST, SakuraChestItemRenderer())

    BlockEntityRendererRegistry.INSTANCE.register<SakuraSignBlockEntity>(BlockEntities.SAKURA_SIGN) {
        SakuraSignBlockEntityRenderer(it)
    }
}

fun registerEntityRenderers() {
    EntityRendererRegistry.INSTANCE.register(Entities.SAKURA_BOAT) { dispatcher, _ ->
        SakuraBoatEntityRenderer(dispatcher)
    }
}

fun registerNetworkHandlers() {
    ClientSidePacketRegistry.INSTANCE.register(CustomEntitySpawnS2CPacket.PACKET_ID) { context, buf ->
        val packet = CustomEntitySpawnS2CPacket.fromBuffer(buf)

        context.taskQueue.execute {
            val world = MinecraftClient.getInstance().networkHandler?.world ?: return@execute  // No client world

            val entity = when (packet.type) {
                Entities.SAKURA_BOAT -> Entities.sakuraBoatFactory(world, packet.x, packet.y, packet.z, packet.yaw)

                else -> null
            } ?: return@execute  // Not an entity we handle

            entity.updateTrackedPosition(packet.x, packet.y, packet.z)
            entity.refreshPositionAfterTeleport(packet.x, packet.y, packet.z)
            entity.pitch = (packet.pitch * 360) / 256F
            entity.yaw = (packet.yaw * 360) / 256F
            entity.entityId = packet.id
            entity.uuid = packet.uuid

            world.addEntity(packet.id, entity)
        }
    }
}

fun registerSignTypes() {
    SignTypes.register()
}

fun registerSprites() {
    ClientSpriteRegistryCallback.event(Sprites.CHEST_ATLAS).register(ClientSpriteRegistryCallback
    { _: SpriteAtlasTexture, registry: ClientSpriteRegistryCallback.Registry ->
        Sprites.registerChestMaterials(registry)
    })

    ClientSpriteRegistryCallback.event(Sprites.SIGN_ATLAS).register(ClientSpriteRegistryCallback
    { _: SpriteAtlasTexture, registry: ClientSpriteRegistryCallback.Registry ->
        Sprites.registerSignMaterials(registry)
    })
}
