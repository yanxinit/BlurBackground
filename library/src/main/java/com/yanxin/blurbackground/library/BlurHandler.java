package com.yanxin.blurbackground.library;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.view.View;

/**
 * Created by YanXin on 2016/1/14.
 */
public class BlurHandler {

    private static final String KEY_IMAGE_CACHE = "key_image_cache";
    private static final float DEFAULT_SCALE = 0.1F;
    private static final int DEFAULT_RADIUS = 10;

    private static BlurHandler sInstance;

    private View mBackgroundView;

    private LruCache<String, Bitmap> mBitmapLruCache = new LruCache<>(1);

    public static synchronized BlurHandler getInstance() {
        if (sInstance == null) {
            sInstance = new BlurHandler();
        }
        return sInstance;
    }

    public Bitmap getImage() {
        return mBitmapLruCache.get(KEY_IMAGE_CACHE);
    }

    public void beginBlur(Object object) {
        View blurView;
        if (object instanceof Activity) {
            blurView = ((Activity) object).getWindow().getDecorView();
        } else if (object instanceof Fragment) {
            blurView = ((Fragment) object).getView();
        } else if (object instanceof android.app.Fragment) {
            blurView = ((android.app.Fragment) object).getView();
        } else if (object instanceof View) {
            blurView = (View) object;
        } else {
            throw new IllegalStateException("Not support blur" + object.getClass().getSimpleName());
        }
        new BlurTask(blurView).execute();
    }

    public void blurBackground(Object object) {
        if (object instanceof Activity) {
            mBackgroundView = ((Activity) object).getWindow().getDecorView().getRootView();
        } else if (object instanceof Fragment) {
            mBackgroundView = ((Fragment) object).getView();
        } else if (object instanceof android.app.Fragment) {
            mBackgroundView = ((android.app.Fragment) object).getView();
        } else if (object instanceof View) {
            mBackgroundView = (View) object;
        } else {
            throw new IllegalStateException("Not support blur" + object.getClass().getSimpleName());
        }

        if (mBitmapLruCache.get(KEY_IMAGE_CACHE) != null) {
            mBackgroundView.setBackgroundDrawable(new BitmapDrawable(getImage()));
            mBackgroundView = null;
        }
    }

    private class BlurTask extends AsyncTask<Void, Void, Void> {

        private View mBlurView;
        private Bitmap mBitmap;

        public BlurTask(View blurView) {
            mBlurView = blurView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBitmapLruCache.remove(KEY_IMAGE_CACHE);

            mBlurView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
            mBlurView.setDrawingCacheEnabled(true);
            mBlurView.buildDrawingCache();
            mBitmap = mBlurView.getDrawingCache();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mBitmapLruCache.put(KEY_IMAGE_CACHE, BlurUtils.fastBlur(mBitmap, DEFAULT_SCALE, DEFAULT_RADIUS));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mBlurView.destroyDrawingCache();
            mBlurView.setDrawingCacheEnabled(false);
            if (mBackgroundView != null) {
                mBackgroundView.setBackgroundDrawable(new BitmapDrawable(getImage()));
                mBackgroundView = null;
            }
        }
    }

}
