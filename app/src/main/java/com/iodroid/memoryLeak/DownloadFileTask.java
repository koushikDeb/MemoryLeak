package com.iodroid.memoryLeak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

class DownloadFileTask extends AsyncTask<Void, Void, Bitmap> {


    String TAG = "priya";
    private Context context;
    private DownloadStatus dnStatus;
    String imageurl;
    public DownloadFileTask(Context context, String url , DownloadStatus ds)
    {

        this.context=context;
        imageurl=url;
        dnStatus=ds;
    }


    protected void onProgressUpdate(Void v) {
        Log.d(TAG,"Progress ");

    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Log.d(TAG,"download start ");
        Bitmap bm=getBitmapFromURL(imageurl);
        return bm;
    }

    protected void onPostExecute(Bitmap v) {
        Log.d(TAG,"Downloaded "  + " bytes ...");
        if(v!=null) {
            dnStatus.DownLoadCompleted(v);
            Toast.makeText(context, "Download complete " + imageurl, Toast.LENGTH_SHORT).show();
        }
        else
        {
            dnStatus.DownLoadFailed();// Need reason for failure  
        }


    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.d(TAG,"download end now ");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
