package com.foxy.recyclerview.simple_rv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree
import kotlinx.android.synthetic.main.fragment_trees_list.*


class SimpleTreesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trees_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PagerSnapHelper().attachToRecyclerView(recycler_view)

        recycler_view.apply {
            adapter = SimpleTreesAdapter(createData())
        }



        onClickListener()
    }

    private fun onClickListener() {
        btn_linear.setOnClickListener {
            recycler_view.apply { layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true) }
        }
        btn_grid.setOnClickListener {
            recycler_view.apply { layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false) }
        }
        btn_staggered.setOnClickListener {
            recycler_view.apply {
                layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
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
        fun newInstance() = SimpleTreesFragment()
    }
}