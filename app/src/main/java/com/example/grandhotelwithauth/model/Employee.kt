package com.example.grandhotelwithauth.model

data class Employee(
    val id: String,
    val username: String,
    val name: String,
    val email: String,
    val address: String,
    val dateOfBirth: String,
    val jobID: String,
    val cleaningRoom: String
)