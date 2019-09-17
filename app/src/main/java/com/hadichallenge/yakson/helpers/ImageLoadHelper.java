package com.hadichallenge.yakson.helpers;

import android.widget.ImageView;

import com.hadichallenge.yakson.R;
import com.hadichallenge.yakson.ui.activity.BaseActivity;
import com.squareup.picasso.Picasso;


public class ImageLoadHelper {

    private static ImageLoadHelper imageLoadHelper;

    public static ImageLoadHelper getInstance() {
        if (imageLoadHelper == null) {
            imageLoadHelper = new ImageLoadHelper();
        }
        return imageLoadHelper;
    }

    public void loadImage(ImageView imageView, String url) {

        if (url == null || url.isEmpty()) {
            url = "empty";
        }

        Picasso.with(BaseActivity.currentActivity)
                .load(url)
                .resize(500, 0)
                .onlyScaleDown()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(imageView);
    }
}