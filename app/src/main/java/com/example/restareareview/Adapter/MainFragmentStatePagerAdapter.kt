package com.example.restareareview.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.restareareview.Fragment.*


class MainFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return FirstFragment()
            1 -> return SecondFragment()
            2 -> return ThirdFragment()
            else -> return FirstFragment()
        }
    }

    override fun getCount(): Int = fragmentCount

}