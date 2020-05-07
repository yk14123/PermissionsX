package com.chinafocus.permissionsx;

import java.util.List;


public interface IPermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);
}
