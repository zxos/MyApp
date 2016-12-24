package me.zxos.myapp.agera.demo1;

import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zxos on 16/12/24.
 */

public class DataFetcher {



    public void get(@NonNull final CallBack callBack){


        Handler mHandler = new Handler();
        Runnable runnable= new Runnable() {
            @Override
            public void run() {

                List<String> data = new ArrayList<>();

                int i = 0;
                Random random = new Random();

                while (i < 20){

                    int a = random.nextInt(20);

                    data.add(a + "");

                    i++;
                }


                callBack.fSuccess(data);
            }
        };

        mHandler.postDelayed(runnable, 2000);

    }



    interface CallBack {

        void fSuccess(List<String> data);

        void fError();
    }

}
