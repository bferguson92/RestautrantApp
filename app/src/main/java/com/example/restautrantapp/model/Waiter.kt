package com.example.restautrantapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Waiter(val name: String, var waitTime: Int, var progress: Int): Parcelable