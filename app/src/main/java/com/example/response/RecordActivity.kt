package com.example.response

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_records.*

class RecordActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)
        var rec = intent.getDoubleArrayExtra("records")
        records(rec)
        //TODO: rec is null. why?
    }

    fun records(rec : DoubleArray){
        rec.sort()

        if (rec[0] != null){
            textView1.text = "1. ${rec[0]}"
        }
        if (rec[1] != null){
            textView2.text = "1. ${rec[1]}"
        }
        if (rec[2] != null){
            textView3.text = "1. ${rec[2]}"
        }
        if (rec[3] != null){
            textView4.text = "1. ${rec[3]}"
        }
        if (rec[4] != null){
            textView5.text = "1. ${rec[4]}"
        }
    }
}