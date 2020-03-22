package com.example.kotlindictionary.view.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlindictionary.R
import com.example.kotlindictionary.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }


}
