package com.dokage.gallery.gallery;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dokage.gallery.R;
import com.dokage.gallery.util.Constant;


public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GalleryFragment galleryFragment = (GalleryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_content);

        if(galleryFragment == null) {
            galleryFragment = new GalleryFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_content, galleryFragment);
            transaction.commit();
        }

        boolean firstLoad = true;
        if (savedInstanceState != null) {
            firstLoad = savedInstanceState.getBoolean(Constant.KEY_FIRST_LOAD, true);
        }
        new GalleryPresenter(galleryFragment, firstLoad);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(Constant.KEY_FIRST_LOAD, false);
    }

}