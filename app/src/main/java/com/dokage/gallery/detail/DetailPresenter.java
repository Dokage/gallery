package com.dokage.gallery.detail;

public class DetailPresenter implements DetailContract.Presenter {
    private String mImageUrl;
    private DetailContract.View mDetailView;

    public DetailPresenter(DetailContract.View detailView, String imageUrl){
        mImageUrl = imageUrl;
        mDetailView = detailView;
        mDetailView.setPresenter(this);
    }

    @Override
    public void loadImage() {
        mDetailView.showImage(mImageUrl);
    }

    @Override
    public void start() {
        loadImage();
    }
}
