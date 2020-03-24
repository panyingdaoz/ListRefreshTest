package com.kingbird.listrefreshtest.fragment.lab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.kingbird.listrefreshtest.R;
import com.kingbird.listrefreshtest.annotation.Group;
import com.kingbird.listrefreshtest.annotation.Widget;
import com.kingbird.listrefreshtest.base.BaseFragment;
import com.kingbird.listrefreshtest.manager.QDDataManager;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedScrollLayout;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QDContinuousNestedScrollFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.groupListView)
    QMUIGroupListView mGroupListView;

    private QDDataManager mQDDataManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQDDataManager = QDDataManager.getInstance();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_grouplistview, null);
        ButterKnife.bind(this, view);
        initTopBar();
        initGroupListView();
        return view;
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopBar.setTitle(mQDDataManager.getName(this.getClass()));
    }

    private void initGroupListView() {
        QMUIGroupListView.newSection(getContext())
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll1Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll1Fragment fragment = new QDContinuousNestedScroll1Fragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll2Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll2Fragment fragment = new QDContinuousNestedScroll2Fragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll3Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll3Fragment fragment = new QDContinuousNestedScroll3Fragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll4Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll4Fragment fragment = new QDContinuousNestedScroll4Fragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll5Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll5Fragment fragment = new QDContinuousNestedScroll5Fragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll6Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll6Fragment fragment = new QDContinuousNestedScroll6Fragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll7Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll7Fragment fragment = new QDContinuousNestedScroll7Fragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDContinuousNestedScroll8Fragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDContinuousNestedScroll8Fragment fragment = new QDContinuousNestedScroll8Fragment();
                        startFragment(fragment);
                    }
                })
                .addTo(mGroupListView);
    }
}
