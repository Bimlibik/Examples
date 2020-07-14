package com.foxy.recyclerview.header_and_footer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree
import kotlinx.android.synthetic.main.fragment_trees_list_multiple.*


class HeaderAndFooterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trees_list_nested, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            adapter = HeaderAndFooterAdapter(createData())
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
        fun newInstance() = HeaderAndFooterFragment()
    }
}