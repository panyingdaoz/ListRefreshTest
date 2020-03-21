package com.kingbird.listrefreshtest.fragment.lab;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedBottomAreaBehavior;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedScrollLayout;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopAreaBehavior;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopDelegateLayout;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopWebView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmuidemo.lib.Group;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;

@Widget(group = Group.Other, name = "(header + webview + bottom) + (part sticky header + viewpager)")
public class QDContinuousNestedScroll7Fragment extends QDContinuousNestedScrollBaseFragment {
    private static final String TAG = "ContinuousNestedScroll";

    private QMUIContinuousNestedTopDelegateLayout mTopDelegateLayout;
    private QMUIContinuousNestedTopWebView mNestedWebView;
    private QDContinuousBottomView mBottomView;

    @Override
    protected void initCoordinatorLayout() {
        mTopDelegateLayout = new QMUIContinuousNestedTopDelegateLayout(getContext());
        mTopDelegateLayout.setBackgroundColor(Color.LTGRAY);
        mNestedWebView = new QMUIContinuousNestedTopWebView(getContext());

        AppCompatTextView headerView = new AppCompatTextView(getContext()) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                        QMUIDisplayHelper.dp2px(getContext(), 100), MeasureSpec.EXACTLY
                ));
            }
        };
        headerView.setTextSize(17);
        headerView.setBackgroundColor(Color.GRAY);
        headerView.setTextColor(Color.WHITE);
        headerView.setText("This is Top Header");
        headerView.setGravity(Gravity.CENTER);
        mTopDelegateLayout.setHeaderView(headerView);

        AppCompatTextView footerView = new AppCompatTextView(getContext()) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                        QMUIDisplayHelper.dp2px(getContext(), 100), MeasureSpec.EXACTLY
                ));
            }
        };
        footerView.setTextSize(17);
        footerView.setBackgroundColor(Color.GRAY);
        footerView.setTextColor(Color.WHITE);
        footerView.setGravity(Gravity.CENTER);
        footerView.setText("This is Top Footer");
        mTopDelegateLayout.setFooterView(footerView);

        mTopDelegateLayout.setDelegateView(mNestedWebView);

        int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
        CoordinatorLayout.LayoutParams topLp = new CoordinatorLayout.LayoutParams(
                matchParent, matchParent);
        topLp.setBehavior(new QMUIContinuousNestedTopAreaBehavior(getContext()));
        mCoordinatorLayout.setTopAreaView(mTopDelegateLayout, topLp);

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
                Log.i(TAG, String.format("newScrollState = %d; fromTopBehavior = %b",
                        newScrollState, fromTopBehavior));
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
