package com.example.kotlindictionary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(
	val defid: Int? = null,
	val sound_urls: List<String?>? = null,
	val thumbs_down: Int? = null,
	val author: String? = null,
	val written_on: String? = null,
	val definition: String? = null,
	val permalink: String? = null,
	val thumbs_up: Int? = null,
	val word: String? = null,
	val current_vote: String? = null,
	val example: String? = null
) : Parcelable
