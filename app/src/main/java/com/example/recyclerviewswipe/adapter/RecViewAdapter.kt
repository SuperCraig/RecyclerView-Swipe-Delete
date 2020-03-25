package com.example.recyclerviewswipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewswipe.R
import com.example.recyclerviewswipe.databinding.RecViewRowLayoutBinding
import com.example.recyclerviewswipe.model.DataItem

class RecViewAdapter(private val mContext: Context, private val mDataItemList: ArrayList<DataItem>): RecyclerView.Adapter<RecViewAdapter.RecViewHolder>(){
    private var mBinding: RecViewRowLayoutBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.rec_view_row_layout, parent, false)
        return RecViewHolder(mBinding!!)
    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title).text = mDataItemList[position].title
        holder.itemView.findViewById<TextView>(R.id.sub_title).text = mDataItemList[position].subTitle
        holder.itemView.findViewById<RatingBar>(R.id.rating_bar).numStars = mDataItemList[position].rating
        holder.itemView.findViewById<TextView>(R.id.description).text = mDataItemList[position].description
        holder.itemView.findViewById<TextView>(R.id.date_text).text = mDataItemList[position].date
    }

    override fun getItemCount(): Int {
        return mDataItemList.size
    }

    fun removeItem(position: Int){
        mDataItemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: DataItem, position: Int){
        mDataItemList.add(position, item)
        notifyItemInserted(position)
    }

    inner  class RecViewHolder(mBinding: RecViewRowLayoutBinding): RecyclerView.ViewHolder(mBinding.root)
}