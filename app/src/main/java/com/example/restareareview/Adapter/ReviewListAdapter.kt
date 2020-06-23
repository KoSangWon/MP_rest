package com.example.restareareview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restareareview.MyReviewData
import com.example.restareareview.R
import kotlinx.android.synthetic.main.activity_review.view.*

class ReviewListAdapter(val items: ArrayList<MyReviewData>): RecyclerView.Adapter<ReviewListAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view: View, data:MyReviewData, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var reviewtitle: TextView = itemView.findViewById(R.id.reviewTextView)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row5, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.reviewtitle.text = items[position].reviewtitle
    }
}