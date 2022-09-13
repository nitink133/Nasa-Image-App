package `in`.obvious.nitin.nasaapp.utils.functional

import `in`.obvious.nitin.nasaapp.constants.ResourceFile
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

object ResourceUtils {

    @JvmStatic
    inline fun <reified T> readResourceFile(context: Context?, resourceFile: ResourceFile): T? {
        if (context == null) return null
        val `is`: InputStream = context.resources.openRawResource(resourceFile.file)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        `is`.use { `is` ->
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        val jsonString: String = writer.toString()
        return Gson().fromJson(
            jsonString,
            if (resourceFile.isList) object : TypeToken<List<T>>() {}.type else T::class.java
        )
    }
}