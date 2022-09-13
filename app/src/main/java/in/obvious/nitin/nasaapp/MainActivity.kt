package `in`.obvious.nitin.nasaapp

import `in`.obvious.nitin.nasaapp.base.activity.BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}