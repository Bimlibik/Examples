package com.foxy.recyclerview.concat_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree

class ListItemAdapter(
    private val trees: ArrayList<Tree>
) : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tree_simple, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val tree = trees[position]
        holder.bindView(tree)
    }

    override fun getItemCount(): Int = trees.size


    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val description: TextView = itemView.findViewById(R.id.description)

        fun bindView(tree: Tree) {
            name.text = tree.name
            description.text = tree.description
        }
    }


}