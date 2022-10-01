package com.hostapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodCall;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MyFlutterActivity extends FlutterActivity {

    private static final String CHANNEL = "samples.flutter.dev";

    String tempToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        tempToken = extras.getString("tempToken");
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        // logic to register channel
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(((methodCall, result) -> {
            if (methodCall.method.equals("ScreenCallback")) {

                String first = methodCall.argument("first");
                String second = methodCall.argument("second");

                if (!TextUtils.isEmpty(first)) {
                    finish();
                } else {
                    startActivity(new Intent(MyFlutterActivity.this, SecondActivity.class));
                    result.success("batteryLevel"); // It returns string "batteryLevel".

                }
                Singleton.getInstance().setArgs(methodCall.arguments.toString());

                toast(methodCall.arguments.toString());
//                toast(methodCall.argument("second"));

            } else if (methodCall.method.equals("callback")) {

                toast(methodCall.arguments.toString());
            } else if (methodCall.method.equals("getTempToken")) {

                HashMap<String,String> map = new HashMap<>();

                map.put("tempToken", tempToken);
                map.put("cif", "246840");
                map.put("mobile", "8668616909");
//                result.success(tempToken); // It returns string "tempToken".
                result.success(map);//s string "tempToken".

            } else {
                result.notImplemented();
            }
        }));


    }

    public void toast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }
}