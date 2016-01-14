package com.yanxin.library.library;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanXin on 2016/1/14.
 */
public class BlurHandler {

    private static BlurHandler sInstance;

    private List<BlurCompleteListener> mBlurCompleteListeners;
    private Bitmap mImage;

    public static synchronized BlurHandler getInstance() {
        if (sInstance == null) {
            sInstance = new BlurHandler();
            sInstance.mBlurCompleteListeners = new ArrayList<>();
        }
        return sInstance;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void beginBlur(View blurView) {
        new BlurTask(blurView).execute();
    }

    public void addBlurCompleteListener(BlurCompleteListener listener) {
        mBlurCompleteListeners.add(listener);
    }

    public void removeBlurCompleteListener(BlurCompleteListener listener) {
        mBlurCompleteListeners.remove(listener);
    }

    private class BlurTask extends AsyncTask<Void, Void, Void> {

        private View mBlurView;

        public BlurTask(View blurView) {
            mBlurView = blurView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBlurView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
            mBlurView.setDrawingCacheEnabled(true);
            mBlurView.buildDrawingCache();
            mImage = mBlurView.getDrawingCache();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mImage = BlurUtils.fastBlur(mImage, 0.1f, 10);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mBlurView.destroyDrawingCache();
            mBlurView.setDrawingCacheEnabled(false);
            for (BlurCompleteListener listener : mBlurCompleteListeners) {
                listener.onComplete();
            }
        }
    }

}
