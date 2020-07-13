package com.foxy.recyclerview.nested_rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree

class ParentNestedAdapter(
    private val trees: ArrayList<Tree>
) : RecyclerView.Adapter<ParentNestedAdapter.ParentNestedViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentNestedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tree_nested, parent, false)
        return ParentNestedViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentNestedViewHolder, position: Int) {
        val tree = trees[position]
        holder.name.text = tree.name
        holder.description.text = tree.description
        holder.recyclerView.apply {
            adapter = ChildNestedAdapter(trees)
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int = trees.size


    class ParentNestedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_nested_name)
        val description: TextView = itemView.findViewById(R.id.tv_nested_description)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view_child)
    }

}