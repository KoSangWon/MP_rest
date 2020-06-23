package com.example.restareareview.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.restareareview.R
import com.example.restareareview.LoadPickActivity
import com.example.restareareview.MapActivity
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView:ViewGroup = inflater.inflate(R.layout.fragment_first, container, false) as ViewGroup

        val pickbtn:Button = rootView.findViewById(R.id.restpickbtn)
        val mapbtn:Button = rootView.findViewById(R.id.restmapbtn)

        pickbtn.setOnClickListener{
            activity?.let {
                var intent = Intent(context, LoadPickActivity::class.java)
                startActivity(intent)
            }
        }

        mapbtn.setOnClickListener{
            activity?.let {
                var intent = Intent(context, MapActivity::class.java)
                startActivity(intent)
            }
        }

        return rootView
    }


}