package io.github.izzyleung.zhihudailypurify.support;

import android.content.Intent;
import android.content.pm.PackageManager;
import io.github.izzyleung.zhihudailypurify.ZhihuDailyPurifyApplication;

public final class Check {
    private Check() {

    }
    //检测是否安装知乎客户端
    //根据包名"com.zhihu.android"判断
    public static boolean isZhihuClientInstalled() {
        try {
            return preparePackageManager().getPackageInfo("com.zhihu.android", PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException ignored) {
            return false;
        }
    }
    //
    public static boolean isIntentSafe(Intent intent) {
        return preparePackageManager().queryIntentActivities(intent, 0).size() > 0;
    }

    private static PackageManager preparePackageManager() {
        return ZhihuDailyPurifyApplication.getInstance().getPackageManager();
    }
}
