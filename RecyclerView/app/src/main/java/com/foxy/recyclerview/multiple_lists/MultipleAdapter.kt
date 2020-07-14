package com.foxy.recyclerview.multiple_lists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree

class MultipleAdapter(
    private val trees: ArrayList<Tree>
) : RecyclerView.Adapter<MultipleAdapter.GenericViewHolder>() {

    private val list1 = mutableListOf<Tree>()
    private val list2 = mutableListOf<Tree>()

    init {
        sortTrees()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view: View
        return when(viewType) {
            FIRST_LIST_ITEM_VIEW -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_tree_multiple_1, parent, false)
                FirstListItemViewHolder(view)
            }
            else -> {
                // SECOND_LIST_ITEM_VIEW
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_tree_multiple_2, parent, false)
                SecondListItemViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bindView(position)
        holder.itemView.setOnClickListener { updateUi(holder.adapterPosition, holder.itemView.context) }
    }

    override fun getItemCount(): Int = trees.size


    override fun getItemViewType(position: Int): Int {
        when {
            list1.size > 0 && list2.size > 0 -> {
                return if (position >= list1.size) SECOND_LIST_ITEM_VIEW
                else FIRST_LIST_ITEM_VIEW
            }
            list1.size == 0 && list2.size > 0 -> return SECOND_LIST_ITEM_VIEW
            list1.size > 0 && list2.size == 0 -> return FIRST_LIST_ITEM_VIEW
        }
        return super.getItemViewType(position)
    }

    private fun updateUi(position: Int, context: Context) {
        if (position >= list1.size) {
            list2[position - list1.size].clicked = !list2[position - list1.size].clicked
        } else {
            list1[position].clicked = !list1[position].clicked
        }
        sortTrees()
//        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
    }

    private fun sortTrees() {
        list1.clear()
        list2.clear()
        for (tree in trees) {
            if (tree.clicked) {
                list2.add(tree)
            } else {
                list1.add(tree)
            }
        }
        notifyDataSetChanged()
    }



    abstract class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindView(position: Int)
    }

    private inner class FirstListItemViewHolder(itemView: View) : GenericViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)

        override fun bindView(position: Int) {
            name.text = list1[position].name
            description.text = list1[position].description
        }
    }

    private inner class SecondListItemViewHolder(itemView: View) : GenericViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)

        override fun bindView(position: Int) {
            val i = if (list1.size > 0) {
                position - list1.size
            } else {
                position
            }
            name.text = list2[i].name
            description.text = list2[i].description
        }
    }


    companion object {
        const val FIRST_LIST_ITEM_VIEW = 1
        const val SECOND_LIST_ITEM_VIEW = 2
    }

}