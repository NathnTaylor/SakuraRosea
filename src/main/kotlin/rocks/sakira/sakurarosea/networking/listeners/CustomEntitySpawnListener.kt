package rocks.sakira.sakurarosea.networking.listeners

import net.minecraft.client.MinecraftClient
import net.minecraft.network.ClientConnection
import net.minecraft.network.NetworkThreadUtils
import net.minecraft.network.listener.PacketListener
import net.minecraft.text.Text
import net.minecraft.util.thread.ThreadExecutor
import rocks.sakira.sakurarosea.networking.packets.CustomEntitySpawnS2CPacket
import rocks.sakira.sakurarosea.register.Entities

class CustomEntitySpawnListener : PacketListener {
    override fun onDisconnected(reason: Text) {}

    override fun getConnection(): ClientConnection = MinecraftClient.getInstance().networkHandler!!.connection

    fun onEntitySpawn(packet: CustomEntitySpawnS2CPacket) {
//        NetworkThreadUtils.forceMainThread(packet, this, MinecraftClient.getInstance() as ThreadExecutor<*>)
        val world = MinecraftClient.getInstance().networkHandler?.world ?: return  // No client world

        val entity = when (packet.type) {
            Entities.SAKURA_BOAT -> Entities.sakuraBoatFactory(world, packet.x, packet.y, packet.z, packet.yaw)

            else -> null
        } ?: return  // Not an entity we handle

        entity.updateTrackedPosition(packet.x, packet.y, packet.z)
        entity.refreshPositionAfterTeleport(packet.x, packet.y, packet.z)
        entity.pitch = (packet.pitch * 360) / 256F
        entity.yaw = (packet.yaw * 360) / 256F
        entity.entityId = packet.id
        entity.uuid = packet.uuid

        world.addEntity(packet.id, entity)
    }
}
