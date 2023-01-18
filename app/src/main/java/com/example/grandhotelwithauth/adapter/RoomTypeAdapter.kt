package com.example.grandhotelwithauth.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grandhotelwithauth.R
import com.example.grandhotelwithauth.RecyclerViewEvent
import com.example.grandhotelwithauth.model.RoomType

class RoomTypeAdapter(
    val context: Context,
    val roomtypes: MutableList<RoomType>
): RecyclerView.Adapter<RoomTypeAdapter.RoomTypeViewHolder>() {
    private val TAG = "RoomTypeAdapter"
    inner class RoomTypeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tvTypeName)
        val tvCapacity = view.findViewById<TextView>(R.id.tvTypeCapacity)
        val tvPrice = view.findViewById<TextView>(R.id.tvTypePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.roomtype_row_item, parent, false)
        Log.e(TAG, "onCreateViewHolder: this is oncreatelalalalaal", )
        return RoomTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        val resource = context.resources
        holder.tvName.text = roomtypes[position].name
        holder.tvCapacity.text = resource.getString(R.string.capacity_1_s, roomtypes[position].capacity)
        holder.tvPrice.text = resource.getString(R.string.price_1_s, roomtypes[position].roomPrice)
    }

    override fun getItemCount(): Int = roomtypes.size
}