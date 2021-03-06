package com.hzhu.networkrequestreport.widget.floatwindow

import android.app.Application
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hzhu.networkrequestreport.R
import com.hzhu.networkrequestreport.report.entity.NetworkQueue

class FullRequestAdapter(val app:Application,val list:ArrayList<NetworkQueue>?) : RecyclerView.Adapter<FullRequestAdapter.VH>() {
    private var itemClick: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(app.applicationContext).inflate(R.layout.item_url, null, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tv.text = list?.get(position)?.request?.url?:""
        holder.tv_time.text = list?.get(position)?.request?.startTime
        if (list?.get(position)?.response?.code!=200) {
            holder.tv.setTextColor(Color.parseColor("#ff0000"))
        }else{
            holder.tv.setTextColor(Color.parseColor("#666666"))
        }
        holder.itemView.setOnClickListener {
            itemClick?.invoke(position)
        }
    }

    override fun getItemCount()=list?.size?:0



    fun setOnItemClickListener(itemClick1: ((Int) -> Unit)){
        this.itemClick=itemClick1
    }
    class VH(val view:View) :RecyclerView.ViewHolder(view){
        val tv = view.findViewById<TextView>(R.id.tv_title)
        val tv_time = view.findViewById<TextView>(R.id.tv_time)

    }
}