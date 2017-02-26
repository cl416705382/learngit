package com.example.clong.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static java.lang.Thread.getDefaultUncaughtExceptionHandler;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int BUMP_MSG=1;
    private Button btn1;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case BUMP_MSG:
                        Log.v ("handler", "Handler====" + msg.arg1);//打印收到的消息
                        break;
                    default:
                        super.handleMessage(msg);//这里最好对不需要或者不关心的消息抛给父类，避免丢失消息
                        break;
                }
            }
        };

        btn1=(Button)findViewById(R.id.btn1);

    }

    @Override
    public void onClick(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(20 * 1000);
                    handler.sendEmptyMessage(BUMP_MSG);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
