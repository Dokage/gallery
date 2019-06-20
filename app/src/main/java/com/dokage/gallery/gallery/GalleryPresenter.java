package com.dokage.gallery.gallery;

import com.dokage.gallery.data.DataSource;
import com.dokage.gallery.data.NetworkDataSource;

import java.util.List;

public class GalleryPresenter implements GalleryContract.Presenter {
    private boolean mFirstLoad;
    private GalleryContract.View mGalleryView;

    public GalleryPresenter(GalleryContract.View galleryView, boolean firstLoad){
        mFirstLoad = firstLoad;
        mGalleryView = galleryView;
        galleryView.setPresenter(this);
    }

    @Override
    public void start(){
        loadImage(mFirstLoad);
        mFirstLoad = false;
    }

    @Override
    public void loadImage(boolean forceUpdate) {
        mGalleryView.showProgressCircle();
        NetworkDataSource.getInstance().getData(new DataSource.LoadDataCallback() {
            @Override
            public void onDataLoaded(List<String> urls) {
                mGalleryView.showImage(urls);
            }
            @Override
            public void onDataNotAvailable() {
                mGalleryView.showNoImage();
            }
            }, forceUpdate);
    }
}
