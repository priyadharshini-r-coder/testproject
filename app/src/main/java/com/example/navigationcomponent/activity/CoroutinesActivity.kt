package com.example.navigationcomponent.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelLazy
import com.example.navigationcomponent.R
import com.example.navigationcomponent.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class CoroutinesActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by ViewModelLazy(
        MainViewModel::class,
        {viewModelStore },
        { defaultViewModelProviderFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
//        sampleSuspendFunc()
        viewModel.exampleMethodUsingAsync()
//        viewModel.sampleRunBlocking()
    }

    private fun sampleSuspendFunc() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                val one = sampleOne()
                val two = sampleTwo()
                println("The answer is ${one + two}")
            }
            println("Completed in $time ms")
        }
        println("EOF")
    }

    private suspend fun sampleOne(): Int {
        println( "sampleOne"+System.currentTimeMillis())
        delay(1000L)
        return 10
    }

    private suspend fun sampleTwo(): Int {
        println( "sampleTwo"+System.currentTimeMillis())
        delay(1000L)
        return 10
    }
}