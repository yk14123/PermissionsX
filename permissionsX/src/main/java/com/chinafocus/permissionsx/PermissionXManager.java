package com.chinafocus.permissionsx;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class PermissionXManager {

    public static void requestRuntimePermission(FragmentActivity activity, String[] permissions, IPermissionListener listener) {
        String TAG = "tag";
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new PermissionXFragment();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commitNow();
        }
        PermissionXFragment permissionXFragment = (PermissionXFragment) fragment;
        permissionXFragment.requestRuntimePermission(activity, permissions, listener);
    }

}
