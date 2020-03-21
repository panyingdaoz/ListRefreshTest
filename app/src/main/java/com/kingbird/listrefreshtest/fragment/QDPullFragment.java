package com.kingbird.listrefreshtest.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.kingbird.listrefreshtest.R;
import com.kingbird.listrefreshtest.manager.QDDataManager;
import com.kingbird.listrefreshtest.model.QDItemDescription;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(widgetClass = QMUIPullLayout.class, iconRes = R.mipmap.icon_grid_pull_layout)
public class QDPullFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.groupListView)
    QMUIGroupListView mGroupListView;

    private QDDataManager qdDataManager;
    private QDItemDescription qdItemDescription;

    @Override
    protected View onCreateView() {
        KLog.e("调用对象："+getActivity());
        qdDataManager = QDDataManager.getInstance();
        @SuppressLint("InflateParams")
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_grouplistview, null);
        ButterKnife.bind(this, root);

        KLog.e("this.getClass()："+this.getClass());
        qdItemDescription = qdDataManager.getDescription(this.getClass());
        initTopBar();

        initGroupListView();

        return root;
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopBar.setTitle(qdItemDescription.getName());

        injectDocToTopBar(mTopBar);
    }

    private void initGroupListView() {
        QMUIGroupListView.newSection(getContext())
                .addItemView(mGroupListView.createItemView(qdDataManager.getName(
                        QDPullVerticalTestFragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDPullVerticalTestFragment fragment = new QDPullVerticalTestFragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(qdDataManager.getName(
                        QDPullHorizontalTestFragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDPullHorizontalTestFragment fragment = new QDPullHorizontalTestFragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(qdDataManager.getName(
                        QDPullRefreshAndLoadMoreTestFragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDPullRefreshAndLoadMoreTestFragment fragment = new QDPullRefreshAndLoadMoreTestFragment();
                        startFragment(fragment);
                    }
                })
                .addTo(mGroupListView);

    }
}
