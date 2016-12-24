package me.zxos.myapp.agera.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AgeraDemo1Activity extends AppCompatActivity {

    private AgeraDemo1View mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new AgeraDemo1View(this);


        setContentView(mView.getView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }
}
