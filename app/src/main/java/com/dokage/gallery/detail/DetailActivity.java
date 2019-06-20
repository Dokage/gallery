package com.dokage.gallery.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dokage.gallery.R;
import com.dokage.gallery.util.Constant;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String imageUrl = getIntent().getExtras().getString(Constant.KEY_URL);

        DetailFragment detailFragment = new DetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_detail, detailFragment);
        transaction.commit();

        new DetailPresenter(detailFragment, imageUrl);

    }
}
