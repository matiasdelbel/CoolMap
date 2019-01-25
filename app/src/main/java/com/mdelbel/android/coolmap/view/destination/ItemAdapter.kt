package com.mdelbel.android.coolmap.view.destination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mdelbel.android.coolmap.R

class ItemAdapter(private val dataSet: List<ItemViewModel> = listOf()) :

    ListAdapter<ItemViewModel, ItemAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: ItemViewModel) {
            view.findViewById<TextView>(R.id.item_info_code).text = item.code
            view.findViewById<TextView>(R.id.item_info_title).text = item.title
            view.findViewById<TextView>(R.id.item_info_subtitle).text = item.subtitle
            view.findViewById<TextView>(R.id.item_info_subtitle).visibility = when (item.subtitle.isEmpty()) {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
}

data class ItemViewModel(val code: String, val title: String, val subtitle: String = "")

class DiffCallback : DiffUtil.ItemCallback<ItemViewModel>() {

    override fun areItemsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel) = (oldItem == newItem)

    override fun areContentsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel) = (oldItem.code == newItem.code)
}