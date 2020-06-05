package com.example.restareareview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBar
import com.example.restareareview.Adapter.MainFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        init()
        configureBottomNavigation()
    }

    private fun init() {

    }
//    private fun makeToolBar(){
//        toolbar=findViewById(R.id.myToolBar)
//        setSupportActionBar(toolbar)
//    }
    private fun setToolBar(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)

        val view = supportActionBar!!.customView
        val imageButton1 = view.findViewById<ImageButton>(R.id.back)
        imageButton1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val imageButton2 = view.findViewById<ImageButton>(R.id.mail)
        imageButton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun configureBottomNavigation(){
        main_frag_paper.adapter = MainFragmentStatePagerAdapter(supportFragmentManager, 3)
        main_bottom_menu.setupWithViewPager(main_frag_paper)
        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.bottom_navigation_tab, null, false)
        main_bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_home_tab) as RelativeLayout
        main_bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_map_tab) as RelativeLayout
        main_bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_store_tab) as RelativeLayout

    }
}
