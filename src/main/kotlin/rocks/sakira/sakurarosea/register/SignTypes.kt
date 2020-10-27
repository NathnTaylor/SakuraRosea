package rocks.sakira.sakurarosea.register

import rocks.sakira.sakurarosea.mixin.common.SignTypeMixin
import rocks.sakira.sakurarosea.sign.UtilSignType

object SignTypes {
    val SAKURA = UtilSignType("sakura")

    fun register() {
        SignTypeMixin.register(SAKURA)
    }
}
