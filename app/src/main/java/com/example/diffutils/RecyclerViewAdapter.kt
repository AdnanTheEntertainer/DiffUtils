package com.journaldev.androidrecyclerviewdiffutil

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutils.Model
import com.example.diffutils.MyDiffUtilCallBack
import com.example.diffutils.R
import kotlinx.android.synthetic.main.cardview_item_layout.view.*
import java.util.*

class RecyclerViewAdapter(private val data: ArrayList<Model>, val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.CryptoViewHolder>() {

    inner class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        public val mName: TextView
        public val mPrice: TextView

        init {
            mName = itemView.txtName
            mPrice = itemView.txtPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cardview_item_layout, parent, false)
        return CryptoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.mName.text = data[position].name
        holder.mPrice.setText(data[position].price.toString() + " USD")
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int, payloads: List<Any>) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                if (key == "price") {
                    holder.mName.text = data[position].name
                    holder.mPrice.setText(data[position].price.toString() + " USD")
                    holder.mPrice.setTextColor(Color.GREEN)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getData(): ArrayList<Model> {
        return data
    }

    fun setData(newData: ArrayList<Model>) {

        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallBack(newData, data))
        diffResult.dispatchUpdatesTo(this)
        data.clear()
        this.data.addAll(newData)
    }
}

