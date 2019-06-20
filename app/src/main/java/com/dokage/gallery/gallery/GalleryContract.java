package com.dokage.gallery.gallery;

import com.dokage.gallery.BasePresenter;
import com.dokage.gallery.BaseView;

import java.util.List;

public interface GalleryContract {

    interface View extends BaseView<Presenter>{
        void showImage(List<String> urls);
        void showNoImage();
        void showProgressCircle();
    }

    interface Presenter extends BasePresenter{
        void loadImage(boolean isUpdate);
    }
}
