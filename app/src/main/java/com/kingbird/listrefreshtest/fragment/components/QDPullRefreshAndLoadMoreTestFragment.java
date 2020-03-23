package com.kingbird.listrefreshtest.fragment.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingbird.listrefreshtest.R;
import com.kingbird.listrefreshtest.base.BaseRecyclerAdapter;
import com.kingbird.listrefreshtest.base.RecyclerViewHolder;
import com.kingbird.listrefreshtest.fragment.BaseFragment;
import com.kingbird.listrefreshtest.manager.QDDataManager;
import com.kingbird.listrefreshtest.model.QDItemDescription;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout;
import com.qmuiteam.qmuidemo.lib.Group;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@LatestVisitRecord
@Widget(group = Group.Other, name = "PullLayout: Refresh And LoadMore")
public class QDPullRefreshAndLoadMoreTestFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.pull_layout)
    QMUIPullLayout mPullLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private BaseRecyclerAdapter<String> mAdapter;

    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pull_refresh_and_load_more_test_layout, null);
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
            public void onActionTriggered(@NonNull final QMUIPullLayout.PullAction pullAction) {
                mPullLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pullAction.getPullEdge() == QMUIPullLayout.PULL_EDGE_TOP) {
                            onRefreshData();
                        } else if (pullAction.getPullEdge() == QMUIPullLayout.PULL_EDGE_BOTTOM) {
                            onLoadMore();
                        }
                        mPullLayout.finishActionRun(pullAction);
                    }
                }, 3000);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

        mAdapter = new BaseRecyclerAdapter<String>(getContext(), null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return android.R.layout.simple_list_item_1;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, String item) {
                holder.setText(android.R.id.text1, item);
            }
        };
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(getContext(), "click position=" + pos, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        onDataLoaded();
    }

    private void onDataLoaded() {
        List<String> data = new ArrayList<>(Arrays.asList("Helps", "Maintain", "Liver", "Health", "Function", "Supports", "Healthy", "Fat",
                "Metabolism", "Nuturally", "Bracket", "Refrigerator", "Bathtub", "Wardrobe", "Comb", "Apron", "Carpet", "Bolster", "Pillow", "Cushion"));
        Collections.shuffle(data);
        mAdapter.setData(data);
    }

    private void onRefreshData() {
        List<String> data = new ArrayList<>();
        long id = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            data.add("onRefreshData-" + id + "-" + i);
        }
        mAdapter.prepend(data);
        mRecyclerView.scrollToPosition(0);
    }

    private void onLoadMore() {
        List<String> data = new ArrayList<>();
        long id = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            data.add("onLoadMore-" + id + "-" + i);
        }
        mAdapter.append(data);
    }
}
