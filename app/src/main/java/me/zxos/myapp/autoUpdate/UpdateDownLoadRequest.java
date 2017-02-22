package me.zxos.myapp.autoUpdate;

import android.os.Handler;
import android.os.Message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zxos on 17/2/3.
 */

public class UpdateDownLoadRequest implements Runnable{

    private String downloadUrl;
    private String localFilePath;
    private UpdateDownLoadListener downLoadListener;
    private boolean isDownloading = false;
    private long currentLength;

    private DownLoadHandler downLoadHandler;

    public UpdateDownLoadRequest(String downloadUrl, String localFilePath, UpdateDownLoadListener downLoadListener) {

        this.downloadUrl = downloadUrl;
        this.localFilePath = localFilePath;
        this.downLoadListener = downLoadListener;

        isDownloading = true;
        this.downLoadHandler = new DownLoadHandler();

    }

    @Override
    public void run() {

        makeRequest();
    }

    private void makeRequest() {

        if (!Thread.currentThread().isInterrupted()){

            try {
                URL url = new URL(downloadUrl);
                HttpURLConnection connection = (HttpURLConnection) url.getContent();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setRequestProperty("Connection","Keep-Alive");
                connection.connect();   //阻塞当前线程

                currentLength = connection.getContentLength();

                if (!Thread.currentThread().isInterrupted()){
                    downLoadHandler.sendResponseMessage(connection.getInputStream());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class DownLoadHandler{

        private Handler handler;

        private int mCompleteSize;

        public DownLoadHandler() {

            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    handleMsg(msg);
                }
            };
        }


        private void handleMsg(Message message){

        }

        public void sendResponseMessage(InputStream inputStream) {

            RandomAccessFile randomAccessFile = null;
            mCompleteSize = 0;

            try {
                byte[] buffer = new byte[1024];
                int length = -1;
                int limit  = 0;
                randomAccessFile = new RandomAccessFile(localFilePath, "rwd");

                while ((length = inputStream.read(buffer)) != -1){

                    if (isDownloading){

                        randomAccessFile.write(buffer, 0, length);
                        mCompleteSize += length;
                        if (mCompleteSize < currentLength){


                        }
                    }
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
