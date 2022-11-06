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
import com.example.diceroller.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openButton: Button = findViewById(R.id.button)
        var arrayOfString = arrayOf<String>()
        openButton.setOnClickListener {
            val editTextView: TextView = findViewById(R.id.editText)
            val deeplinkNew = editTextView.text.toString()
            arrayOfString += deeplinkNew
            val history: TextView = findViewById(R.id.textView)
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
            val editTextView: TextView = findViewById(R.id.editText)
            editTextView.startAnimation(animShake)
            val toast = Toast.makeText(applicationContext, R.string.errorMsg, duration)
            toast.show()
        }
    }
}
