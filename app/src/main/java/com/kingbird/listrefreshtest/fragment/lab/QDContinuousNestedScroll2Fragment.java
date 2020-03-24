package com.kingbird.listrefreshtest.fragment.lab;

import android.util.Log;
import android.view.ViewGroup;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedBottomAreaBehavior;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedScrollLayout;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopAreaBehavior;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopWebView;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;

public class QDContinuousNestedScroll2Fragment extends QDContinuousNestedScrollBaseFragment {
    private static final String TAG = "ContinuousNestedScroll";

    private QMUIWebView mNestedWebView;
    private QDContinuousBottomView mBottomView;

    @Override
    protected void initCoordinatorLayout() {
        mNestedWebView = new QMUIContinuousNestedTopWebView(getContext());
        int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
        CoordinatorLayout.LayoutParams webViewLp = new CoordinatorLayout.LayoutParams(
                matchParent, matchParent);
        webViewLp.setBehavior(new QMUIContinuousNestedTopAreaBehavior(getContext()));
        mCoordinatorLayout.setTopAreaView(mNestedWebView, webViewLp);

        mBottomView = new QDContinuousBottomView(getContext());
        CoordinatorLayout.LayoutParams recyclerViewLp = new CoordinatorLayout.LayoutParams(
                matchParent, matchParent);
        recyclerViewLp.setBehavior(new QMUIContinuousNestedBottomAreaBehavior());
        mCoordinatorLayout.setBottomAreaView(mBottomView, recyclerViewLp);

        mNestedWebView.loadUrl("https://mp.weixin.qq.com/s/zgfLOMD2JfZJKfHx-5BsBg");

        mCoordinatorLayout.addOnScrollListener(new QMUIContinuousNestedScrollLayout.OnScrollListener() {

            @Override
            public void onScroll(QMUIContinuousNestedScrollLayout scrollLayout, int topCurrent, int topRange, int offsetCurrent,
                                 int offsetRange, int bottomCurrent, int bottomRange) {
                Log.i(TAG, String.format("topCurrent = %d; topRange = %d; " +
                                "offsetCurrent = %d; offsetRange = %d; " +
                                "bottomCurrent = %d, bottomRange = %d",
                        topCurrent, topRange, offsetCurrent, offsetRange, bottomCurrent, bottomRange));
            }

            @Override
            public void onScrollStateChange(QMUIContinuousNestedScrollLayout scrollLayout, int newScrollState, boolean fromTopBehavior) {

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mNestedWebView != null) {
            mCoordinatorLayout.removeView(mNestedWebView);
            mNestedWebView.destroy();
            mNestedWebView = null;
        }
    }

}
