package com.diego.test.openWeatherAPI

import Models.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService
{

    @GET("data/2.5/weather")
    fun getCurrentWeatherByCity(@Query("q") city: String,
                                @Query("units") units: String,
                                @Query("lang") lang: String,
                                @Query("APPID") appid: String)
            : Call<CurrentWeatherResponse>;

    @GET("data/2.5/weather")
    fun getCurrentWeatherByLocation(@Query("lat") lat: Double,
                                    @Query("lon") lon: Double,
                                    @Query("units") units: String,
                                    @Query("lang") lang: String,
                                    @Query("APPID") appid: String)
            : Call<CurrentWeatherResponse>;

}