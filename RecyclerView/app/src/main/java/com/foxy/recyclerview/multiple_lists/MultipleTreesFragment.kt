package com.foxy.recyclerview.multiple_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree
import kotlinx.android.synthetic.main.fragment_trees_list_multiple.*


class MultipleTreesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trees_list_nested, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PagerSnapHelper().attachToRecyclerView(recycler_view)

        recycler_view.apply {
            adapter = MultipleAdapter(createData(), createData())
        }
    }

    private fun createData(): ArrayList<Tree> {
        val names = resources.getStringArray(R.array.names)
        val descriptions = resources.getStringArray(R.array.descriptions)
        val trees = mutableListOf<Tree>()

        for (i in names.indices) {
            trees.add(
                Tree(
                    name = names[i],
                    description = descriptions[i]
                )
            )
        }
        return trees as ArrayList<Tree>
    }

    companion object {
        fun newInstance() = MultipleTreesFragment()
    }
}