package com.foxy.recyclerview.nested_rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree

class ChildNestedAdapter(
    private val trees: ArrayList<Tree>
) : RecyclerView.Adapter<ChildNestedAdapter.ChildNestedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildNestedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tree_simple, parent, false)
        return ChildNestedViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildNestedViewHolder, position: Int) {
        val tree = trees[position]
        holder.names.text = tree.name
        holder.description.text = tree.description
    }

    override fun getItemCount(): Int = trees.size


    class ChildNestedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val names: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
    }

}