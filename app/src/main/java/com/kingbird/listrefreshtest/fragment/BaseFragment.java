package com.kingbird.listrefreshtest.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kingbird.listrefreshtest.MyApplication;
import com.kingbird.listrefreshtest.QDMainActivity;
import com.kingbird.listrefreshtest.fragment.home.HomeFragment;
import com.kingbird.listrefreshtest.manager.QDDataManager;
import com.kingbird.listrefreshtest.manager.QDUpgradeManager;
import com.kingbird.listrefreshtest.model.QDItemDescription;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.SwipeBackLayout;
import com.qmuiteam.qmui.skin.QMUISkinMaker;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * @ClassName: BaseFragment
 * @Description: java类作用描述
 * @Author: Pan
 * @CreateDate: 2020/3/19 17:08:24
 */
public abstract class BaseFragment extends QMUIFragment {

    private int mBindId = -1;

    public BaseFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (MyApplication.openSkinMake) {
            openSkinMaker();
        }
    }

    public void openSkinMaker() {
        if (mBindId < 0) {
            mBindId = QMUISkinMaker.getInstance().bind(this);
        }
    }

    public void closeSkinMaker() {
        QMUISkinMaker.getInstance().unBind(mBindId);
        mBindId = -1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        closeSkinMaker();
    }

    @Override
    protected int backViewInitOffset(Context context, int dragDirection, int moveEdge) {
        if (moveEdge == SwipeBackLayout.EDGE_TOP || moveEdge == SwipeBackLayout.EDGE_BOTTOM) {
            return 0;
        }
        return QMUIDisplayHelper.dp2px(context, 100);
    }

    @Override
    public void onResume() {
        super.onResume();
        QDUpgradeManager.getInstance(getContext()).runUpgradeTipTaskIfExist(getActivity());

    }

    @Override
    public Object onLastFragmentFinish() {
        return new HomeFragment();

    }

    protected void goToWebExplorer(@NonNull String url, @Nullable String title) {
        Intent intent = QDMainActivity.createWebExplorerIntent(getContext(), url, title);
        startActivity(intent);
    }

    protected void injectDocToTopBar(QMUITopBar topBar) {
        final QDItemDescription description = QDDataManager.getInstance().getDescription(this.getClass());
        if (description != null) {
            topBar.addRightTextButton("DOC", QMUIViewHelper.generateViewId())
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToWebExplorer(description.getDocUrl(), description.getName());
                        }
                    });
        }
    }

    protected void injectDocToTopBar(QMUITopBarLayout topBar) {
        final QDItemDescription description = QDDataManager.getInstance().getDescription(this.getClass());
        if (description != null) {
            topBar.addRightTextButton("DOC", QMUIViewHelper.generateViewId())
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToWebExplorer(description.getDocUrl(), description.getName());
                        }
                    });
        }
    }
}
