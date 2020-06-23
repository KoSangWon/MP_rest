package com.example.restareareview

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restareareview.Adapter.ReviewListAdapter
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.ref.WeakReference
import java.net.URL

class ReviewActivity : AppCompatActivity() {
    lateinit var adapter: ReviewListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        setActionBar()
        init()
        startTask()
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

        val restName = intent.getStringExtra("restKey").toString()

        title_text.text = "${restName} 후기"
    }

    fun startTask(){
        val task = MyAsyncTask(this)
        val restName = intent.getStringExtra("restKey").toString()
        task.execute(URL("https://search.naver.com/search.naver?sm=tab_hty.top&where=post&query=${restName}메뉴&oquery=${restName}메뉴&tqi=UYut0dp0J14ssa2mbA4ssssst4o-222322"))
    }

    fun init() {
        recyclerView5.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView5.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        adapter = ReviewListAdapter(ArrayList<MyReviewData>())
        adapter.itemClickListener = object:ReviewListAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: ReviewListAdapter.MyViewHolder,
                view: View,
                data: MyReviewData,
                position: Int
            ) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(adapter.items[position].url))
                startActivity(intent)
            }
        }
        recyclerView5.adapter = adapter
    }

    class MyAsyncTask(context: ReviewActivity): AsyncTask<URL, Unit, Unit>(){
        val activityreference = WeakReference(context)

        override fun doInBackground(vararg params: URL?): Unit {
            val activity = activityreference.get()
            activity?.adapter?.items?.clear()

            val doc = Jsoup.connect(params[0].toString()).get()
            val titles = doc.select(".sh_blog_title._sp_each_url._sp_each_title")
            for(title in titles){
                activity?.adapter?.items?.add(MyReviewData(title.text(), title.absUrl("href")))
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            val activity = activityreference.get()
            if(activity==null || activity.isFinishing){
                return
            }
            activity.swiperefresh4.isRefreshing = false
            activity.adapter.notifyDataSetChanged()
        }
    }
}