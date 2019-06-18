package com.example.response

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.target.SimpleTarget
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
    var userRec: Double = 0.0
    var scoreArr = ArrayList<String>()
    //ArrayList<Double> got problem when RecordActivity get intent
    //ArrayList is not empty or null, but RecordActivity gets null
    var foulCheck: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, RecordActivity::class.java)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageLocation = IntArray(2)
        imageView.getLocationInWindow(imageLocation)
        val imageX = imageLocation[0] + imageView.width / 2f
        val imageY = imageLocation[1] + imageView.height / 2f
        val imageRadious = 100f

        val firstTatget = SimpleTarget.Builder(this)
            .setPoint(imageX, imageY)
            .setShape(Circle(imageRadious))
            .setTitle("First title")
            .setDescription("First description")
            .setOverlayPoint(100f, imageY + imageRadious + 100f)
            .build()

        val startButton = findViewById<Button>(R.id.buttonStart)
        val sbLocation = IntArray(2)
        startButton.getLocationInWindow(sbLocation)
        val startX = sbLocation[0] + startButton.width / 2f
        val startY = sbLocation[1] + startButton.height / 2f
        val startRadious = 100f

        val secondTarget = SimpleTarget.Builder(this)
            .setPoint(startX, startY)
            .setShape(Circle(startRadious))
            .setTitle("Second Title")
            .setDescription("Second describtion")
            .setOverlayPoint(100f, startY + startRadious + 100f)
            .build()

        Spotlight.with(this)
            .setOverlayColor(R.color.background)
            .setDuration(1000L)
            .setAnimation(DecelerateInterpolator(2f))
            .setTargets(firstTatget, secondTarget)
            .setClosedOnTouchedOutside(true)
            .start()

        rank.setOnClickListener {
            intent.putExtra("scoreArr", scoreArr)
            startActivity(intent)
        }

        buttonStart.setOnClickListener {
            response.textSize = 30f
            response.text = "Response"
            foulCheck = true
            mTimer.schedule(GameStart(), nextInt(3, 6) * 1000.toLong())
            start = true
        }

        imageView.setOnClickListener {
            response.textSize = 30f
            if (start) {
                imageView.setColorFilter(RED)
                userTimeEnd = System.currentTimeMillis()

                if (userTimeStart != 0.toLong() && userTimeEnd != 0.toLong() && !foulCheck) {
                    userRec = (userTimeEnd - userTimeStart) / 1000.0
                    response.text = "기록: $userRec"
                    scoreArr.add(userRec.toString())
                    start = false
                } else if (foulCheck) {
                    response.textSize = 20f
                    response.text = "너무 빨리 눌렀습니다.\n다시 시작하세요."
                    foulCheck = false
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

    fun onClick(v : View){
        response.textSize = 30f
    }

    inner class GameStart : TimerTask() {
        override fun run() {
            if (foulCheck) {
                foulCheck = false
                imageView.setColorFilter(Color.GREEN)
                userTimeStart = System.currentTimeMillis()
            }
        }
    }
}
