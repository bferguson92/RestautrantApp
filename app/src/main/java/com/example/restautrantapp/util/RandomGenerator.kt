package com.example.restautrantapp.util

object RandomGenerator {

    fun getRandomNumber(): Int{
        return (2 until 10).random()
    }
}