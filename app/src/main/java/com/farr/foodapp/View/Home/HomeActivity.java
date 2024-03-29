package com.farr.foodapp.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.farr.foodapp.Adapter.RecyclerViewHomeAdapter;
import com.farr.foodapp.Adapter.ViewPagerHeaderAdapter;
import com.farr.foodapp.Model.Categories;
import com.farr.foodapp.Model.Meals;
import com.farr.foodapp.R;
import com.farr.foodapp.Utils;
import com.farr.foodapp.View.Category.CategoryActivity;
import com.farr.foodapp.View.Details.DetailsActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    //*******//
    @BindView(R.id.viewpager)
    ViewPager viewPagerMeal;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerViewCategory;

    //******//
    private Toast previousToast;
    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();

    }// end of onCreate

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((view, position) -> {
            //showMessage(meal.get(position).getStrMeal());

            TextView mealName = view.findViewById(R.id.mealName);
            // make intent of meal name from edit text view and send it to Details Activity
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
            startActivity(intent);

        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {

        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            //showMessage(category.get(position).getStrCategory());
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLodaing(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

    // method for display Toast message
    public void showMessage(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
        //Hide the previous toast
        if (previousToast != null)
            previousToast.cancel();
        previousToast = toast;
    }
}
