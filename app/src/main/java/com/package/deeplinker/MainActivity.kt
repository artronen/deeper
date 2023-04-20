package com.`package`.deeplinker

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.deeplinker.R
import com.example.deeplinker.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val openButton: Button = bindingClass.button
        var arrayOfString = arrayOf<String>()
        openButton.setOnClickListener {
            val deeplinkNew: String = bindingClass.editText.text.toString()
            arrayOfString += deeplinkNew
            val history: TextView = bindingClass.historyList
            history.text =
                Arrays.toString(arrayOfString.reversedArray()).replace(",", "\n").replace("[", "")
                    .replace("]", "")
            launchDeeplink(deeplinkNew)
        }

    }


    private fun launchDeeplink(deeplink: String) {
        try {
            val deepLinkIntent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(deeplink))
            startActivity(deepLinkIntent)
        } catch (e: ActivityNotFoundException) {
            val animShake = AnimationUtils.loadAnimation(this, R.anim.shake)
            val duration = Toast.LENGTH_SHORT
            bindingClass.textInputLayout.startAnimation(animShake)
            val toast = Toast.makeText(applicationContext, R.string.errorMsg, duration)
            toast.show()
        }
    }
}
