package com.kingbird.listrefreshtest.fragment.components;

import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.kingbird.listrefreshtest.R;
import com.kingbird.listrefreshtest.adaptor.QDRecyclerViewAdapter;
import com.kingbird.listrefreshtest.base.BaseFragment;
import com.kingbird.listrefreshtest.manager.QDDataManager;
import com.kingbird.listrefreshtest.model.QDItemDescription;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

@LatestVisitRecord
public class QDPullHorizontalTestFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.pull_layout)
    QMUIPullLayout mPullLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private QDRecyclerViewAdapter mAdapter;

    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pull_horizontal_test_layout, null);
        ButterKnife.bind(this, root);

        mQDItemDescription = QDDataManager.getInstance().getDescription(this.getClass());
        initTopBar();
        initData();

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
    }

    private void initData() {
        mPullLayout.setActionListener(new QMUIPullLayout.ActionListener() {
            @Override
            public void onActionTriggered(final QMUIPullLayout.PullAction pullAction) {
                mPullLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullLayout.finishActionRun(pullAction);
                    }
                }, 1000);
            }
        });
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);
        mAdapter = new QDRecyclerViewAdapter();
        mAdapter.setItemCount(10);
        mRecyclerView.setAdapter(mAdapter);
    }
}
