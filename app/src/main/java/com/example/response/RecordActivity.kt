package com.example.response

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_records.*

var arr : ArrayList<Double> = ArrayList()

class RecordActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)
//        var bundle : Bundle = intent.getBundleExtra("bundle")
//        var rec : ArrayList<Double> = bundle.getDoubleArray("records")
//        var intent : Intent = intent
        var rec : Double = intent.getDoubleExtra("rec", 99.999)
        //TODO: rec have nothing so i get 99.999
        arr.add(rec)
        setText(arr)
    }

    fun setText(rec : ArrayList<Double>){
        rec.sort()

        if (rec.size > 1){
            textView1.text = "1. ${rec[0]}sec"
        }
        if (rec.size > 2){
            textView2.text = "1. ${rec[1]}sec"
        }
        if (rec.size > 3){
            textView3.text = "1. ${rec[2]}sec"
        }
        if (rec.size > 4){
            textView4.text = "1. ${rec[3]}sec"
        }
        if (rec.size > 5){
            textView5.text = "1. ${rec[4]}sec"
        }
    }
}