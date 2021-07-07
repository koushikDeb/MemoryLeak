package com.iodroid.memoryLeak;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GetDemoAsync {

        private static GetDemoAsync ourInstance;
        private ArrayList<AsyncTask<Void,Void,Bitmap>> mbitmapAsyncTask =new ArrayList<>();

        private Context context;

        static GetDemoAsync getInstance(Application context) {
            if (ourInstance == null) {
                ourInstance = new GetDemoAsync(context);
            }
            return ourInstance;
        }


        private GetDemoAsync(Context context) {
            this.context = context;
        }



        void startDownload(String url,DownloadStatus d) {
           mbitmapAsyncTask.add(new DownloadFileTask(context,url,d).execute());
        }

        public void stopDownload()
        {
            if(!mbitmapAsyncTask.isEmpty()) {
                for(int i=0;i<mbitmapAsyncTask.size();i++) {
                    Log.d("priya", "download stopped");
                    mbitmapAsyncTask.get(i).cancel(true);
                }
                mbitmapAsyncTask.clear();
            }
        }

    }

