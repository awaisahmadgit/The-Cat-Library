package com.gini.cattest;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gini.cattest.adapter.ImageGridAdapter;
import com.gini.cattest.databinding.ActivityCatBinding;
import com.gini.cattest.model.CatResult;
import com.gini.cattest.retrofit.api.Response;
import com.gini.cattest.utils.Constants;
import com.gini.cattest.viewmodel.CatViewModel;

import java.util.List;

public class ActivityCat extends AppCompatActivity {
    ActivityCatBinding binding;
    CatViewModel viewModel;
    static boolean active = false;

    private String title;
    private int color;
    private boolean showBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cat);

        viewModel = new ViewModelProvider(this).get(CatViewModel.class);

        title = CatManager.getInstance(CatApplication.context).title;
        color = CatManager.getInstance(CatApplication.context).bgColor;
        showBackBtn = CatManager.getInstance(CatApplication.context).showBackBtn;

        initView();
        fetchDataFromServer();
    }

    private void initView() {
        // Setting Action Bar TITLE, COLOR, BACK BUTTON
        if (showBackBtn) {
            binding.actionBar.setNavigationIcon(Constants.DRAWABLE_BACK_BTN);
        }
        binding.actionBarTitle.setText(title);
        binding.actionBar.setBackgroundColor(color);

        setSupportActionBar(binding.actionBar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(Boolean.FALSE);
    }

    private void fetchDataFromServer() {

        viewModel.getCatData(new Response.ListenerSize<List<CatResult>, Integer>() {
            @Override
            public void onResponse(List<CatResult> response, Integer paginationCount) {
                initiliazeRecyclerView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(Throwable error) {
                error.printStackTrace();
            }
        }, 0);

    }

    private void initiliazeRecyclerView(List<CatResult> catImagesList) {
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rv.setLayoutManager(sglm);

        ImageGridAdapter iga = new ImageGridAdapter(ActivityCat.this, catImagesList);
        binding.rv.setAdapter(iga);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CatManager.getInstance(getApplicationContext()).getmListener().onBackPress();
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void returnImage(Drawable drawable) {
        finish();
        CatManager.getInstance(getApplicationContext()).getmListener().onImageClick(drawable);
    }
}
