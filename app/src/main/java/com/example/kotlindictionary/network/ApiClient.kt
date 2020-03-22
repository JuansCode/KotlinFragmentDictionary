package com.example.kotlindictionary.network


import com.example.kotlindictionary.model.UrbanResponse
import io.reactivex.Single

class ApiClient {

    //Create implementation of Interface
    private val apiService : RestService =
        ApiEndPoint.retrofitInstance.create(RestService::class.java)

    fun getDefinitionList(term : String) : Single<UrbanResponse> =
        apiService.getDefinitions(term)

}