package com.foxy.recyclerview.header_and_footer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree

class HeaderAndFooterAdapter(
    private val trees: ArrayList<Tree>
) : RecyclerView.Adapter<HeaderAndFooterAdapter.GenericViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view: View
        return when(viewType) {
            HEADER_VIEW -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
                HeaderViewHolder(view)
            }
            FOOTER_VIEW -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.footer, parent, false)
                FooterViewHolder(view)
            }
            else -> {
                // LIST_ITEM_VIEW
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_tree_simple, parent, false)
                ListItemViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = trees.size + 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_VIEW
            trees.size + 1 -> FOOTER_VIEW
            else -> LIST_ITEM_VIEW
        }
    }



   abstract class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       abstract fun bindView(position: Int)
    }

    private inner class ListItemViewHolder(itemView: View) : GenericViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)

        override fun bindView(position: Int) {
            name.text = trees[position - 1].name
            description.text = trees[position - 1].description
        }
    }

    private class HeaderViewHolder(itemView: View) : GenericViewHolder(itemView) {
        val header: TextView = itemView.findViewById(R.id.header)

        override fun bindView(position: Int) {
            header.text = "I'm a header"
        }
    }

    private class FooterViewHolder(itemView: View) : GenericViewHolder(itemView) {
        val footer: TextView = itemView.findViewById(R.id.footer)

        override fun bindView(position: Int) {
            footer.text = "I'm a footer"
        }
    }


    companion object {
        const val HEADER_VIEW = 1
        const val LIST_ITEM_VIEW = 2
        const val FOOTER_VIEW = 3
    }

}