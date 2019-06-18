package com.example.response

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_records.*

class RecordActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        var score : ArrayList<String> = intent.getStringArrayListExtra("scoreArr")
        var set : ArrayList<Double> = ArrayList()

        for(s : String in score){
            set.add(s.toDouble())
        }

        setText(set)

        buttonBack.setOnClickListener {
            finish()
        }
    }

    fun setText(rec : ArrayList<Double>){
        rec.sort()

        if (rec.size > 0){
            textView1.text = "1: ${rec[0]}sec"
        }
        if (rec.size > 1){
            textView2.text = "2: ${rec[1]}sec"
        }
        if (rec.size > 2){
            textView3.text = "3: ${rec[2]}sec"
        }
        if (rec.size > 3){
            textView4.text = "4: ${rec[3]}sec"
        }
        if (rec.size > 4){
            textView5.text = "5: ${rec[4]}sec"
        }
    }
}