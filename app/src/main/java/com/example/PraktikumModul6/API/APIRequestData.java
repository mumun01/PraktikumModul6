package com.example.PraktikumModul6.API;

import com.example.PraktikumModul6.Model.ResponModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponModel> ardCreateData(
            @Field("nama") String nama,
            @Field("jurusan") String jurusan,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponModel> arddeleteData(
      @Field("id") int id
    );
}
