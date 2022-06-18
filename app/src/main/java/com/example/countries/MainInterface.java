package com.example.countries;

import retrofit2.Call;
import retrofit2.http.GET;

interface MainInterface {
    @GET("v3.1/all")
    Call<String> STRING_CALL();
}
