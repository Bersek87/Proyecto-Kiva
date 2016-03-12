package com.torres.siscom.kivaonline;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Eden on 10/03/2016.
 */
    import android.app.Application;
    import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

    public class VolleySingleton extends Application {


            private static VolleySingleton mInstance = null;
            private RequestQueue mRequestQueue;
            private ImageLoader mImageLoader;
        private Context appContext;

        private VolleySingleton(){
                mRequestQueue = Volley.newRequestQueue(MainActivity.getAppContext());
                mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
                    public void putBitmap(String url, Bitmap bitmap) {
                        mCache.put(url, bitmap);
                    }
                    public Bitmap getBitmap(String url) {
                        return mCache.get(url);
                    }
                });
            }

            public static VolleySingleton getInstance(){
                if(mInstance == null){
                    mInstance = new VolleySingleton();
                }
                return mInstance;
            }

            public RequestQueue getRequestQueue(){
                return this.mRequestQueue;
            }

            public ImageLoader getImageLoader(){
                return this.mImageLoader;
            }

    }