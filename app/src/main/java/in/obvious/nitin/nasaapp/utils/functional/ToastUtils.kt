package `in`.obvious.nitin.nasaapp.utils.functional

import android.content.Context
import android.widget.Toast


/**
 * @author Nitin Khanna
 * @date 13/09/2022
 */
object ToastUtils {

    fun showMessage(context: Context?, message: String?, duration: Int = Toast.LENGTH_SHORT) {
        if (context == null || message.isNullOrEmpty()) return
        Toast.makeText(context, message, duration).show()
    }
}