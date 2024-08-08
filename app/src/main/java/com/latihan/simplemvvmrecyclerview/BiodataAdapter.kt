package com.latihan.simplemvvmrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class BiodataAdapter(private var arrayListBiodata:  List<Biodata>): Adapter<BiodataAdapter.BiodataViewHolder>() {

    class BiodataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAge: TextView = itemView.findViewById(R.id.tvAge)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiodataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.biodata_item, parent, false)
        return BiodataViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrayListBiodata.size
    }

    override fun onBindViewHolder(holder: BiodataViewHolder, position: Int) {
        holder.tvName.text = arrayListBiodata.get(position).name
        holder.tvAge.text = arrayListBiodata.get(position).age.toString()
        holder.tvAddress.text = arrayListBiodata.get(position).address
    }

    fun updateItems(newItems: List<Biodata>) {
        arrayListBiodata = newItems
        notifyDataSetChanged()
    }
}