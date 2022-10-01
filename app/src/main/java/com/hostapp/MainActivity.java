package com.hostapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {

    Button btn, btnScr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btnScr = findViewById(R.id.btnScr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                functionCall();
            }
        });

        btnScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTempToken();
            }
        });
    }

    void functionCall() {
//        Test test = new Test("val from host | ", this);
//        Test test = new Test();
//        test.setMyInterface(this);
//        test.test();

//        Singleton.getInstance().setMyInterface(this);
//
//        Singleton.getInstance().test("val from host | ");

        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

    void getTempToken() {

        //onSuccess
        flutterScreenCall("My Temp Token");
    }

    void flutterScreenCall(String tempToken) {
//        startActivity(
//                FlutterActivity
//                        .withNewEngine()
//                        .initialRoute("/first")
////                        .dartEntrypointArgs()
//                        .build(MainActivity.this)
//        );

        startActivity(
                new FlutterActivity.NewEngineIntentBuilder(MyFlutterActivity.class)
                        .initialRoute("/first")
//                        .initialRoute("/secondScreen")
                        .build(getApplicationContext())
//                        .putExtra("title", getString(R.string.app_name))
                        .putExtra("tempToken", tempToken)
        );

//        finish();

    }

}