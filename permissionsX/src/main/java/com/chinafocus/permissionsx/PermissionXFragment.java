package com.chinafocus.permissionsx;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2020/5/7
 * description：
 */
public class PermissionXFragment extends Fragment {

    private IPermissionListener mListener;
    //    private String[] mPermissions;
    private final int REQUEST_CODE_PERMISSION = 1;

    void requestRuntimePermission(FragmentActivity activity, String[] permissions, IPermissionListener listener) {
//        mPermissions = permissions;
        mListener = listener;

        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            // 核对权限是否需要授权
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            // 对需要授权的权限，启用申请授权对话框
            // 这里启用
            this.requestPermissions(permissions, REQUEST_CODE_PERMISSION);
        } else {
            //doSomeThing
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
                    //如果结果有非通过的权限，就加入到集合中做处理
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        deniedPermissions.add(permission);
                    }
                }

                if (deniedPermissions.isEmpty()) {
                    //doSomeThing
                    if (mListener != null) {
                        mListener.onGranted();
                    }
                } else {
                    //集合不为空，表示有拒绝的权限。启用回调方法
                    if (mListener != null) {
                        mListener.onDenied(deniedPermissions);
                    }
                }

            }
        }
    }

}
