package rocks.sakira.sakurarosea.networking.packets

import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.network.Packet
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry
import rocks.sakira.sakurarosea.MOD_ID
import rocks.sakira.sakurarosea.networking.listeners.CustomEntitySpawnListener
import java.util.*

class CustomEntitySpawnS2CPacket : Packet<ClientPlayNetworkHandler> {
    companion object {
        val PACKET_ID = Identifier(MOD_ID, "custom_entity_spawn")

        fun fromBuffer(buf: PacketByteBuf) = CustomEntitySpawnS2CPacket(
            id = buf.readVarInt(),
            uuid = buf.readUuid(),
            type = Registry.ENTITY_TYPE[buf.readVarInt()],
            x = buf.readDouble(),
            y = buf.readDouble(),
            z = buf.readDouble(),
            pitch = buf.readByte().toFloat(),
            yaw = buf.readByte().toFloat(),
            data = buf.readInt(),
            velocityX = buf.readShort().toInt(),
            velocityY = buf.readShort().toInt(),
            velocityZ = buf.readShort().toInt()
        )
    }

    var id: Int
    var uuid: UUID
    var x: Double
    var y: Double
    var z: Double
    var pitch: Float
    var yaw: Float
    var type: EntityType<*>
    var data: Int
    var velocityX: Int
    var velocityY: Int
    var velocityZ: Int

    constructor(
        id: Int, uuid: UUID,
        x: Double, y: Double, z: Double, pitch: Float, yaw: Float,
        type: EntityType<*>, data: Int, velocityX: Int, velocityY: Int, velocityZ: Int
    ) {
        this.id = id;
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = MathHelper.floor(pitch * 256.0F / 360.0F).toFloat()
        this.yaw = MathHelper.floor(yaw * 256.0F / 360.0F).toFloat()
        this.type = type
        this.data = data
        this.velocityX = velocityX
        this.velocityY = velocityY
        this.velocityZ = velocityZ
    }

    constructor(
        id: Int, uuid: UUID,
        x: Double, y: Double, z: Double, pitch: Float, yaw: Float,
        type: EntityType<*>, data: Int, velocity: Vec3d
    ) : this(
        id, uuid,
        x, y, z, pitch, yaw,
        type, data,
        (MathHelper.clamp(velocity.x, -3.9, 3.9) * 8000.0).toInt(),
        (MathHelper.clamp(velocity.y, -3.9, 3.9) * 8000.0).toInt(),
        (MathHelper.clamp(velocity.z, -3.9, 3.9) * 8000.0).toInt()
    )

    constructor(entity: Entity) : this(entity, 0)

    constructor(entity: Entity, data: Int) : this(
        entity.entityId, entity.uuid,
        entity.x, entity.y, entity.z, entity.pitch, entity.yaw,
        entity.type, data, entity.velocity
    )

    constructor(entity: Entity, type: EntityType<*>, data: Int, pos: BlockPos) : this(
        entity.entityId, entity.uuid,
        pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), entity.pitch, entity.yaw,
        type, data, entity.velocity
    )

    override fun read(buf: PacketByteBuf) {
        id = buf.readVarInt()
        uuid = buf.readUuid()
        type = Registry.ENTITY_TYPE[buf.readVarInt()]
        x = buf.readDouble()
        y = buf.readDouble()
        z = buf.readDouble()
        pitch = buf.readByte().toFloat()
        yaw = buf.readByte().toFloat()
        data = buf.readInt()
        velocityX = buf.readShort().toInt()
        velocityY = buf.readShort().toInt()
        velocityZ = buf.readShort().toInt()
    }

    override fun write(buf: PacketByteBuf) {
        buf.writeVarInt(id)
        buf.writeUuid(uuid)
        buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(type))
        buf.writeDouble(x)
        buf.writeDouble(y)
        buf.writeDouble(z)
        buf.writeByte(pitch.toInt())
        buf.writeByte(yaw.toInt())
        buf.writeInt(data)
        buf.writeShort(velocityX)
        buf.writeShort(velocityY)
        buf.writeShort(velocityZ)
    }

    override fun apply(listener: ClientPlayNetworkHandler?) { }  // Nothing to do
}
