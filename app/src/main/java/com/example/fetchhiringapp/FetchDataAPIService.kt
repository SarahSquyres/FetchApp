package com.example.fetchhiringapp

import retrofit2.http.GET

interface FetchDataAPIService {
    @GET("/hiring.json")
    suspend fun getItems(): List<Item>
}