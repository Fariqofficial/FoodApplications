package com.farr.foodapp.View.Details;

import com.farr.foodapp.Model.Meals;

public interface DetailsView {

    void showLoading();

    void hideLoading();

    void setMeal(Meals.Meal meal);

    void onErrorLoading(String message);
}
