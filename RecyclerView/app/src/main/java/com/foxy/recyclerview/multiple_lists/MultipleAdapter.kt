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
) : RecyclerView.Adapter<MultipleAdapter.ViewHolder>() {

    private val list1 = mutableListOf<Tree>()
    private val list2 = mutableListOf<Tree>()

    init {
        sortTrees()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var pos = position
        when(holder.itemViewType) {
            FIRST_LIST_ITEM_VIEW -> {
                val tree = list1[pos]
                holder.bindFirstList(tree)
            }
            SECOND_LIST_ITEM_VIEW -> {
                pos = if (list1.size > 0) {
                    position - list1.size
                } else {
                    position
                }
                val tree = list2[pos]
                holder.bindSecondList(tree)
            }
        }

        holder.itemView.setOnClickListener { updateUi(holder.adapterPosition, holder.itemView.context) }
    }

    override fun getItemCount(): Int = trees.size
//        return when {
//            list1.size > 0 && list2.size > 0 -> list1.size + list2.size
//            list1.size == 0 && list2.size > 0 -> list2.size
//            list1.size > 0 && list2.size == 0 -> list1.size
//            else -> 0
//        }
//    }

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
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
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


   open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // view для первого списка
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)

        // view для второго списка
        val name2: TextView = itemView.findViewById(R.id.name)
        val description2: TextView = itemView.findViewById(R.id.description)

        fun bindSecondList(tree: Tree) {
            name2.text = tree.name
            description2.text = tree.description
        }

        fun bindFirstList(tree: Tree) {
            name.text = tree.name
            description.text = tree.description
        }

    }

    private class FirstListItemViewHolder(itemView: View?) : ViewHolder(itemView!!)

    private class SecondListItemViewHolder(itemView: View?) : ViewHolder(itemView!!)


    companion object {
        const val FIRST_LIST_ITEM_VIEW = 1
        const val SECOND_LIST_ITEM_VIEW = 2
    }

}