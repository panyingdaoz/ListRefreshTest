package com.kingbird.listrefreshtest.fragment.section;

import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingbird.listrefreshtest.model.SectionHeader;
import com.kingbird.listrefreshtest.model.SectionItem;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;

public class QDListSectionLayoutFragment extends QDBaseSectionLayoutFragment {

    @Override
    protected QMUIStickySectionAdapter<SectionHeader, SectionItem, QMUIStickySectionAdapter.ViewHolder> createAdapter() {
        return new QDListSectionAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }
}
