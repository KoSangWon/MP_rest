package com.example.restareareview

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restareareview.Adapter.LoadListAdapter
import kotlinx.android.synthetic.main.activity_rest_pick.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.ref.WeakReference
import java.net.URL

class LoadPickActivity :  AppCompatActivity() {

    lateinit var adapter:LoadListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_pick)
        init()
        startJSONTask()
    }

    fun startJSONTask(){
        val task = MyAsyncTask(this)
        task.execute(URL("http://data.ex.co.kr/exopenapi/business/representFoodServiceArea?serviceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D&type=json&numOfRows=10&pageNo=20"))
    }

    fun init(){

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = true
            //startXMLTask()
//            startTask()
            startJSONTask()
        }

        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = LoadListAdapter(ArrayList<String>())
        adapter.itemClickListener = object:LoadListAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: LoadListAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {


                Toast.makeText(applicationContext, data, Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView2.adapter = adapter
    }

    class MyAsyncTask(context:LoadPickActivity):AsyncTask<URL, Unit, Unit>(){
        val activityreference = WeakReference(context)

        override fun doInBackground(vararg params: URL?): Unit {
            val activity = activityreference.get()
            activity?.adapter?.items?.clear()

            val doc = Jsoup.connect(params[0].toString()).ignoreContentType(true).get()
            val json = JSONObject(doc.text())
            val array = json.getJSONArray("list")
//            Log.i("JSON: ", array.toString())
            for(i in 0 until array.length()) {
                val name = array.getJSONObject(i).getString("routeName")
                activity?.adapter?.items?.add(name)
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            val activity = activityreference.get()
            if(activity==null || activity.isFinishing){
                return
            }
            activity.swiperefresh.isRefreshing = false
            activity.adapter.notifyDataSetChanged()
        }

    }

}
