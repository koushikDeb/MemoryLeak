package com.iodroid.memoryLeak;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String imageurl ="https://www.rd.com/wp-content/uploads/2021/03/GettyImages-1133605325-scaled-e1617227898456.jpg";
    private ImageView imageView;
    private String TAG="priya";
    private GetDemoAsync mgetDemoAsync=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
    }

    public void downloadIt(View view) {
        mgetDemoAsync=GetDemoAsync.getInstance(getApplication());
        mgetDemoAsync.startDownload(imageurl,downloadStatus);

    }

    DownloadStatus downloadStatus=new DownloadStatus() {
        @Override
        public void DownLoadCompleted(Bitmap bm) {
            imageView.setImageBitmap(bm);
        }

        @Override
        public void DownLoadFailed() {

        }
    };

    public void nextPage(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }

    //Download Should stop if activity is closed or backPressed

    protected void onResume()
    {
        super.onResume();
        if(mgetDemoAsync!=null) {
            Log.d(TAG, "download stopped of MainActivity");
            mgetDemoAsync.stopDownload();
        }
    }

}