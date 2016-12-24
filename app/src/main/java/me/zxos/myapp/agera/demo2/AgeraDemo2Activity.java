package me.zxos.myapp.agera.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AgeraDemo2Activity extends AppCompatActivity {


    private AgeraDemo2View mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new AgeraDemo2View(this);

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
