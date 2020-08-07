package com.yaseminuctas.betbullcase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yaseminuctas.betbullcase.R
import com.yaseminuctas.betbullcase.data.network.Datum
import com.yaseminuctas.betbullcase.databinding.ItemRecyclerviewBinding

class DatumAdapter(
    var dataList: List<Datum>
) : RecyclerView.Adapter<DatumAdapter.DatumViewHolder>() {


    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DatumViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recyclerview,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DatumViewHolder, position: Int) {

        holder.itemRecyclerviewBinding.tvItem.text =
            (dataList[position].id.toString() + "-" + (dataList[position].name))

    }

    fun update(data:ArrayList<Datum>){
        dataList = data
        notifyDataSetChanged()
    }

    inner class DatumViewHolder(
        val itemRecyclerviewBinding: ItemRecyclerviewBinding
    ) : RecyclerView.ViewHolder(itemRecyclerviewBinding.root)

}
