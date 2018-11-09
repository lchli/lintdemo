package com.appdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    static final int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        System.err.println("1");
        System.out.println(1);///
        System.out.println(100L);///
        System.out.println(100.0);///
        System.out.println(i);
        System.out.println(true);
        test(null);

        Toast.makeText(this, "99", Toast.LENGTH_LONG).show();

      String r=  t().toLowerCase();


    }

    void test(Object o) {
    }

    String t(){
        return null;
    }
}
