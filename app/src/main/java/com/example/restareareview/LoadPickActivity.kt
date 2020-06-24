package com.example.restareareview

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restareareview.Adapter.EachLoadAdapter
import com.example.restareareview.Adapter.LoadListAdapter
import com.example.restareareview.EachLoadMap.*
import kotlinx.android.synthetic.main.activity_each_load_map_list.*
import kotlinx.android.synthetic.main.activity_load_pick.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.ref.WeakReference
import java.net.URL

class LoadPickActivity :  AppCompatActivity() {

    var loadData:ArrayList<String> = ArrayList<String>()
    lateinit var adapter:LoadListAdapter
    var cnt: Int? = null
//    var urlList: MutableList<URL>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_pick)
        setActionBar()
        initData()
        initRecyclerView()
//        init()
//        startJSONTask()
    }

    private fun setActionBar() {
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
            val questionIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jackoss@naver.com", null))
            questionIntent.putExtra(Intent.EXTRA_SUBJECT, "")
            questionIntent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(questionIntent, ""))
        }

        title_text.text = "고속도로 선택"
    }

    private fun initRecyclerView() {
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = LoadListAdapter(loadData)

        adapter.itemClickListener = object : LoadListAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: LoadListAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {

                val nextIntent = Intent(this@LoadPickActivity, RestPickActivity::class.java)
                nextIntent.putExtra("loadKey", data)
                startActivity(nextIntent)

            }
        }

        recyclerView2.adapter = adapter


    }



    private fun initData(){
        loadData.add("경부선")
        loadData.add("광주대구선")
        loadData.add("남해선")
        loadData.add("남해제2지선")
        loadData.add("논산천안선,호남선")
        loadData.add("당진영덕선")
        loadData.add("대구포항선")
        loadData.add("동해선")
        loadData.add("무안광주선")
        loadData.add("부산외곽순환선")
        loadData.add("상주영천선")
        loadData.add("서울양양선")
        loadData.add("서울외곽순환선")
        loadData.add("서천공주선")
        loadData.add("서해안선")
        loadData.add("순천완주선")
        loadData.add("영동선")
        loadData.add("울산포항선")
        loadData.add("익산장수선")
        loadData.add("인천국제공항선")
        loadData.add("중부내륙선")
        loadData.add("중부내륙의 지선")
        loadData.add("중부선")
        loadData.add("중앙선")
        loadData.add("통영대전선")
        loadData.add("통영대전선,중부선")
        loadData.add("평택시흥선")
        loadData.add("평택제천선")
        loadData.add("평택파주선")
        loadData.add("호남선")
        loadData.add("호남선의 지선")
    }

}
