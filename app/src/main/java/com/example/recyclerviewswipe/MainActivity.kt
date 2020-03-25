package com.example.recyclerviewswipe

import android.content.ClipData
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewswipe.adapter.RecViewAdapter
import com.example.recyclerviewswipe.databinding.ActivityMainBinding
import com.example.recyclerviewswipe.model.DataItem
import com.example.recyclerviewswipe.uiUtils.RecyclerItemTouchHelper
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private var mDataItem = ArrayList<DataItem>()
    private var mAdapter: RecViewAdapter? = null
    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mAdapter = RecViewAdapter(this, setDataItem(13))

        mBinding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        mBinding?.recyclerView?.adapter = mAdapter

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mBinding?.recyclerView)

        object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun setDataItem(Size: Int): ArrayList<DataItem>{
        for(i in 1..Size){
            val dataItem = DataItem()
            dataItem.id = i
            dataItem.title = "Title" + i
            dataItem.subTitle = "Sub Title" + i
            dataItem.description = "Android Kotlin RecyclerView Demos"
            dataItem.rating = 4
            if(i < 9)
                dataItem.date = "Date: 0" + i + "-03-2020"
            else
                dataItem.date = "Date: " + i + "-03-2020"
            mDataItem.add(dataItem)
        }
        return mDataItem
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if(viewHolder is RecViewAdapter.RecViewHolder){
            val name = mDataItem[viewHolder.adapterPosition].title

            val deleteItem = mDataItem[viewHolder.adapterPosition]
            val deleteIndex = viewHolder.adapterPosition

            mAdapter?.removeItem(viewHolder.adapterPosition)

            var snackbar = Snackbar.make(mBinding?.coordinatorLayout!!, name!! + "remove from cart!", Snackbar.LENGTH_LONG)
            snackbar.setAction("UNDO") {
                mAdapter?.restoreItem(deleteItem, deleteIndex)
            }
            snackbar.setActionTextColor(Color.YELLOW)
            snackbar.show()
        }
    }
}
