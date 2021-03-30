package com.example.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    lateinit var timerText: TextView
    lateinit var timer: CountDownTimer
    var flag = false
    var millis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second);

        millis = intent.getLongExtra("secondUntilFinished", 0)

        timerText = findViewById(R.id.timer_text_s)

        timer = object : CountDownTimer(millis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = (millisUntilFinished / 1000).toString()
                millis = millisUntilFinished
            }

            override fun onFinish() {
                this.cancel()
            }
        }
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
        flag = true
    }

    override fun onStart() {
        super.onStart()
        if(flag){
            timer = object : CountDownTimer(millis, 1000){
                override fun onTick(millisUntilFinished: Long) {
                    timerText.text = (millisUntilFinished / 1000).toString()
                    millis = millisUntilFinished
                }

                override fun onFinish() {
                    this.cancel()
                }
            }.start()
        }
    }
}