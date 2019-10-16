package com.example.restautrantapp.util

import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.restautrantapp.model.Waiter
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor

object HandlerUtil {

    lateinit var handler: Handler
    lateinit var taskList: MutableList<Runnable>
    private val taskQueue: BlockingQueue<Runnable> = LinkedBlockingQueue<Runnable>()
    var threadPoolExecutor: ThreadPoolExecutor? = null

    fun setProperties(handler: Handler, taskList: MutableList<Runnable>) {
        this.handler = handler
        this.taskList = taskList
    }

    fun executeTasks() {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = ThreadPoolExecutor(
                Constants.CORE_POOL_SIZE,
                Constants.MAX_POOL_SIZE,
                Constants.TIME_LIMIT,
                Constants.TIME_UNIT, taskQueue
            )
        }

        taskList.forEach{currentTask ->
            threadPoolExecutor?.execute(currentTask)
        }
    }

    fun sendMessage(waiter: Waiter){
        val msg = Message()
        val bundle = Bundle()

        bundle.putParcelable(Constants.MESSAGE_KEY, waiter)
        msg.data= bundle

        handler.sendMessage(msg)
    }
}