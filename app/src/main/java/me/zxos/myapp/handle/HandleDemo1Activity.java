package me.zxos.myapp.handle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import me.zxos.myapp.R;

public class HandleDemo1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_demo1);

        init();
    }

    private void init() {

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = handler.obtainMessage();
                msg.what = 100;
                handler.sendMessage(msg);
            }
        });

    }


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            Log.e("999999999999","callback  " + message.what);

            message.what = 200;

            return false;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.e("999999999999",String.valueOf(msg.what));


        }
    };



}
