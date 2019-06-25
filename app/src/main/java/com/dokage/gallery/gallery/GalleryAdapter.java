package com.dokage.gallery.gallery;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dokage.gallery.detail.DetailActivity;
import com.dokage.gallery.R;
import com.dokage.gallery.util.Constant;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Fragment mFragment;
    private List<String> mImageUrls;

    GalleryAdapter(Fragment fragment, List<String> imageUrls) {
        mFragment = fragment;
        mImageUrls = imageUrls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.image_item, viewGroup, false);
        return new ViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ImageView imageView = viewHolder.mImageView;

        if (i < mImageUrls.size()) {
            String url = mImageUrls.get(i);
            Glide.with(mFragment)
                    .load(url)
                    .thumbnail(0.25f)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public void setImageUrls(List<String> imageUrls) {
        mImageUrls = imageUrls;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_image_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String url = mImageUrls.get(position);
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra(Constant.KEY_URL, url);
            v.getContext().startActivity(intent);
        }
    }
}
