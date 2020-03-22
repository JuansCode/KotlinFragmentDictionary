package com.example.kotlindictionary.model

import android.os.Parcelable
import com.example.kotlindictionary.model.ListItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UrbanResponse(
	val list: List<ListItem?>? = null
) : Parcelable
