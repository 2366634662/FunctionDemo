package utouu.functiondemo.framework.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import utouu.functiondemo.MyApplication;

/**
 * Created by Du_Li on 2016/10/27.
 * Desc:
 */
public class GlideHelper {
    private static GlideHelper mGlideHelper;

    private GlideHelper() {
    }

    public static GlideHelper getInstance() {
        if (mGlideHelper == null) {
            mGlideHelper = new GlideHelper();
        }
        return mGlideHelper;
    }

    public void displayImage(ImageView img, String imgUrl) {

        Glide.with(MyApplication.getAppContext())
                .load(imgUrl)
                .into(img);

    }


}
