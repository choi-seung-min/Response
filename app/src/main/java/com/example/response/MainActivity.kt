package com.example.response

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    val RED: Int = 0xFF2800
    var userTimeStart: Long = 0
    var userTimeEnd: Long = 0
    var flag : Boolean = false
    //flag == check if color is changed
    var mTimer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rank.setOnClickListener {
            Toast.makeText(this, "Not available now", Toast.LENGTH_LONG).show()
        }

        buttonStart.setOnClickListener {
            Thread.sleep(nextInt(3, 6)*1000.toLong())
//            mTimer.schedule(Custom(), nextInt(3,6)*1000.toLong())
            imageView.setColorFilter(Color.GREEN)
            userTimeStart = System.currentTimeMillis()
//            time(responseStart())
        }

        imageView.setOnClickListener {
            if(!flag){
                imageView.setColorFilter(RED)
                userTimeEnd = System.currentTimeMillis()

                if (userTimeStart != 0.toLong() && userTimeEnd != 0.toLong())
                    response.text = "기록: ${(userTimeEnd - userTimeStart) / 1000.0}"
                else
                    response.text = "Response"

                userTimeStart = 0
                userTimeEnd = 0
                flag = false
            } else{
                response.text = "너무 빠릅니다.\n다시 시작해주세요"
                flag = false
            }
        }
    }

//    fun responseStart(): Boolean {
//        if (!flag){
//            var mTimer = Timer()
//            mTimer.schedule(Custom(), nextInt(3, 6) * 1000.toLong())
////        imageView.setColorFilter(Color.GREEN)
//            return true
//        }
//        else {
//            return false
//        }
//    }

    fun time(check: Boolean) {
        if (!check)
            response.text = "너무 빠릅니다, 다시 시작하세요"
        else
            userTimeStart = System.currentTimeMillis()
    }

    inner class Custom : TimerTask() {
        override fun run() {
            flag = true
            imageView.setColorFilter(Color.GREEN)
        }
    }
}
