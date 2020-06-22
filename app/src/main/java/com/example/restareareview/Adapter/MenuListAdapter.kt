package com.example.restareareview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restareareview.R

class MenuListAdapter(val items:ArrayList<String>): RecyclerView.Adapter<MenuListAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder:MyViewHolder, view: View, data:String, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView: TextView = itemView.findViewById(R.id.menuTextView)
        var textView2: TextView = itemView.findViewById(R.id.menuTextView)
        init{
            itemView.setOnClickListener{
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row4, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = items[position]
        holder.textView2.text = items[position]
    }

}