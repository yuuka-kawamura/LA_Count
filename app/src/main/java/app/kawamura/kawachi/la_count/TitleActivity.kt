package app.kawamura.kawachi.la_count

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.kawamura.kawachi.la_count.databinding.ActivityMainBinding
import app.kawamura.kawachi.la_count.databinding.ActivityTitleBinding

class TitleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTitleBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.nextButton.setOnClickListener{
            val toMainActivityIntent= Intent(this,MainActivity::class.java)
            startActivity(toMainActivityIntent)
        }
    }
}