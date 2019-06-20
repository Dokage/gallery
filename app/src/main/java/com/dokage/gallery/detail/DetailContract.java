package com.dokage.gallery.detail;

import com.dokage.gallery.BasePresenter;
import com.dokage.gallery.BaseView;

public interface DetailContract {
    interface View extends BaseView<Presenter>{
        void showImage(String url);
    }

    interface Presenter extends BasePresenter{
        void loadImage();
    }
}
