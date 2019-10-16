package com.example.restautrantapp.task

import android.os.Handler
import com.example.restautrantapp.util.HandlerUtil

class WaiterRunnable(private val handler: Handler, private val timeList: MutableList<Int>, private val name: MutableList<String>) : Runnable{

    override fun run() {
        val taskList: MutableList<Runnable> = mutableListOf()

        for(i in 0 until timeList.size){
            taskList.add(WaiterTask(name[i], timeList[i]))
        }

        HandlerUtil.setProperties(handler, taskList)
        HandlerUtil.executeTasks()
    }

}