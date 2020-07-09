package com.foxy.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.foxy.recyclerview.simple_rv.SimpleTreesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_simple.setOnClickListener { createSimpleTreesFragment(supportFragmentManager) }

    }

    private fun createSimpleTreesFragment(fm: FragmentManager) {
        val fragment = SimpleTreesFragment.newInstance()
        fm.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()

        btn_simple.visibility = View.GONE
    }

    override fun onBackPressed() {
        btn_simple.visibility = View.VISIBLE
    }
}