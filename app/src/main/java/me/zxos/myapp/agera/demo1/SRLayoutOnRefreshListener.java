package me.zxos.myapp.agera.demo1;

import android.support.v4.widget.SwipeRefreshLayout;

import com.google.android.agera.BaseObservable;

/**
 * Created by zxos on 16/12/24.
 */

public class SRLayoutOnRefreshListener extends BaseObservable implements SwipeRefreshLayout.OnRefreshListener {

    //下拉事件，出发数据库拉数据， 实际不需要刷新自己， 被观察者， 需要同时被界面和数据源观察

    @Override
    public void onRefresh() {
        dispatchUpdate();  //通知事件发生
    }
}
