package com.fumiao.core.uitls;

import android.widget.Toast;

import com.fumiao.core.app.CoreApp;

/**
 * Created by chee on 2018/8/21.
 */

public class ToastUitl {

    private static Toast toast = null;

    public static void error(String msg) {
        if (toast == null) {
            toast = Toast.makeText(CoreApp.getSingle(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void success(String msg) {
        if (toast == null) {
            toast = Toast.makeText(CoreApp.getSingle(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void show(String message) {
        if (toast == null) {
            toast = Toast.makeText(CoreApp.getSingle(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
