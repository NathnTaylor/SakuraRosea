package rocks.sakira.sakurarosea.common.tileentities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rocks.sakira.sakurarosea.common.block.Blocks;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

public class TileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    public static final RegistryObject<TileEntityType<SakuraBarrelTileEntity>> SAKURA_BARREL_ENTITY = REGISTER.register(
            "sakura_barrel",

            () -> TileEntityType.Builder.create(
                    () -> new SakuraBarrelTileEntity(TileEntities.SAKURA_BARREL_ENTITY.get()),
                    Blocks.SAKURA_BARREL_BLOCK.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<SakuraChestTileEntity>> SAKURA_CHEST_ENTITY = REGISTER.register(
            "sakura_chest",

            () -> TileEntityType.Builder.create(
                    () -> new SakuraChestTileEntity(TileEntities.SAKURA_CHEST_ENTITY.get()),
                    Blocks.SAKURA_CHEST_BLOCK.get()
            ).build(null)
    );

    public static final RegistryObject<TileEntityType<SakuraSignTileEntity>> SAKURA_SIGN_ENTITY = REGISTER.register(
            "sakura_sign",

            () -> TileEntityType.Builder.create(
                    SakuraSignTileEntity::new,
                    Blocks.SAKURA_SIGN_BLOCK.get(), Blocks.SAKURA_WALL_SIGN_BLOCK.get()
            ).build(null)
    );
}
