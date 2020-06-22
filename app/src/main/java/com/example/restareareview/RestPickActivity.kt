package com.example.restareareview

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restareareview.Adapter.RestListAdapter
import kotlinx.android.synthetic.main.activity_rest_pick.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.ref.WeakReference
import java.net.URL

class RestPickActivity : AppCompatActivity() {
    lateinit var adapter: RestListAdapter
    var cnt: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_pick)
        setActionBar()
        init()
        startJSONTask()
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
            val questionIntent = Intent(this, MainActivity::class.java)
            startActivity(questionIntent)
        }

        val loadName = intent.getStringExtra("loadKey").toString()
        title_text.text = "${loadName} 휴게소선택"
    }


    fun startJSONTask(){
        val task = MyAsyncTask(this)
        val loadName = intent.getStringExtra("loadKey").toString()
        task.execute(
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=1&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=2&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=3&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=4&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=5&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=6&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=7&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=8&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=9&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=10&routeNm=${loadName}&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D")
        )
    }

    fun init(){
        swiperefresh2.setOnRefreshListener {
            swiperefresh2.isRefreshing = true
            startJSONTask()
        }

        recyclerView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = RestListAdapter(ArrayList<String>())
        adapter.itemClickListener = object: RestListAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: RestListAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                val nextIntent = Intent(this@RestPickActivity, MenuActivity::class.java)
                nextIntent.putExtra("restKey", data)
                val loadName = intent.getStringExtra("loadKey").toString()
                nextIntent.putExtra("loadKey", loadName)

                startActivity(nextIntent)


                Toast.makeText(applicationContext, data, Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, loadName, Toast.LENGTH_SHORT).show()

            }
        }
        recyclerView3.adapter = adapter
    }

    class MyAsyncTask(context:RestPickActivity): AsyncTask<URL, Unit, Unit>(){
        private val activityreference = WeakReference(context)

        override fun doInBackground(vararg params: URL?): Unit {
            val activity = activityreference.get()
            activity?.adapter?.items?.clear()

            val doc = Jsoup.connect(params[0].toString()).ignoreContentType(true).get()
            val json = JSONObject(doc.text())
            val array = json.getJSONArray("list")
            activity?.cnt = json.getInt("pageSize")
            val count = activity?.cnt

            Log.i("JSON: ", count.toString())
            for(i in 0 until array.length()) {
                val name = array.getJSONObject(i).getString("stdRestNm")
                if(!activity?.adapter?.items?.contains(name)!!) {
                    activity?.adapter?.items?.add(name)
                }
            }
            for(i in 1 until count!!){
                var doc2 = Jsoup.connect(params[i].toString()).ignoreContentType(true).get()
                var json2 = JSONObject(doc2.text())
                var array2 = json2.getJSONArray("list")

                for(i in 0 until array2.length()) {
                    val name2 = array2.getJSONObject(i).getString("stdRestNm")
                    if(!activity?.adapter?.items?.contains(name2)!!) {
                        activity?.adapter?.items?.add(name2)
                    }
                }
            }
            activity?.adapter?.items?.sort()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            val activity = activityreference.get()
            if(activity==null || activity.isFinishing){
                return
            }
            activity.swiperefresh2.isRefreshing = false
            activity.adapter.notifyDataSetChanged()
        }

    }
}