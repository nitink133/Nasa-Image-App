package `in`.obvious.nitin.nasaapp.utils.functional

import java.util.*

/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
object StringUtils {

    @JvmStatic
    fun toFirstCharCapital(value: String?, replaceComma: Boolean = false): String {
        if (value.isNullOrEmpty()) return ""
        val upperString: String =
            value.substring(0, 1).uppercase(Locale.getDefault()) + value.substring(1)
                .lowercase(Locale.getDefault())

        if (replaceComma)
            upperString.replace(",", "")
        return upperString
    }

}
