package com.dokage.gallery.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.dokage.gallery.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements GalleryContract.View{
    private GalleryContract.Presenter mPresenter;
    private GalleryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayout mNoImageView;
    private Button mRetryButton;
    private ProgressBar mProgressCircle;
    private RecyclerView.LayoutManager mLayoutManager;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAdapter = new GalleryAdapter(this, new ArrayList<String>(0));
        mLayoutManager = new GridLayoutManager(getContext(), 3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        mRecyclerView = root.findViewById(R.id.rv_images);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mNoImageView = root.findViewById(R.id.ll_no_image);
        mRetryButton = root.findViewById(R.id.bnt_retry);
        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadImage(true);
            }
        });

        mProgressCircle = root.findViewById(R.id.pb_circle);
        mRecyclerView.setVisibility(View.GONE);
        mNoImageView.setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showImage(List<String> urls) {
        mProgressCircle.setVisibility(View.GONE);
        mNoImageView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

        mAdapter.mImageUrls = urls;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoImage(){
        mProgressCircle.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mNoImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressCircle(){
        mRecyclerView.setVisibility(View.GONE);
        mNoImageView.setVisibility(View.GONE);
        mProgressCircle.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(GalleryContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
