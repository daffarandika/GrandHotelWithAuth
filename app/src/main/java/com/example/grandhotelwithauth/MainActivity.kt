package com.example.grandhotelwithauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grandhotelwithauth.adapter.RoomTypeAdapter
import com.example.grandhotelwithauth.adapter.EmployeeAdapter
import com.example.grandhotelwithauth.databinding.ActivityMainBinding
import com.example.grandhotelwithauth.model.Employee
import com.example.grandhotelwithauth.model.RoomType
import com.example.grandhotelwithauth.utils.capitalizeWords
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), RecyclerViewEvent{
    private lateinit var binding: ActivityMainBinding
    val employees: MutableList<Employee> = mutableListOf()
    val types: MutableList<RoomType> = mutableListOf()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getEmployees()

        Log.e(TAG, "size of types: ${types.size}")
        val adapter = EmployeeAdapter(this@MainActivity, employees, this)
        // val adapter = RoomTypeAdapter(this@MainActivity, types)
        binding.rvEmployee.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    private fun getRoomTypes() = runBlocking{
        launch(Dispatchers.IO) {
            val conn = URL(CONSTS.url + "/api/roomtype").openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.doInput = true
            conn.setRequestProperty("token", CONSTS.token)
            conn.setRequestProperty("Content-type", "application/json")
            val inputString = conn.inputStream.bufferedReader().readText()
            val jsonArray = JSONArray(inputString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                types.add(RoomType(
                    jsonObject.getString("id"),
                    jsonObject.getString("name").capitalizeWords(),
                    jsonObject.getString("capacity"),
                    jsonObject.getString("roomPrice"),
                ))
            }
            if (conn.responseCode != 200) {
                Log.e(TAG, "getRoomTypes: gagal fetch", )
            }
        }
    }

    private fun getEmployees() = runBlocking {
        launch (Dispatchers.IO) {
            val conn = URL(CONSTS.url+"/api/Employee").openConnection() as HttpURLConnection
            conn.setRequestProperty("token", CONSTS.token)
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doInput = true
            val jsonString = conn.inputStream.bufferedReader().readText()
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                employees.add(Employee(
                    jsonObject.getString("id"),
                    jsonObject.getString("username"),
                    jsonObject.getString("name"),
                    jsonObject.getString("email"),
                    jsonObject.getString("address"),
                    jsonObject.getString("dateOfBirth"),
                    jsonObject.getString("jobId"),
                    jsonObject.getJSONArray("cleaningRooms").length().toString()
                ))
            }
            Log.d(TAG, "getEmployees: $employees")
        }
    }

    override fun onItemClick(position: Int) {
        Log.e(TAG, "onItemClick: yoyoyoyoyyy", )
    }
}