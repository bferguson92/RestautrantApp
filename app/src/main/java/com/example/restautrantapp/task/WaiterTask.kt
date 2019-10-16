package com.example.restautrantapp.task

import android.util.Log
import com.example.restautrantapp.model.Waiter
import com.example.restautrantapp.util.HandlerUtil

class WaiterTask(private val name: String, private val countTime: Int) : Runnable {
    override fun run() {
        var time = 0
        while (countTime > time) {
            time += 1
            var waitTime = countTime - time
            Log.d("TAG_TASK", time.toString())
            HandlerUtil.sendMessage(
                Waiter(
                    name,
                    waitTime,
                    ((time.toFloat() / countTime.toFloat()) * 100).toInt()
                ))
            Thread.sleep(60000)
        }
    }
}