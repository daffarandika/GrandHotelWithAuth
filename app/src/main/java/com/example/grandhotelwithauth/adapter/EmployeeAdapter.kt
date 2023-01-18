package com.example.grandhotelwithauth.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grandhotelwithauth.CONSTS
import com.example.grandhotelwithauth.R
import com.example.grandhotelwithauth.RecyclerViewEvent
import com.example.grandhotelwithauth.model.Employee

class EmployeeAdapter(
    val context: Context,
    val employees: MutableList<Employee>,
    val event: RecyclerViewEvent
): RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {
    private val TAG = "EmployeeAdapter"
    inner class EmployeeViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                event.onItemClick(position)
            }
        }
        val tvUsername = view.findViewById<TextView>(R.id.tvEmployeeUsername)
        val tvName = view.findViewById<TextView>(R.id.tvEmployeeName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmployeeEmail)
        val tvAddress = view.findViewById<TextView>(R.id.tvEmployeeAddress)
        val tvDOB = view.findViewById<TextView>(R.id.tvEmployeeDOB)
        val tvJobID = view.findViewById<TextView>(R.id.tvEmployeeJobID)
        val tvCleaningRoom = view.findViewById<TextView>(R.id.tvEmployeeCleaningRoom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.employee_row_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        val resource = context.resources
        holder.tvUsername.text = resource.getString(R.string.username_1_s, employee.username)
        holder.tvName.text = employee.name
        holder.tvEmail.text = resource.getString(R.string.email_1_s, employee.email)
        holder.tvAddress.text = resource.getString(R.string.address_1_s, employee.address)
        holder.tvDOB.text = resource.getString(R.string.date_of_birth_1_s, employee.dateOfBirth)
        holder.tvJobID.text = resource.getString(R.string.job_1_s, employee.jobID)
        holder.tvCleaningRoom.text = resource.getString(R.string.cleaning_room_1_s, employee.cleaningRoom)
        Log.e(TAG, "onBindViewHolder: ${employee.jobID}", )
        CONSTS.temp = "woi"
    }

    override fun getItemCount(): Int = employees.size
}