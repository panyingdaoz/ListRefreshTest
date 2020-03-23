package com.kingbird.listrefreshtest.fragment.helper;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.kingbird.listrefreshtest.R;
import com.kingbird.listrefreshtest.annotation.Group;
import com.kingbird.listrefreshtest.annotation.Widget;
import com.kingbird.listrefreshtest.fragment.BaseFragment;
import com.kingbird.listrefreshtest.manager.QDDataManager;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.tab.QMUITab;
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder;
import com.qmuiteam.qmui.widget.tab.QMUITabSegment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Widget(group = Group.Helper, name = "QMUINotchHelper"
//        , iconRes = R.mipmap.icon_grid_status_bar_helper
)
public class QDNotchHelperFragment extends BaseFragment {
    private static final String TAG = "QDNotchHelperFragment";
    @BindView(R.id.not_safe_bg)
    FrameLayout mNoSafeBgLayout;
    @BindView(R.id.safe_area_tv)
    TextView mSafeAreaTv;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.tabs_container)
    FrameLayout mTabContainer;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;

    boolean isFullScreen = false;

    @OnClick(R.id.safe_area_tv)
    void onClickTv() {
        if (isFullScreen) {
            changeToNotFullScreen();
        } else {
            changeToFullScreen();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected View onCreateView() {
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.fragment_notch, null);
        ButterKnife.bind(this, layout);
        initTopBar();
        initTabs();
        mNoSafeBgLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int height = bottom - top;
                int width = right - left;
                int screenUsefulWidth = QMUIDisplayHelper.getUsefulScreenWidth(v);
                int screenUsefulHeight = QMUIDisplayHelper.getUsefulScreenHeight(v);
                Log.i(TAG, "width = " + width + "; height = " + height +
                        "; screenUsefulWidth = " + screenUsefulWidth +
                        "; screenUsefulHeight = " + screenUsefulHeight);
            }
        });
        return layout;
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopBar.setTitle(QDDataManager.getInstance().getName(this.getClass()));
    }

    private void initTabs() {
        QMUITabBuilder builder = mTabSegment.tabBuilder();
        builder.setColorAttr(R.attr.qmui_config_color_gray_6, R.attr.qmui_config_color_blue)
                .setSelectedIconScale(2f)
                .setTextSize(QMUIDisplayHelper.sp2px(Objects.requireNonNull(getContext()), 14), QMUIDisplayHelper.sp2px(getContext(), 16))
                .setDynamicChangeIconColor(false);
        QMUITab component = builder
                .setNormalDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component))
                .setSelectedDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component_selected))
                .setText("Components")
                .build(getContext());
        QMUITab util = builder
                .setNormalDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_util))
                .setSelectedDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_util_selected))
                .setText("Helper")
                .build(getContext());
        QMUITab lab = builder
                .setNormalDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab))
                .setSelectedDrawable(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab_selected))
                .setText("Lab")
                .build(getContext());

        mTabSegment.addTab(component)
                .addTab(util)
                .addTab(lab);
        mTabSegment.notifyDataChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        changeToFullScreen();
    }

    private void changeToFullScreen() {
        isFullScreen = true;
        Activity activity = getActivity();
        if (activity != null) {
            Window window = activity.getWindow();
            if (window == null) {
                return;
            }
            View decorView = window.getDecorView();
            int systemUi = decorView.getSystemUiVisibility();
            systemUi |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                systemUi |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                systemUi |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(systemUi);
            QMUIDisplayHelper.setFullScreen(getActivity());
            QMUIViewHelper.fadeOut(mTopBar, 300, null, true);
            QMUIViewHelper.fadeOut(mTabContainer, 300, null, true);
        }
    }

    private void changeToNotFullScreen() {
        isFullScreen = false;
        Activity activity = getActivity();
        if (activity != null) {
            Window window = activity.getWindow();
            if (window == null) {
                return;
            }
            final View decorView = window.getDecorView();
            int systemUi = decorView.getSystemUiVisibility();
            systemUi &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                systemUi &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
                systemUi |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                systemUi &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(systemUi);
            QMUIDisplayHelper.cancelFullScreen(getActivity());
            QMUIViewHelper.fadeIn(mTopBar, 300, null, true);
            QMUIViewHelper.fadeIn(mTabContainer, 300, null, true);
            decorView.post(new Runnable() {
                @Override
                public void run() {
                    ViewCompat.requestApplyInsets(decorView);
                }
            });

        }

    }

    @Override
    protected void popBackStack() {
        changeToNotFullScreen();
        super.popBackStack();
    }
}