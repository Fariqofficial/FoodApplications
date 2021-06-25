package com.farr.foodapp.View.Category;

import com.farr.foodapp.Model.Meals;

import java.util.List;

public interface CategoryView {
    void showLoading();

    void hideLoading();

    void setMeals(List<Meals.Meal> meals);

    void onErrorLoading(String message);
}
