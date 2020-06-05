package com.example.restareareview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.example.restareareview.Adapter.MainFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        configureBottomNavigation()
    }

    private fun init() {

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
