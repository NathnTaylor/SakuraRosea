package io.github.faecraft.sakurarosea.register

import io.github.faecraft.sakurarosea.mixin.common.SignTypeMixin
import io.github.faecraft.sakurarosea.sign.UtilSignType

object SignTypes {
    val SAKURA = UtilSignType("sakura")

    fun register() {
        SignTypeMixin.register(SAKURA)
    }
}
