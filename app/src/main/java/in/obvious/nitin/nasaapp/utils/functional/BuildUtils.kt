package `in`.obvious.nitin.nasaapp.utils.functional

import `in`.obvious.nitin.nasaapp.BuildConfig


/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
object BuildUtils {
    @JvmStatic
    fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}