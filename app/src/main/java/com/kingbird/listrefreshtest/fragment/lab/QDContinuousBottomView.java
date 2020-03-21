package com.kingbird.listrefreshtest.fragment.lab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingbird.listrefreshtest.base.BaseRecyclerAdapter;
import com.kingbird.listrefreshtest.base.RecyclerViewHolder;
import com.qmuiteam.qmui.nestedScroll.IQMUIContinuousNestedBottomView;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedBottomDelegateLayout;
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedBottomRecyclerView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QDContinuousBottomView extends QMUIContinuousNestedBottomDelegateLayout {

    private MyViewPager mViewPager;
    private QMUIContinuousNestedBottomRecyclerView mCurrentItemView;
    private int mCurrentPosition = -1;
    private IQMUIContinuousNestedBottomView.OnScrollNotifier mOnScrollNotifier;

    public QDContinuousBottomView(Context context) {
        super(context);
    }

    public QDContinuousBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QDContinuousBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @NonNull
    @Override
    protected View onCreateHeaderView() {
        TextView headerView = new TextView(getContext());
        headerView.setTextSize(16);
        headerView.setTextColor(Color.BLACK);
        headerView.setBackgroundColor(Color.LTGRAY);
        headerView.setGravity(Gravity.CENTER);
        headerView.setText("This is normal view with ViewPager below");
        return headerView;
    }

    @Override
    protected int getHeaderHeightLayoutParam() {
        return QMUIDisplayHelper.dp2px(getContext(), 200);
    }

    @Override
    protected int getHeaderStickyHeight() {
        return QMUIDisplayHelper.dp2px(getContext(), 50);
    }


    @NonNull
    @Override
    protected View onCreateContentView() {
        mViewPager = new MyViewPager(getContext());
        mViewPager.setAdapter(new QMUIPagerAdapter() {
            @Override
            protected Object hydrate(ViewGroup container, int position) {
                QMUIContinuousNestedBottomRecyclerView recyclerView = new QMUIContinuousNestedBottomRecyclerView(getContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
                    @Override
                    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT);
                    }
                });

                BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(getContext(), null) {
                    @Override
                    public int getItemLayoutId(int viewType) {
                        return android.R.layout.simple_list_item_1;
                    }

                    @Override
                    public void bindData(RecyclerViewHolder holder, int position, String item) {
                        holder.setText(android.R.id.text1, item);
                    }
                };
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int pos) {
                        Toast.makeText(getContext(), "click position=" + pos, Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(adapter);
                onDataLoaded(adapter);
                return recyclerView;
            }

            @Override
            protected void populate(ViewGroup container, Object item, int position) {
                container.addView((View) item);
            }

            @Override
            protected void destroy(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.setPrimaryItem(container, position, object);
                mCurrentItemView = (QMUIContinuousNestedBottomRecyclerView) object;
                mCurrentPosition = position;
                if (mOnScrollNotifier != null) {
                    mCurrentItemView.injectScrollNotifier(mOnScrollNotifier);
                }
            }
        });
        return mViewPager;
    }

    private void onDataLoaded(BaseRecyclerAdapter<String> adapter) {
        List<String> data = new ArrayList<>(Arrays.asList("Helps", "Maintain", "Liver", "Health",
                "Function", "Supports", "Healthy", "Fat", "Metabolism", "Nuturally", "Bracket",
                "Refrigerator", "Bathtub", "Wardrobe", "Comb", "Apron", "Carpet", "Bolster",
                "Pillow", "Cushion"));
        Collections.shuffle(data);
        adapter.setData(data);
    }

    class MyViewPager extends QMUIViewPager implements IQMUIContinuousNestedBottomView {
        static final String KEY_CURRENT_POSITION = "demo_bottom_current_position";

        public MyViewPager(Context context) {
            super(context);
        }

        @Override
        public void consumeScroll(int dyUnconsumed) {
            if (mCurrentItemView != null) {
                mCurrentItemView.consumeScroll(dyUnconsumed);
            }

        }

        @Override
        public void smoothScrollYBy(int dy, int duration) {
            if (mCurrentItemView != null) {
                mCurrentItemView.smoothScrollYBy(dy, duration);
            }
        }

        @Override
        public void stopScroll() {
            if (mCurrentItemView != null) {
                mCurrentItemView.stopScroll();
            }
        }
// 2131230884 2131492894  #0x7f0c001e
        @Override
        public int getContentHeight() {
            if (mCurrentItemView != null) {
                return mCurrentItemView.getContentHeight();
            }
            return 0;
        }

        @Override
        public int getCurrentScroll() {
            if (mCurrentItemView != null) {
                return mCurrentItemView.getCurrentScroll();
            }
            return 0;
        }

        @Override
        public int getScrollOffsetRange() {
            if (mCurrentItemView != null) {
                return mCurrentItemView.getScrollOffsetRange();
            }
            return getHeight();
        }

        @Override
        public void injectScrollNotifier(OnScrollNotifier notifier) {
            mOnScrollNotifier = notifier;
            if (mCurrentItemView != null) {
                mCurrentItemView.injectScrollNotifier(notifier);
            }
        }

        @Override
        public void saveScrollInfo(@NonNull Bundle bundle) {
            bundle.putInt(KEY_CURRENT_POSITION, mCurrentPosition);
            if(mCurrentItemView != null){
                mCurrentItemView.saveScrollInfo(bundle);
            }
        }

        @Override
        public void restoreScrollInfo(@NonNull Bundle bundle) {
            if(mCurrentItemView != null){
                int currentPos = bundle.getInt(KEY_CURRENT_POSITION, -1);
                if(currentPos == mCurrentPosition){
                    mCurrentItemView.restoreScrollInfo(bundle);
                }
            }
        }
    }
}