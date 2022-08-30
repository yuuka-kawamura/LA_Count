package app.kawamura.kawachi.la_count

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.kawamura.kawachi.la_count.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mSoundPool: SoundPool
    private var mSoundResID = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var pref:SharedPreferences
    var count: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        pref= getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        val str = pref.getString("COUNT", "NoData")


        binding.tapCountText.text = str
        binding.tapButton.setOnClickListener {

            count = count + 1
            binding.tapCountText.text = count.toString()




            when {
                count % 2 == 0 -> binding.tapCountText.setTextColor(android.graphics.Color.BLUE)
                count % 2 == 1 -> binding.tapCountText.setTextColor(android.graphics.Color.RED)
            }
            if (count % 2 == 1)
                mSoundPool.play(mSoundResID, 1.0f, 100f, 0, 0, 1.0f)


        }
    }

    override fun onResume() {
        super.onResume()
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        mSoundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .build()

        mSoundResID = mSoundPool.load(this, R.raw.music2, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        mSoundPool.release()
        //val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        val editor = pref.edit()
        editor.putString("COUNT", count.toString())
        editor.apply()
    }


}