package com.example.restautrantapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.restautrantapp.R
import com.example.restautrantapp.model.Waiter
import com.example.restautrantapp.task.WaiterRunnable
import com.example.restautrantapp.util.Constants
import com.example.restautrantapp.util.RandomGenerator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Handler.Callback {

     lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler(this)

        btnStart.setOnClickListener {
            runnable = WaiterRunnable(
                handler,
                mutableListOf(
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber()
                ), mutableListOf("Brian", "Luca", "Chris")
            )

            val thread = Thread(runnable)
            thread.start()
        }

    }

    override fun handleMessage(msg: Message): Boolean {
        val waiter: Waiter? = msg.data.getParcelable(Constants.MESSAGE_KEY)
        when(waiter?.name) {
            "Brian" -> {
                tvWaiter1.text = waiter.name
                tvWaitTime1.text = waiter.waitTime.toString() + " Minutes Left"
                pbWaiter1.progress = waiter.progress
            }
            "Luca" ->  {
                tvWaiter2.text = waiter.name
                tvWaitTime2.text = waiter.waitTime.toString() + " Minutes Left"
                pbWaiter2.progress = waiter.progress
            }
            "Chris" -> {
                tvWaiter3.text = waiter.name
                tvWaitTime3.text = waiter.waitTime.toString() + " Minutes Left"
                pbWaiter3.progress = waiter.progress
            }
        }

        return true
    }
}
