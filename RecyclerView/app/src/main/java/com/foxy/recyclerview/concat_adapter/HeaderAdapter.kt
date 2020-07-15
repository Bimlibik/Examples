package com.foxy.recyclerview.concat_adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R

class HeaderAdapter(
    private val header: Header
) : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bindView(header)
    }

    override fun getItemCount(): Int = 1


    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerView: TextView = itemView.findViewById(R.id.header)

        fun bindView(header: Header) {
            headerView.text = header.title
            headerView.setTextColor(Color.parseColor(header.color))
            headerView.textSize = header.textSize
        }
    }


}