package com.kingbird.listrefreshtest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import com.kingbird.listrefreshtest.manager.QDSkinManager;
import com.kingbird.listrefreshtest.manager.QDUpgradeManager;
import com.qmuiteam.qmui.QMUILog;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.qmuiteam.qmui.skin.QMUISkinMaker;
import com.squareup.leakcanary.LeakCanary;

/**
 * Demo 的 Application 入口。
 * Created by cgine on 16/3/22.
 */
public class QDApplication extends Application {

    public static boolean openSkinMake = false;

    @SuppressLint("StaticFieldLeak") private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        QMUILog.setDelegete(new QMUILog.QMUILogDelegate() {
            @Override
            public void e(String tag, String msg, Object... obj) {

            }

            @Override
            public void w(String tag, String msg, Object... obj) {
                Log.w(tag, msg);
            }

            @Override
            public void i(String tag, String msg, Object... obj) {

            }

            @Override
            public void d(String tag, String msg, Object... obj) {

            }

            @Override
            public void printErrStackTrace(String tag, Throwable tr, String format, Object... obj) {

            }
        });

        QDUpgradeManager.getInstance(this).check();
        QMUISwipeBackActivityManager.init(this);
//        QMUIQQFaceCompiler.setDefaultQQFaceManager(QDQQFaceManager.getInstance());
//        QDSkinManager.install(this);
//        QMUISkinMaker.init(context,
//                new String[]{"com.qmuiteam.qmuidemo"},
//                new String[]{"app_skin_"}, R.attr.class);
    }

//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if((newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES){
//            QDSkinManager.changeSkin(QDSkinManager.SKIN_DARK);
//        }else if(QDSkinManager.getCurrentSkin() == QDSkinManager.SKIN_DARK){
//            QDSkinManager.changeSkin(QDSkinManager.SKIN_BLUE);
//        }
//    }
}
