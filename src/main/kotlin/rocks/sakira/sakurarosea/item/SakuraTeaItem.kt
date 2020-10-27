package rocks.sakira.sakurarosea.item

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.world.World

class SakuraTeaItem(settings: Settings) : Item(settings) {
    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        user.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED, 8 * 20, 1))

        return if (user is PlayerEntity && user.abilities.creativeMode) {
            stack
        } else {
            ItemStack(Items.BOWL)
        }
    }
}
