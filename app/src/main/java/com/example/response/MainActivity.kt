package com.example.response

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    val RED: Int = 0xFF2800
    var userTimeStart: Long = 0
    var userTimeEnd: Long = 0
    var start: Boolean = false
    //start == check if start button is clicked
    var mTimer = Timer()
    var userRec : Double = 0.0
    var records : ArrayList<Double> = ArrayList(Arrays.asList(99.999))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rank.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            intent.putExtra("records", records)
            startActivity(intent)
        }

        buttonStart.setOnClickListener {
            response.text = "Response"
//            Thread.sleep(nextInt(3, 6) * 1000.toLong())
            mTimer.schedule(GameStart(), nextInt(3, 6) * 1000.toLong())
//            imageView.setColorFilter(Color.GREEN)
//            userTimeStart = System.currentTimeMillis()
            start = true
        }

        imageView.setOnClickListener {
            if (start) {
                imageView.setColorFilter(RED)
                userTimeEnd = System.currentTimeMillis()

                if (userTimeStart != 0.toLong() && userTimeEnd != 0.toLong()) {
                    userRec = (userTimeEnd - userTimeStart) / 1000.0
                    response.text = "기록: $userRec"
                    records.add(userRec)
                    start = false
                } else {
                    response.text = "Response"
                }
                userTimeStart = 0
                userTimeEnd = 0
            } else {
                response.text = "start를 누르세요."
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

//    fun time(check: Boolean) {
//        if (!check)
//            response.text = "너무 빠릅니다, 다시 시작하세요"
//        else
//            userTimeStart = System.currentTimeMillis()
//    }

    inner class GameStart : TimerTask() {
        override fun run() {
            imageView.setColorFilter(Color.GREEN)
            userTimeStart = System.currentTimeMillis()
        }
    }
}
