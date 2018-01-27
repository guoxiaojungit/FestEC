package com.diabin.latte.Util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.diabin.latte.app.Latte;

/**
 * Created by lenovo on 2018/1/27.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources= Latte.getApplication().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources=Latte.getApplication().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
