package com.gini.cattest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gini.cattest.ActivityCat;
import com.gini.cattest.R;
import com.gini.cattest.SquareImageView;
import com.gini.cattest.model.CatResult;

import java.util.List;

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.GridItemViewHolder> {

    private List<CatResult> imageList;
    private ActivityCat c;

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        SquareImageView siv;

        public GridItemViewHolder(View view) {
            super(view);
            siv = view.findViewById(R.id.siv);
        }
    }

    public ImageGridAdapter(ActivityCat c, List<CatResult> imageList) {
        this.c = c;
        this.imageList = imageList;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);

        return new GridItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        final String path = imageList.get(position).getUrl();

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(c);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        Glide.with(c)
                .load(path)
                .apply(new RequestOptions().override(600, 500))
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .fallback(new ColorDrawable(Color.GRAY))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(holder.siv);

        holder.siv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.returnImage(((SquareImageView) v).getDrawable());
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

}
