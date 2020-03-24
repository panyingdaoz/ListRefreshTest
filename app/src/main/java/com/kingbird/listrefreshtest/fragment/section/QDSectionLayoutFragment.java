package com.kingbird.listrefreshtest.fragment.section;

import android.view.LayoutInflater;
import android.view.View;

import com.kingbird.listrefreshtest.R;
import com.kingbird.listrefreshtest.base.BaseFragment;
import com.kingbird.listrefreshtest.manager.QDDataManager;
import com.kingbird.listrefreshtest.model.QDItemDescription;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QDSectionLayoutFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.groupListView)
    QMUIGroupListView mGroupListView;

    private QDDataManager mQDDataManager;
    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_grouplistview, null);
        ButterKnife.bind(this, root);

        mQDDataManager = QDDataManager.getInstance();
        mQDItemDescription = mQDDataManager.getDescription(this.getClass());
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

        mTopBar.setTitle(mQDItemDescription.getName());

        injectDocToTopBar(mTopBar);
    }

    private void initGroupListView() {
        QMUIGroupListView.newSection(getContext())
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDListSectionLayoutFragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDListSectionLayoutFragment fragment = new QDListSectionLayoutFragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDGridSectionLayoutFragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDGridSectionLayoutFragment fragment = new QDGridSectionLayoutFragment();
                        startFragment(fragment);
                    }
                })
                .addItemView(mGroupListView.createItemView(mQDDataManager.getName(
                        QDListWithDecorationSectionLayoutFragment.class)), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QDListWithDecorationSectionLayoutFragment fragment = new QDListWithDecorationSectionLayoutFragment();
                        startFragment(fragment);
                    }
                })
                .addTo(mGroupListView);

    }
}
