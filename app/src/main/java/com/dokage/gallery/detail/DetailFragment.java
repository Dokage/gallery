package com.dokage.gallery.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dokage.gallery.R;

public class DetailFragment extends Fragment implements DetailContract.View {
    private DetailContract.Presenter mPresenter;
    private ImageView mImageView;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_detail, container, false);
        mImageView = root.findViewById(R.id.iv_image_detail);
        mPresenter.start();
        return root;
    }

    @Override
    public void showImage(String url) {
        Glide.with(this)
                .load(url)
                .thumbnail(0.25f)
                .placeholder(R.drawable.ic_launcher_background)
                .into(mImageView);
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
