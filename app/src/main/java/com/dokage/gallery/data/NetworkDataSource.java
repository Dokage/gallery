package com.dokage.gallery.data;

import android.text.TextUtils;

import com.dokage.gallery.util.AppExecutors;
import com.dokage.gallery.util.Constant;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetworkDataSource implements DataSource {
    private List<String> mImageUrls = new ArrayList<>();

    private NetworkDataSource() {
    }

    private static class NetworkDataSourceHolder {
        static final NetworkDataSource INSTANCE = new NetworkDataSource();
    }

    public static NetworkDataSource getInstance() {
        return NetworkDataSourceHolder.INSTANCE;
    }

    @Override
    public void getData(final LoadDataCallback callback, boolean isUpdate) {
        if (!isUpdate) {
            if (mImageUrls.isEmpty()) {
                callback.onDataNotAvailable();
            } else {
                callback.onDataLoaded(mImageUrls);
            }
            return;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mImageUrls.clear();
                try {
                    Connection.Response response = Jsoup.connect(Constant.TARGET_SITE_URL)
                            .method(Connection.Method.GET)
                            .execute();
                    Document doc = response.parse();
                    Element body = doc.body();
                    Elements imageItems = body.getElementsByTag("img");

                    for (Element element : imageItems) {
                        String imageUrl = element.attr("data-src");
                        if (!TextUtils.isEmpty(imageUrl)) {
                            mImageUrls.add(imageUrl);
                        }
                    }
                } catch (IOException ignore) {
                }

                AppExecutors.getInstance().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (mImageUrls.isEmpty()) {
                            callback.onDataNotAvailable();
                        } else {
                            callback.onDataLoaded(mImageUrls);
                        }
                    }
                });
            }
        };
        AppExecutors.getInstance().getNetworkIO().execute(runnable);
    }

}
