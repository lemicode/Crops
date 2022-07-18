package com.mv.crops.adapters

import Models.Crop
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mv.crops.R

class CropAdapter(private val cropList: List<Crop>): RecyclerView.Adapter<CropAdapter.CropViewHolder>() {

    class CropViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cropName: TextView = itemView.findViewById(R.id.crop_item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crop_item, parent, false)
        return CropViewHolder(view)
    }

    override fun onBindViewHolder(holder: CropViewHolder, position: Int) {
        val crop = cropList[position]
        holder.cropName.text = crop.nombre
    }

    override fun getItemCount(): Int {
        return cropList.size
    }

}