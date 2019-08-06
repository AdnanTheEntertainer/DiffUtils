package com.example.diffutils


import android.os.Bundle
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil

import java.util.ArrayList

class MyDiffUtilCallBack(internal var newList: ArrayList<Model>?, internal var oldList: ArrayList<Model>?) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return if (oldList != null) oldList!!.size else 0
    }

    override fun getNewListSize(): Int {
        return if (newList != null) newList!!.size else 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList!![newItemPosition].id == oldList!![oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val result = newList!![newItemPosition].compareTo(oldList!![oldItemPosition])
        return result == 0
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {

        val (_, _, price) = newList!![newItemPosition]
        val (_, _, price1) = oldList!![oldItemPosition]

        val diff = Bundle()

        if (price != price1) {
            diff.putInt("price", price)
        }
        return if (diff.size() == 0) {
            null
        } else diff
//return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}

