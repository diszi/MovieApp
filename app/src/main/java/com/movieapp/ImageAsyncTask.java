package com.movieapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;


import java.io.InputStream;

public class ImageAsyncTask  extends AsyncTask<String, Void, Bitmap> {


    ImageView imageView;

    public ImageAsyncTask(ImageView imageView){
        this.imageView=imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return mIcon11;

    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }


}
