package io.github.faecraft.sakurarosea.mixin.common;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;

@Mixin(value = RecipeManager.class)
public abstract class RecipeManagerMixin {
    /*
     * Based on code from Oh How The Crafting Has Tables, which itself was based on code from Better Nether.
     *
     * This mixin changes the recipe priority order so that vanilla recipes are never favoured over recipes from other
     * namespaces.
     *
     * https://github.com/P3NG00/OhHowTheCraftingHasTabled/blob/master/src/main/java/com/p3ng00/morecraftingtables/mixin/RecipeManagerMixin.java
     */

    @Shadow
    private <C extends Inventory, T extends Recipe<C>> Map<Identifier, Recipe<C>> getAllOfType(RecipeType<T> type) {
        return null;
    }

    @SuppressWarnings("ConstantConditions")  // getAllOfType doesn't really return null
    @Overwrite
    public <C extends Inventory, T extends Recipe<C>> Optional<T> getFirstMatch(RecipeType<T> type, C inventory, World world) {
        Collection<Recipe<C>> values = getAllOfType(type).values();

        List<Recipe<C>> recipes = new ArrayList<>(values);
        String minecraftNamespace = new Identifier("").getNamespace();

        recipes.sort((first, second) -> {
            boolean isMinecraft = first.getId().getNamespace().equals(minecraftNamespace);

//            if (isMinecraft ^ second.getId().getNamespace().equals(minecraftNamespace)) {  // XOR
//                if (isMinecraft) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            } else {
//                return 0;
//            }

            // Ternaries are all very well, but I prefer to expand them for readability
             return (isMinecraft ^ second.getId().getNamespace().equals(minecraftNamespace)) ? (isMinecraft ? 1 : -1) : 0;
        });

        return recipes.stream().flatMap((recipe -> Util.stream(type.get(recipe, world, inventory)))).findFirst();
    }
}
