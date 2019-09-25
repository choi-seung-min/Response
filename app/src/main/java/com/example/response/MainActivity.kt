package com.example.response

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.target.SimpleTarget
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
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

        textView.setOnClickListener{
            val imageView = findViewById<ImageView>(R.id.imageView)
            val imageLocation = IntArray(2)
            imageView.getLocationInWindow(imageLocation)
            val imageX = imageLocation[0] + imageView.width  / 2f
            val imageY = imageLocation[1] + imageView.height / 2f
            val imageRadius = 500f

            val firstTarget = SimpleTarget.Builder(this@MainActivity)
                .setPoint(imageX, imageY)
                .setShape(Circle(imageRadius))
                .setTitle("Response!")
                .setDescription("After you click start button, the color will change.\nTouch this as fast as possible you can!")
                .setOverlayPoint(100f, imageY + imageRadius + 100f)
                .build()

            val buttonStart = findViewById<Button>(R.id.buttonStart)
            val sbLocation = IntArray(2)
            buttonStart.getLocationOnScreen(sbLocation)
            val startX = sbLocation[0] + buttonStart.width / 2f
            val startY = sbLocation[1] + buttonStart.height / 2f
            val startRadius = 300f

            val secondTarget = SimpleTarget.Builder(this@MainActivity)
                .setPoint(startX, startY)
                .setShape(Circle(startRadius))
                .setTitle("Start Button")
                .setDescription("This is start button.\nDo you want to know how fast you are?\nCome on!")
                .setOverlayPoint(100f, startY + startRadius - 1000f)
                .build()
            //overlay point might be the problem

            Spotlight.with(this)
                .setOverlayColor(R.color.background)
                .setDuration(1000L)
                .setAnimation(DecelerateInterpolator(2f))
                .setTargets(firstTarget, secondTarget)
                .setClosedOnTouchedOutside(true)
                .start()
        }

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
        v.response.textSize = 30f
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
