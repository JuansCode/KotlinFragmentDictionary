package com.example.kotlindictionary.view.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kotlindictionary.R
import com.example.kotlindictionary.databinding.ActivityMainBinding
import com.example.kotlindictionary.util.showLoadingDialog
import com.example.kotlindictionary.util.viewModel
import com.example.kotlindictionary.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding : ActivityMainBinding
    //Lazy delegate instance of VM passing lambda
    private val urbanModel: MainViewModel by viewModel {
        MainViewModel()
    }
    private val dialog: AlertDialog by showLoadingDialog {
        cancelable = false
        setMessage("Loading data...")
        setRetryVisibility()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        urbanModel.dataLoading.observe(this, Observer {aBoolean->
            if(aBoolean !!){
                dialog.show()
            }else{
                dialog.dismiss()
            }
        })

    }


}
