package com.example.restareareview.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.restareareview.EachLoadMap.KoreaActivity
import com.example.restareareview.EachLoadMapListActivity
import com.example.restareareview.R

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:ViewGroup = inflater.inflate(R.layout.fragment_second, container, false) as ViewGroup

        val koreaMapBtn: Button = rootView.findViewById(R.id.korea_map_btn)
        val eachMapBtn: Button = rootView.findViewById(R.id.each_map_btn)

        koreaMapBtn.setOnClickListener{
            activity?.let {
                var intent = Intent(context, KoreaActivity::class.java)
                startActivity(intent)
            }
        }

        eachMapBtn.setOnClickListener{
            activity?.let {
                var intent = Intent(context, EachLoadMapListActivity::class.java)
                startActivity(intent)
            }
        }
        return rootView
    }
}