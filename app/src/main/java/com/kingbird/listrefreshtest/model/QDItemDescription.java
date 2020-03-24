package com.kingbird.listrefreshtest.model;

import com.kingbird.listrefreshtest.base.BaseFragment;

/**
 * @author cginechen
 * @date 2016-10-21
 */

public class QDItemDescription {
    private Class<? extends BaseFragment> mKitDemoClass;
    private String mKitName;
    private int mIconRes;
    private String mDocUrl;

    public QDItemDescription(Class<? extends BaseFragment> kitDemoClass, String kitName) {
        this(kitDemoClass, kitName, 0, "");
    }


    public QDItemDescription(Class<? extends BaseFragment> kitDemoClass, String kitName, int iconRes, String docUrl) {
        mKitDemoClass = kitDemoClass;
//        KLog.e("kitName：" + kitName);
        mKitName = kitName;
//        KLog.e("传入iconRes：" + iconRes);
        mIconRes = iconRes;
        mDocUrl = docUrl;
    }

    public Class<? extends BaseFragment> getDemoClass() {
        return mKitDemoClass;
    }

    public String getName() {
        return mKitName;
    }

    public int getIconRes() {
        return mIconRes;
    }

    public String getDocUrl() {
        return mDocUrl;
    }
}
