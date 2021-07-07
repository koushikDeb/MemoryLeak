package com.iodroid.memoryLeak;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private String imageurl ="https://wallpaperaccess.com/full/156340.jpg";
    private String image2Url="https://images.unsplash.com/photo-1523302348819-ffd5c0521796?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80";
    private ArrayList<ImageView>  imgArray=new ArrayList<ImageView>();
    private GetDemoAsync mgetDemoAsync=null;
    private String TAG="priya";
    ArrayList<Bitmap> bmList=new ArrayList<Bitmap>();
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imgArray.add(findViewById(R.id.imageView));
        imgArray.add(findViewById(R.id.imageView2));

    }
    public void downloadIt(View view) {
        mgetDemoAsync=GetDemoAsync.getInstance(getApplication());
        mgetDemoAsync.startDownload(imageurl,downloadStatus);
        mgetDemoAsync.startDownload(image2Url,downloadStatus);
    }

    DownloadStatus downloadStatus=new DownloadStatus() {
        @Override
        public void DownLoadCompleted(Bitmap bm) {
            bmList.add(bm);
            Log.d(TAG, "SetImage Count: "+count);
            setImage(count);
        }

        @Override
        public void DownLoadFailed() {

        }
    };

    //Download Should stop if activity is closed or backPressed

    protected void onPause()
    {
        super.onPause();
        if(mgetDemoAsync!=null) {
            Log.d(TAG, "download stopped of 2ndActivity");
            mgetDemoAsync.stopDownload();
        }
    }

    private void setImage(int i){
        imgArray.get(i).setImageBitmap(bmList.get(i));
        count++;
    }

}