package me.zxos.myapp.agera.demo2;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.zxos.myapp.R;
import me.zxos.myapp.agera.demo1.MyAdapter;
import me.zxos.myapp.agera.demo1.SRLayoutOnRefreshListener;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * Created by zxos on 16/12/24.
 */

public class AgeraDemo2View implements Updatable {


    private View mView;
    private Context mContext;

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SRLayoutOnRefreshListener listener;
    private MyAdapter adapter ;

    private Repository<Result<List<String>>> dataRepository;
    private ExecutorService networkExecutor;



    public AgeraDemo2View(Context context) {

        mContext = context;
        mView = View.inflate(context, R.layout.activity_agera, null);

        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.srLayout);
        listView = (ListView) mView.findViewById(R.id.listView);

        listener = new SRLayoutOnRefreshListener();

        swipeRefreshLayout.setOnRefreshListener(listener);

        adapter = new MyAdapter(mContext);
        listView.setAdapter(adapter);

        setUpRepository();
    }

    private void setUpRepository() {

        networkExecutor = Executors.newSingleThreadExecutor();

        dataRepository = Repositories
                .repositoryWithInitialValue(Result.<List<String>>absent())
                .observe(listener)
                .onUpdatesPerLoop()
                .goTo(networkExecutor)
                .thenGetFrom(new DataSupplier())
                .compile();




    }


    public View getView() {
        return mView;
    }

    public void onResume() {

        dataRepository.addUpdatable(this);   //会触发supplier里的get()

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });

    }

    public void onPause() {

        dataRepository.removeUpdatable(this);
    }

    @Override
    public void update() {


        if (dataRepository.get().isAbsent()){
            swipeRefreshLayout.setRefreshing(true);
        }else if (dataRepository.get().failed()){

            swipeRefreshLayout.setRefreshing(false);
        }else if (dataRepository.get().succeeded()){
            swipeRefreshLayout.setRefreshing(false);
            adapter.setData(dataRepository.get().get());
        }
    }
}
