package com.example.restareareview.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.restareareview.EachLoadMapListActivity
import com.example.restareareview.R
import com.example.restareareview.RestPickActivity

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:ViewGroup = inflater.inflate(R.layout.fragment_second, container, false) as ViewGroup

        val eachMapBtn: Button = rootView.findViewById(R.id.each_map_btn)

        eachMapBtn.setOnClickListener{
            activity?.let {
                var intent = Intent(context, EachLoadMapListActivity::class.java)
                startActivity(intent)
            }
        }
        return rootView
    }
}