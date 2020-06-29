package rocks.sakira.sakurarosea.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import rocks.sakira.sakurarosea.common.item.Items;

public class SakuraBoatEntity extends BoatEntity {
    public SakuraBoatEntity(EntityType<? extends SakuraBoatEntity> p_i50129_1_, World p_i50129_2_) {
        super(p_i50129_1_, p_i50129_2_);
        this.preventEntitySpawning = true;
    }

    public SakuraBoatEntity(World worldIn, double x, double y, double z) {
        this(Entities.SAKURA_BOAT_ENTITY.get(), worldIn);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public Item getItemBoat() {
        return Items.SAKURA_BOAT_ITEM.get();
    }
}
