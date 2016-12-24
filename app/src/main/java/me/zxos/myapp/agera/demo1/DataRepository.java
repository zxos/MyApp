package me.zxos.myapp.agera.demo1;

import android.support.annotation.NonNull;


import com.google.android.agera.BaseObservable;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxos on 16/12/24.
 */

public class DataRepository extends BaseObservable implements Supplier<List<String>>, Updatable, DataFetcher.CallBack {

    //数据源，存放数据 观察界面的下拉事件， 观察者



    private DataFetcher dataFetcher;  //实际执行获取数据的执行者

    private List<String> data;
    private boolean isError;


    public DataRepository(DataFetcher dataFetcher) {

        this.dataFetcher = dataFetcher;
    }


    public boolean isError(){
        return isError;
    }

    @NonNull
    @Override
    public List<String> get() {   //统一向外部提供数据的方法

        return data;
    }

    @Override
    public void update() {

        dataFetcher.get(this);
    }

    @Override
    public void fSuccess(List<String> data) {  //取完数据 通知界面更新
        isError = false;
        this.data = data;
        dispatchUpdate();
    }

    @Override
    public void fError() {
        isError = true;
        dispatchUpdate();
    }
}
