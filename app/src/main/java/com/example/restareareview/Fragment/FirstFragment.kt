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

        pickbtn.setOnClickListener{
            activity?.let {
                var intent = Intent(context, LoadPickActivity::class.java)
                startActivity(intent)
            }
        }
        return rootView
    }


}