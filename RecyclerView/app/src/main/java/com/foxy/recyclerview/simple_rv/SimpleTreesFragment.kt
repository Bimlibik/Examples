package com.foxy.recyclerview.simple_rv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foxy.recyclerview.R

class SimpleTreesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trees_list, container, false)

        if(view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = SimpleTreesAdapter(createData())
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().findViewById<Button>(R.id.btn_simple).visibility = View.VISIBLE
    }

    private fun createData(): ArrayList<Tree> {
        val names = resources.getStringArray(R.array.names)
        val descriptions = resources.getStringArray(R.array.descriptions)
        val trees = mutableListOf<Tree>()

        for (i in names.indices) {
            trees.add(Tree(name = names[i], description = descriptions[i]))
        }
        return trees as ArrayList<Tree>
    }

    companion object {
        fun newInstance() = SimpleTreesFragment()
    }
}