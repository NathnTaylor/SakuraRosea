package io.github.faecraft.sakurarosea.entity

import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry
import net.minecraft.entity.EntityType
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.item.Item
import net.minecraft.network.Packet
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import io.github.faecraft.sakurarosea.networking.packets.CustomEntitySpawnS2CPacket
import io.github.faecraft.sakurarosea.register.Entities
import io.github.faecraft.sakurarosea.register.Items

class SakuraBoatEntity : BoatEntity {
    constructor(type: EntityType<out BoatEntity>, world: World) : super(type, world)

    constructor(world: World, x: Double, y: Double, z: Double) : this(Entities.SAKURA_BOAT, world) {
        updatePosition(x, y, z)
        velocity = Vec3d.ZERO

        prevX = x
        prevY = y
        prevZ = z
    }

    constructor(world: World, x: Double, y: Double, z: Double, yaw: Float) : this(world, x, y, z) {
        this.yaw = yaw
        this.prevYaw = yaw
    }

    override fun asItem(): Item = Items.SAKURA_BOAT

    override fun createSpawnPacket(): Packet<*> {
        val packet = CustomEntitySpawnS2CPacket(this)
        val buffer = PacketByteBuf(Unpooled.buffer())

        packet.write(buffer)

        return ServerSidePacketRegistry.INSTANCE.toPacket(
            CustomEntitySpawnS2CPacket.PACKET_ID, buffer
        )
    }
}
