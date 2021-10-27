package com.raitech.bup.flowapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val f = flow {
            for (num in 1..10) {
                emit("Hello Flow $num")
                delay(1000L)
            }
        }

        val f2 = flow {
            for (num in 1..10) {
                emit(num)
                delay(1000L)
            }
        }

        GlobalScope.launch {
            f.buffer().collect {
                Log.d("MyApp-StringFlow", it)
                delay(2000L)
            }
        }

        GlobalScope.launch {
            f2.buffer()
                .filter {
                    it % 2 == 0
                }
                .map {
                    it * it
                }
                .collect {
                    Log.d("MyApp-IntFlow", it.toString())
                    delay(2000L)
                }
        }
    }
}