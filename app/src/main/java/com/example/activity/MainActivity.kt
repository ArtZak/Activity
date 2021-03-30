package com.example.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Contacts
import android.view.View
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var timerText: TextView
    var millisUntilFinish: Long = 60000
    var flag = false

    var timer = object : CountDownTimer(60000, 1000){
        override fun onTick(millisUntilFinished: Long) {
            timerText.text = (millisUntilFinished / 1000).toString()
            millisUntilFinish = millisUntilFinished
        }

        override fun onFinish() {
            this.cancel()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timer_text)
    }

    fun startClick(view: View){
        timer.start()
    }

    fun nextClick(view: View){
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("secondUntilFinished", millisUntilFinish)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
        flag = true
    }

    override fun onStart() {
        super.onStart()
        if(flag){
            timer = object : CountDownTimer(millisUntilFinish, 1000){
                override fun onTick(millisUntilFinished: Long) {
                    timerText.text = (millisUntilFinished / 1000).toString()
                    millisUntilFinish = millisUntilFinished
                }

                override fun onFinish() {
                    this.cancel()
                }
            }.start()
        }
    }
}