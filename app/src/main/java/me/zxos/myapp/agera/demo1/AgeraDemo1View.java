package me.zxos.myapp.agera.demo1;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.agera.Updatable;

import me.zxos.myapp.R;

/**
 * Created by zxos on 16/12/24.
 */

public class AgeraDemo1View implements Updatable{

    //界面 必定需要刷新，观察者，
    //listView下拉事件的触发
    //取完数据需要更新， 实际还要观察数据源

    private View mView;
    private Context mContext;


    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SRLayoutOnRefreshListener mListener;
    private DataRepository dataRepository;
    private MyAdapter adapter;

    public AgeraDemo1View(Context context) {

        mContext = context;
        mView    = View.inflate(context, R.layout.activity_agera, null);

        init();
    }

    private void init() {

        listView = (ListView) mView.findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.srLayout);

        mListener = new SRLayoutOnRefreshListener();

        swipeRefreshLayout.setOnRefreshListener(mListener);

        dataRepository = new DataRepository(new DataFetcher());

        adapter = new MyAdapter(mContext);
        listView.setAdapter(adapter);


    }


    @Override
    public void update() {

        swipeRefreshLayout.setRefreshing(false);

        if (dataRepository.isError()){


        }else{

            adapter.setData(dataRepository.get());
        }



    }










    public View getView() {
        return mView;
    }

    public void onResume() {

        mListener.addUpdatable(dataRepository);

        dataRepository.addUpdatable(this);
    }

    public void onPause() {

        mListener.removeUpdatable(dataRepository);

        dataRepository.removeUpdatable(this);

    }


}
