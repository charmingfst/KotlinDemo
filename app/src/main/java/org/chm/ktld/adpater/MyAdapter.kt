package org.chm.ktld.adpater


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.chm.ktld.R

/**
 * Created by ason on 2017/6/26.
 */

class MyAdapter(private val context: Context, private val datas: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = View.inflate(context, R.layout.data_recycler, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(false) // itemView是否重用
        holder.item.text = datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.tv_item) as TextView

    }
}



