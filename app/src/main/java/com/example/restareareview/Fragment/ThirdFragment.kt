package com.example.restareareview.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.restareareview.EachLoadMapListActivity
import com.example.restareareview.MyMenuActivity
import com.example.restareareview.R

/**
 * A simple [Fragment] subclass.
 */
class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:ViewGroup = inflater.inflate(R.layout.fragment_third, container, false) as ViewGroup

        val dbBtn: Button = rootView.findViewById(R.id.dbbtn)

        dbBtn.setOnClickListener{
            activity?.let {
                var intent = Intent(context, MyMenuActivity::class.java)
                startActivity(intent)
            }
        }
        return rootView
    }

}