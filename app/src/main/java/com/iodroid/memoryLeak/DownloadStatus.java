package com.iodroid.memoryLeak;

import android.graphics.Bitmap;

public interface DownloadStatus {

    void DownLoadCompleted(Bitmap bm);
    void DownLoadFailed();
}
