package com.example.kotlindictionary.viewmodel.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindictionary.R
import com.example.kotlindictionary.model.ListItem
import com.example.kotlindictionary.util.*
import kotlinx.android.synthetic.main.definition_list_item.view.*
import java.util.ArrayList

class UrbanAdapter(
    private val urban_list: ArrayList<ListItem>
) : CustomRecyclerViewAdapter<ListItem, UrbanAdapter.MyViewHolder>() {


    override fun updateData(data: List<ListItem>) {
            this.urban_list.clear()
            this.urban_list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(parent.inflate(R.layout.definition_list_item))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.loadData(position)

    override fun getItemCount() : Int = urban_list.size


    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun loadData(position: Int) {
            val spaceXModel = urban_list[position]
            view.apply {
                tv_definition.text = spaceXModel.definition
                tv_word.text = spaceXModel.word
                tv_author.text = spaceXModel.author
                tv_examples.text = spaceXModel.example
                tv_thumbs_up.text = spaceXModel.thumbs_up.toString()
                tv_thumbs_down.text = spaceXModel.thumbs_down.toString()
                tv_date.text = spaceXModel.written_on?.toDate()?.formatTo(Utils.dateFormatYYYYMMDD)
            }

        }



    }

}