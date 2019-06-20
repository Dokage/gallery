package com.dokage.gallery.data;

import java.util.List;

public interface DataSource {

    interface LoadDataCallback {
        void onDataLoaded(List<String> urls);
        void onDataNotAvailable();
    }

    void getData(LoadDataCallback callback, boolean isUpdate);
}
