package com.kingbird.listrefreshtest.fragment.lab;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingbird.listrefreshtest.base.BaseRecyclerAdapter;
import com.kingbird.listrefreshtest.base.RecyclerViewHolder;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.arch.record.RecordArgumentEditor;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedBottomAreaBehavior;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedBottomRecyclerView;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopAreaBehavior;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopWebView;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@LatestVisitRecord
public class QDContinuousNestedScroll1Fragment extends QDContinuousNestedScrollBaseFragment {

    private QMUIWebView mNestedWebView;
    private RecyclerView mRecyclerView;
    private BaseRecyclerAdapter<String> mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getInt("fragment_test") == 20) {
            Toast.makeText(getContext(), "恢复到最近阅读(Int)", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initCoordinatorLayout() {
        mNestedWebView = new QMUIContinuousNestedTopWebView(getContext());
        int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
        CoordinatorLayout.LayoutParams webViewLp = new CoordinatorLayout.LayoutParams(
                matchParent, matchParent);
        webViewLp.setBehavior(new QMUIContinuousNestedTopAreaBehavior(getContext()));
        mCoordinatorLayout.setTopAreaView(mNestedWebView, webViewLp);

        mRecyclerView = new QMUIContinuousNestedBottomRecyclerView(getContext());
        CoordinatorLayout.LayoutParams recyclerViewLp = new CoordinatorLayout.LayoutParams(
                matchParent, matchParent);
        recyclerViewLp.setBehavior(new QMUIContinuousNestedBottomAreaBehavior());
        mCoordinatorLayout.setBottomAreaView(mRecyclerView, recyclerViewLp);

        mNestedWebView.loadUrl("https://mp.weixin.qq.com/s/zgfLOMD2JfZJKfHx-5BsBg");

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
        List<String> data = new ArrayList<>(Arrays.asList("Helps", "Maintain", "Liver",
                "Health", "Function", "Supports", "Healthy", "Fat", "Metabolism",
                "Nuturally", "Bracket", "Refrigerator", "Bathtub", "Wardrobe", "Comb",
                "Apron", "Carpet", "Bolster", "Pillow", "Cushion"));
        Collections.shuffle(data);
        mAdapter.setData(data);
    }

    @Override
    public void onCollectLatestVisitArgument(RecordArgumentEditor editor) {
        editor.putInt("fragment_test", 20);
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
