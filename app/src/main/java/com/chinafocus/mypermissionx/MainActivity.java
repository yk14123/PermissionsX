package com.chinafocus.mypermissionx;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chinafocus.permissionsx.IPermissionListener;
import com.chinafocus.permissionsx.PermissionXManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        PermissionXManager.requestRuntimePermission(this, new String[]{Manifest.permission.CALL_PHONE}, new IPermissionListener() {
            @Override
            public void onGranted() {
                callPhone("13500351975");
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (String denied : deniedPermission) {
                    Log.e("MyLog", " denied >> " + denied);
                }
            }
        });
    }


    @SuppressLint("MissingPermission")
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
