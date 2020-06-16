package com.example.restareareview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restareareview.Adapter.EachLoadAdapter
import kotlinx.android.synthetic.main.activity_each_load_map_list.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class EachLoadMapListActivity : AppCompatActivity() {

    var data:ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_each_load_map_list)
        setActionBar()
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = EachLoadAdapter(data)
    }


    private fun setActionBar(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)

        val view = supportActionBar!!.customView
        val homeImageButton = view.findViewById<ImageButton>(R.id.home)
        homeImageButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        val questionImageButton = view.findViewById<ImageButton>(R.id.mail)
        questionImageButton.setOnClickListener {
            val questionIntent = Intent(this, MainActivity::class.java)
            startActivity(questionIntent)
        }

        title_text.text = "고속도로 별 맛집 지도"
    }


    private fun initData(){
        data.add("경부고속도로")
        data.add("중부고속도로")
        data.add("서해안고속도로")
        data.add("호남고속도로")
        data.add("남해고속도로")
        data.add("영동고속도로")
        data.add("중부내륙고속도로")
        data.add("중앙고속도로")
    }


}