/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.farr.foodapp.Api;

import com.farr.foodapp.Model.Categories;
import com.farr.foodapp.Model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("filter.php?a=Canadian") // search.php?f=a
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

    // add interface for get meal bu category
    @GET("filter.php?")
    Call<Meals> getMealsByCategory(@Query("c") String category); // c = seafood

    // get meal by name
    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName); // s = Arrabiata


}
