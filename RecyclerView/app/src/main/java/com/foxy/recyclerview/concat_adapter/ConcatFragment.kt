package com.foxy.recyclerview.concat_adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.foxy.recyclerview.R
import com.foxy.recyclerview.Tree
import kotlinx.android.synthetic.main.fragment_trees_list_multiple.*


class ConcatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trees_list_nested, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headerAdapter = HeaderAdapter(Header("Я - header!", "#283593", 25F))
        val treesAdapter = ListItemAdapter(createData())
        val footerAdapter = FooterAdapter(Footer("Я - footer!", "#6A1B9A", 25F))

//        список из адаптеров, который можно передавать в конструктор класса ConcatAdapter
//        val listOfAdapters = listOf(headerAdapter, treesAdapter, footerAdapter)

        val concatAdapter = ConcatAdapter(headerAdapter, treesAdapter)

//        добавление адаптера в конец
//        concatAdapter.addAdapter(footerAdapter)

//      добавление адаптера по индексу
        concatAdapter.addAdapter(2, footerAdapter)


        recycler_view.apply {
            adapter = concatAdapter
        }

//        удаление адаптера
//        concatAdapter.removeAdapter(headerAdapter)

//        узнать количество элементов (суммируется со всех добавленных адаптеров)
//        в данном случае результат = 8 (header + footer + 6 элементов в массиве trees)
        Log.i("ConcatFragment", "Количество адаптеров: ${concatAdapter.itemCount}")

//        возвращает MutableList, состоящий из ссылок на все добавленные адаптеры
        Log.i("ConcatFragment", "Адаптеры: ${concatAdapter.adapters}")

    }

    private fun createData(): ArrayList<Tree> {
        val names = resources.getStringArray(R.array.names)
        val descriptions = resources.getStringArray(R.array.descriptions)
        val trees = mutableListOf<Tree>()

        for (i in names.indices) {
            if (i == 6) break
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
        fun newInstance() = ConcatFragment()
    }
}