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
import com.example.restareareview.Adapter.LoadListAdapter
import kotlinx.android.synthetic.main.activity_load_pick.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.ref.WeakReference
import java.net.URL

class LoadPickActivity :  AppCompatActivity() {

    lateinit var adapter:LoadListAdapter
    var cnt: Int? = null
//    var urlList: MutableList<URL>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_pick)
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

        title_text.text = "고속도로 선택"
    }

    fun startJSONTask(){
        val task = MyAsyncTask(this)
        task.execute(
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=1&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=2&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=3&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=4&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=5&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=6&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=7&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=8&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=9&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=10&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=11&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=12&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=13&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=14&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=15&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=16&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=17&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=18&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=19&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=20&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=21&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=22&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=23&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=24&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=25&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=26&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=27&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=28&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=29&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=30&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=31&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=32&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=33&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=34&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=35&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=36&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=37&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=38&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=39&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=40&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=41&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=42&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=43&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=44&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=45&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=46&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=47&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=48&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=49&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=50&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=51&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=52&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=53&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=54&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=55&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=56&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=57&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=58&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=59&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=60&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=61&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=62&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=63&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=64&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=65&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=66&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=67&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=68&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D"),
            URL("http://data.ex.co.kr/exopenapi/restinfo/restBestfoodList?type=json&numOfRows=50&pageNo=69&ServiceKey=t7ZzTcFLxSYmxE8AuoZRJ21QVdx0VIlomBqM4gAOvz2J2sVinQ5R5Eb0lC4ShSfOQ2VdWMBhhmgYPJ0kSwk9jg%3D%3D")
        )
    }

    fun init(){

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = true
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
                val nextIntent = Intent(this@LoadPickActivity, RestPickActivity::class.java)
                nextIntent.putExtra("loadKey", data)
                startActivity(nextIntent)


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
            activity?.cnt = json.getInt("pageSize")
            val count = activity?.cnt

            Log.i("JSON: ", count.toString())
            for(i in 0 until array.length()) {
                val name = array.getJSONObject(i).getString("routeNm")
                if(!activity?.adapter?.items?.contains(name)!!) {
                    activity?.adapter?.items?.add(name)
                }
            }
            for(i in 1 until count!!){
                var doc2 = Jsoup.connect(params[i].toString()).ignoreContentType(true).get()
                var json2 = JSONObject(doc2.text())
                var array2 = json2.getJSONArray("list")

                for(i in 0 until array2.length()) {
                    val name2 = array2.getJSONObject(i).getString("routeNm")
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
            activity.swiperefresh.isRefreshing = false
            activity.adapter.notifyDataSetChanged()
        }

    }

}
