package rocks.sakira.sakurarosea.common.item;

import net.minecraft.item.*;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rocks.sakira.sakurarosea.common.block.Blocks;
import rocks.sakira.sakurarosea.common.tileentities.renderers.SakuraChestItemStackRenderer;
import rocks.sakira.sakurarosea.common.tileentities.renderers.SakuraShieldRenderer;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

public class Items {
    public static final DeferredRegister<Item> REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);
    private static final ItemGroup GROUP = new SakuraRoseaItemGroup("sakurarosea") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SAKURA_SAPLING_BLOCK_ITEM.get());
        }
    };
    public static final RegistryObject<Item> CHERRY_ITEM = REGISTER.register(
            "cherry",

            () -> new Item(
                    new Item.Properties().group(GROUP)
                            .food(Foods.APPLE)
            )
    );

    public static final RegistryObject<Item> SAKURA_BUTTON_ITEM = REGISTER.register(
            "sakura_button",

            () -> new BlockItem(
                    Blocks.SAKURA_BUTTON_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> PINK_CLAY_DOOR_ITEM = REGISTER.register(
            "pink_clay_door",

            () -> new BlockItem(
                    Blocks.PINK_CLAY_DOOR_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHItE_CLAY_DOOR_ITEM = REGISTER.register(
            "white_clay_door",

            () -> new BlockItem(
                    Blocks.WHITE_CLAY_DOOR_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_DOOR_ITEM = REGISTER.register(
            "sakura_door",

            () -> new BlockItem(
                    Blocks.SAKURA_DOOR_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_TRAPDOOR_ITEM = REGISTER.register(
            "sakura_trapdoor",

            () -> new BlockItem(
                    Blocks.SAKURA_TRAPDOOR_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_LEAVES_BLOCK_ITEM = REGISTER.register(
            "sakura_leaves",

            () -> new BlockItem(
                    Blocks.SAKURA_LEAVES_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> ALT_SAKURA_LEAVES_BLOCK_ITEM = REGISTER.register(
            "alt_sakura_leaves",

            () -> new BlockItem(
                    Blocks.ALT_SAKURA_LEAVES_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHITE_SAKURA_LEAVES_BLOCK_ITEM = REGISTER.register(
            "white_sakura_leaves",

            () -> new BlockItem(
                    Blocks.WHITE_SAKURA_LEAVES_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_LOG_BLOCK_ITEM = REGISTER.register(
            "sakura_log",

            () -> new BlockItem(
                    Blocks.SAKURA_LOG_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_PRESSURE_PLATE_BLOCK_ITEM = REGISTER.register(
            "sakura_pressure_plate",

            () -> new BlockItem(
                    Blocks.SAKURA_PRESSURE_PLATE_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_SAPLING_BLOCK_ITEM = REGISTER.register(
            "sakura_sapling",

            () -> new BlockItem(
                    Blocks.SAKURA_SAPLING_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_SLAB_BLOCK_ITEM = REGISTER.register(
            "sakura_slab",

            () -> new BlockItem(
                    Blocks.SAKURA_SLAB_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_PLANKS_BLOCK_ITEM = REGISTER.register(
            "sakura_planks",

            () -> new BlockItem(
                    Blocks.SAKURA_PLANKS_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> STRIPPED_SAKURA_LOG_BLOCK_ITEM = REGISTER.register(
            "stripped_sakura_log",

            () -> new BlockItem(
                    Blocks.STRIPPED_SAKURA_LOG_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_WOOD_BLOCK_ITEM = REGISTER.register(
            "sakura_wood",

            () -> new BlockItem(
                    Blocks.SAKURA_WOOD_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> STRIPPED_SAKURA_WOOD_BLOCK_ITEM = REGISTER.register(
            "stripped_sakura_wood",

            () -> new BlockItem(
                    Blocks.STRIPPED_SAKURA_WOOD_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> STRIPPED_STAIRS_BLOCK_ITEM = REGISTER.register(
            "sakura_stairs",

            () -> new BlockItem(
                    Blocks.SAKURA_STAIRS_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_FENCE_ITEM = REGISTER.register(
            "sakura_fence",

            () -> new BlockItem(
                    Blocks.SAKURA_FENCE_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_BARREL_ITEM = REGISTER.register(
            "sakura_barrel",

            () -> new BlockItem(
                    Blocks.SAKURA_BARREL_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_CHEST_ITEM = REGISTER.register(
            "sakura_chest",

            () -> new BlockItem(
                    Blocks.SAKURA_CHEST_BLOCK.get().getBlock(),
                    getPropsForChest()
            )
    );

    public static final RegistryObject<Item> SAKURA_FENCE_GATE_ITEM = REGISTER.register(
            "sakura_fence_gate",

            () -> new BlockItem(
                    Blocks.SAKURA_FENCE_GATE_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_SIGN_ITEM = REGISTER.register(
            "sakura_sign",

            () -> new SignItem(
                    new Item.Properties().group(GROUP)
                            .maxStackSize(16),

                    Blocks.SAKURA_SIGN_BLOCK.get(),
                    Blocks.SAKURA_WALL_SIGN_BLOCK.get()
            )
    );

    public static final RegistryObject<Item> SAKURA_BOAT_ITEM = REGISTER.register(
            "sakura_boat",

            () -> new SakuraBoatItem(
                    new Item.Properties().group(GROUP).maxStackSize(1)
            )
    );

    public static final RegistryObject<Item> SAKURA_BLOSSOM_ITEM = REGISTER.register(
            "sakura_blossom",

            () -> new Item(
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_TEA_ITEM = REGISTER.register(
            "sakura_tea",

            () -> new SakuraTeaItem(
                    new Item.Properties().group(GROUP)
                            .maxStackSize(1)
                            .food(Foods.BAKED_POTATO)  // Same hunger/sat that we want
            )
    );

    public static final RegistryObject<Item> SAKURA_SHIELD_ITEM = REGISTER.register(
            "sakura_shield",

            () -> new ShieldItem(getPropsForShield())
    );

    public static final RegistryObject<Item> WHITE_CLAY_ITEM = REGISTER.register(
            "white_clay",

            () -> new Item(
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHITE_BRICK_ITEM = REGISTER.register(
            "white_brick",

            () -> new Item(
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> PINK_CLAY_ITEM = REGISTER.register(
            "pink_clay",

            () -> new Item(
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> PINK_BRICK_ITEM = REGISTER.register(
            "pink_brick",

            () -> new Item(
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHITE_BRICKS_ITEM = REGISTER.register(
            "white_bricks",

            () -> new BlockItem(
                    Blocks.WHITE_BRICKS_BLOCK.get(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHITE_BRICKS_BLOCK_ITEM = REGISTER.register(
            "white_bricks_stairs",

            () -> new BlockItem(
                    Blocks.WHITE_BRICKS_STAIRS_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHITE_BRICKS_WALL_ITEM = REGISTER.register(
            "white_bricks_wall",

            () -> new BlockItem(
                    Blocks.WHITE_BRICKS_WALL_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHITE_BRICKS_SLAB_ITEM = REGISTER.register(
            "white_bricks_slab",

            () -> new BlockItem(
                    Blocks.WHITE_BRICKS_SLAB_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> PINK_BRICKS_ITEM = REGISTER.register(
            "pink_bricks",

            () -> new BlockItem(
                    Blocks.PINK_BRICKS_BLOCK.get(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> PINK_BRICKS_BLOCK_ITEM = REGISTER.register(
            "pink_bricks_stairs",

            () -> new BlockItem(
                    Blocks.PINK_BRICKS_STAIRS_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> PINK_BRICKS_WALL_ITEM = REGISTER.register(
            "pink_bricks_wall",

            () -> new BlockItem(
                    Blocks.PINK_BRICKS_WALL_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> PINK_BRICKS_SLAB_ITEM = REGISTER.register(
            "pink_bricks_slab",

            () -> new BlockItem(
                    Blocks.PINK_BRICKS_SLAB_BLOCK.get().getBlock(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> SAKURA_LEAF_CARPET_ITEM = REGISTER.register(
            "sakura_leaf_carpet",

            () -> new BlockItem(
                    Blocks.SAKURA_LEAF_CARPET.get(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> ALT_SAKURA_LEAF_CARPET_ITEM = REGISTER.register(
            "alt_sakura_leaf_carpet",

            () -> new BlockItem(
                    Blocks.ALT_SAKURA_LEAF_CARPET.get(),
                    new Item.Properties().group(GROUP)
            )
    );

    public static final RegistryObject<Item> WHITE_SAKURA_LEAF_CARPET_ITEM = REGISTER.register(
            "white_sakura_leaf_carpet",

            () -> new BlockItem(
                    Blocks.WHITE_SAKURA_LEAF_CARPET.get(),
                    new Item.Properties().group(GROUP)
            )
    );

// TODO: Cherry pie textures
//
//    private static final RegistryObject<Item> CHERRY_PIE_ITEM = REGISTER.register(
//            "cherry_pie",
//
//            () -> new BlockItem(
//                    Blocks.CHERRY_PIE_BLOCK.get(),
//                    new Item.Properties().group(GROUP)
//            )
//    );

    private static Item.Properties getPropsForShield() {
        Item.Properties props = new Item.Properties().
                maxDamage(336)
                .group(GROUP);

        DistExecutor.runForDist(
                () -> () -> props.setISTER(() -> SakuraShieldRenderer::new),
                () -> () -> props
        );

        return props;
    }

    private static Item.Properties getPropsForChest() {
        Item.Properties props = new Item.Properties().group(GROUP);

        DistExecutor.runForDist(
                () -> () -> props.setISTER(() -> SakuraChestItemStackRenderer::new),
                () -> () -> props
        );

        return props;
    }
}
