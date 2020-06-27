package rocks.sakira.sakurarosea.common.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rocks.sakira.sakurarosea.common.tileentities.TileEntities;
import rocks.sakira.sakurarosea.common.wood.FaeWoodType;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

public class Blocks {
    public static final DeferredRegister<Block> REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> SAKURA_DOOR_BLOCK = REGISTER.register(
            "sakura_door",

            () -> new SakuraDoor(
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
            )
    );

    public static final RegistryObject<Block> SAKURA_TRAPDOOR_BLOCK = REGISTER.register(
            "sakura_trapdoor",

            () -> new SakuraTrapDoor(
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
            )
    );

    public static final RegistryObject<Block> SAKURA_LEAVES_BLOCK = REGISTER.register(
            "sakura_leaves",

            () -> new LeavesBlock(
                    Block.Properties.create(Material.LEAVES, MaterialColor.MAGENTA)
                            .hardnessAndResistance(0.2F)
                            .notSolid()
                            .sound(SoundType.PLANT)
            )
    );

    public static final RegistryObject<Block> ALT_SAKURA_LEAVES_BLOCK = REGISTER.register(
            "alt_sakura_leaves",

            () -> new LeavesBlock(
                    Block.Properties.create(Material.LEAVES, MaterialColor.MAGENTA)
                            .hardnessAndResistance(0.2F)
                            .notSolid()
                            .sound(SoundType.PLANT)
            )
    );

    public static final RegistryObject<Block> WHITE_SAKURA_LEAVES_BLOCK = REGISTER.register(
            "white_sakura_leaves",

            () -> new LeavesBlock(
                    Block.Properties.create(Material.LEAVES, MaterialColor.MAGENTA)
                            .hardnessAndResistance(0.2F)
                            .notSolid()
                            .sound(SoundType.PLANT)
            )
    );

    public static final RegistryObject<Block> SAKURA_BUTTON_BLOCK = REGISTER.register(
            "sakura_button",

            () -> new SakuraButtonBlock(
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> SAKURA_LOG_BLOCK = REGISTER.register(
            "sakura_log",

            () -> new LogBlock(
                    MaterialColor.MAGENTA, //o.0 magenta
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> SAKURA_PRESSURE_PLATE_BLOCK = REGISTER.register(
            "sakura_pressure_plate",

            () -> new SakuraPressurePlate(
                    PressurePlateBlock.Sensitivity.EVERYTHING,
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .doesNotBlockMovement()
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> SAKURA_SAPLING_BLOCK = REGISTER.register(
            "sakura_sapling",

            () -> new SakuraSaplingBlock(
                    Block.Properties.create(Material.PLANTS, MaterialColor.MAGENTA)
                            .doesNotBlockMovement()
                            .hardnessAndResistance(0F)
                            .sound(SoundType.PLANT)
                            .tickRandomly()
            )
    );

    public static final RegistryObject<Block> STRIPPED_SAKURA_LOG_BLOCK = REGISTER.register(
            "stripped_sakura_log",

            () -> new LogBlock(
                    MaterialColor.MAGENTA, //o.0 magenta
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> SAKURA_WOOD_BLOCK = REGISTER.register(
            "sakura_wood",

            () -> new LogBlock(
                    MaterialColor.MAGENTA, //o.0 magenta
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> STRIPPED_SAKURA_WOOD_BLOCK = REGISTER.register(
            "stripped_sakura_wood",

            () -> new LogBlock(
                    MaterialColor.MAGENTA, //o.0 magenta
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> SAKURA_SLAB_BLOCK = REGISTER.register(
            "sakura_slab",

            () -> new SlabBlock(
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> SAKURA_PLANKS_BLOCK = REGISTER.register(
            "sakura_planks",

            () -> new Block(
                    Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> SAKURA_STAIRS_BLOCK = REGISTER.register(
            "sakura_stairs",

            () -> new StairsBlock(
                    () -> SAKURA_PLANKS_BLOCK.get().getDefaultState(),
                    Block.Properties.from(SAKURA_PLANKS_BLOCK.get())
            )
    );

    public static final RegistryObject<Block> SAKURA_FENCE_BLOCK = REGISTER.register(
            "sakura_fence",

            () -> new FenceBlock(
                    Block.Properties.from(SAKURA_PLANKS_BLOCK.get())
            )
    );

    public static final RegistryObject<Block> SAKURA_FENCE_GATE_BLOCK = REGISTER.register(
            "sakura_fence_gate",

            () -> new FenceGateBlock(
                    Block.Properties.from(SAKURA_PLANKS_BLOCK.get())
            )
    );

    public static final RegistryObject<Block> SAKURA_BARREL_BLOCK = REGISTER.register(
            "sakura_barrel",

            SakuraBarrelBlock::new
    );

    public static final RegistryObject<Block> SAKURA_CHEST_BLOCK = REGISTER.register(
            "sakura_chest",

            () -> new SakuraChestBlock(TileEntities.SAKURA_CHEST_ENTITY)
    );

    public static final RegistryObject<Block> SAKURA_SIGN_BLOCK = REGISTER.register(
            "sakura_sign",

            () -> new SakuraSignBlock(
                    Block.Properties.from(SAKURA_PLANKS_BLOCK.get())
                            .doesNotBlockMovement(),
                    FaeWoodType.SAKURA
            )
    );

    public static final RegistryObject<Block> SAKURA_WALL_SIGN_BLOCK = REGISTER.register(
            "sakura_wall_sign",

            () -> new SakuraWallSignBlock(
                    Block.Properties.from(SAKURA_PLANKS_BLOCK.get())
                            .doesNotBlockMovement()
                            .lootFrom(SAKURA_SIGN_BLOCK.get()),
                    FaeWoodType.SAKURA
            )
    );

    public static final RegistryObject<Block> WHITE_BRICKS_BLOCK = REGISTER.register(
            "white_bricks",

            () -> new Block(
                    Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA)
                            .hardnessAndResistance(2.0F, 6.0F)
                            .sound(SoundType.STONE)
            )
    );

    public static final RegistryObject<Block> WHITE_BRICKS_STAIRS_BLOCK = REGISTER.register(
            "white_bricks_stairs",

            () -> new StairsBlock(
                    () -> WHITE_BRICKS_BLOCK.get().getDefaultState(),
                    Block.Properties.from(WHITE_BRICKS_BLOCK.get())
            )
    );

    public static final RegistryObject<Block> WHITE_BRICKS_WALL_BLOCK = REGISTER.register(
            "white_bricks_wall",

            () -> new WallBlock(
                    Block.Properties.from(WHITE_BRICKS_BLOCK.get())
            )
    );

    public static final RegistryObject<Block> WHITE_BRICKS_SLAB_BLOCK = REGISTER.register(
            "white_bricks_slab",

            () -> new SlabBlock(
                    Block.Properties.from(WHITE_BRICKS_BLOCK.get())
            )
    );
}
