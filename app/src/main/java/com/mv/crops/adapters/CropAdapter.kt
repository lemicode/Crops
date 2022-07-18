package com.mv.crops.adapters

import Models.Crop
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mv.crops.R

interface Callbacks {
    fun onButtonClicked(titleKey: String?)
}

class CropAdapter(private val cropList: List<Crop>): RecyclerView.Adapter<CropAdapter.CropViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class CropViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val cropName: TextView = itemView.findViewById(R.id.crop_item_title)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crop_item, parent, false)
        return CropViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: CropViewHolder, position: Int) {
        val crop = cropList[position]
        holder.cropName.text = crop.nombre
    }

    override fun getItemCount(): Int = cropList.size

}