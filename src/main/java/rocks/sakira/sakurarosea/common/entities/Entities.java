package rocks.sakira.sakurarosea.common.entities;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

public class Entities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);

    public static final RegistryObject<EntityType<SakuraBoatEntity>> SAKURA_BOAT_ENTITY = REGISTER.register(
            "sakura_boat",

            () -> EntityType.Builder
                    .<SakuraBoatEntity>create(SakuraBoatEntity::new, EntityClassification.MISC)
                    .size(1.375F, 0.5625F)
                    .setCustomClientFactory(
                            (spawnEntity, world) -> new SakuraBoatEntity(Entities.SAKURA_BOAT_ENTITY.get(), world)
                    )
                    .build("sakura_boat")
    );
}
