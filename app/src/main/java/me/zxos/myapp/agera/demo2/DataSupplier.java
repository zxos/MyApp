package me.zxos.myapp.agera.demo2;

import android.support.annotation.NonNull;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zxos on 16/12/24.
 */

public class DataSupplier implements Supplier<Result<List<String>>> {



    private List<String> getData(){

        List<String> data = new ArrayList<>();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;
        Random random = new Random();

        while (i < 20){

            int a = random.nextInt(20);

            data.add(a + "");

            i++;
        }

        return data;
    }



    @NonNull
    @Override
    public Result<List<String>> get() {
        List<String> data = new ArrayList<>();
        if (data == null){
            return Result.failure();
        }else
            return Result.success(getData());
    }
}
