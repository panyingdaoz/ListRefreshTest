package com.kingbird.listrefreshtest.fragment.section;

import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingbird.listrefreshtest.model.SectionHeader;
import com.kingbird.listrefreshtest.model.SectionItem;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;

public class QDGridSectionLayoutFragment extends QDBaseSectionLayoutFragment {
    @Override
    protected QMUIStickySectionAdapter<SectionHeader, SectionItem, QMUIStickySectionAdapter.ViewHolder> createAdapter() {
        return new QDGridSectionAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return mAdapter.getItemIndex(i) < 0 ? layoutManager.getSpanCount() : 1;
            }
        });
        return layoutManager;
    }

    @Override
    protected void initStickyLayout() {
        super.initStickyLayout();
        mSectionLayout.getRecyclerView().addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                if(view instanceof TextView){
                    int margin = QMUIDisplayHelper.dp2px(getContext(), 10);
                    outRect.set(margin, margin, margin, margin);
                }else{
                    outRect.set(0, 0, 0, 0);
                }
            }
        });
    }
}
