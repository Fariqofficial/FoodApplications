/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.farr.foodapp.View.Home;

import com.farr.foodapp.Model.Categories;
import com.farr.foodapp.Model.Meals;

import java.util.List;

public interface HomeView {

    void showLoading();

    void hideLoading();

    void setMeal(List<Meals.Meal> meal);

    void setCategory(List<Categories.Category> category);

    void onErrorLodaing(String message);
}
