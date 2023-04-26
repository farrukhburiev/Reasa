package farrukh.example.reasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import farrukh.example.reasa.databinding.ActivityMainBinding
import farrukh.example.reasa.databinding.FragmentSplashBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}