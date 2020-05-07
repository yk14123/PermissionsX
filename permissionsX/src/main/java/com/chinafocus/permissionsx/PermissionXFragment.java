package com.chinafocus.permissionsx;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;


public class PermissionXFragment extends Fragment {

    private IPermissionListener mListener;
    private final int REQUEST_CODE_PERMISSION = 1;

    void requestRuntimePermission(FragmentActivity activity, String[] permissions, IPermissionListener listener) {
        mListener = listener;

        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            this.requestPermissions(permissions, REQUEST_CODE_PERMISSION);
        } else {
            if (listener != null) {
                listener.onGranted();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0) {
                List<String> deniedPermissions = new ArrayList<>();

                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    String permission = permissions[i];
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        deniedPermissions.add(permission);
                    }
                }

                if (deniedPermissions.isEmpty()) {
                    if (mListener != null) {
                        mListener.onGranted();
                    }
                } else {
                    if (mListener != null) {
                        mListener.onDenied(deniedPermissions);
                    }
                }
            }
        }
    }

}
