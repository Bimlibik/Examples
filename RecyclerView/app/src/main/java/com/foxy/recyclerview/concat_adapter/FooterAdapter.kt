package com.foxy.recyclerview.concat_adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R

class FooterAdapter(
    private val footer: Footer
) : RecyclerView.Adapter<FooterAdapter.FooterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.footer, parent, false)
        return FooterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        holder.bindView(footer)
    }

    override fun getItemCount(): Int = 1


    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val footerView: TextView = itemView.findViewById(R.id.footer)

        fun bindView(footer: Footer) {
            footerView.text = footer.title
            footerView.setTextColor(Color.parseColor(footer.color))
            footerView.textSize = footer.textSize
        }
    }


}