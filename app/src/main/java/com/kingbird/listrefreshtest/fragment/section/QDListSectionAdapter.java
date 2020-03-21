package com.kingbird.listrefreshtest.fragment.section;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.kingbird.listrefreshtest.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;

public class QDListSectionAdapter extends QDGridSectionAdapter {

    @NonNull
    @Override
    protected QMUIStickySectionAdapter.ViewHolder onCreateSectionItemViewHolder(@NonNull ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        int paddingHor = QMUIDisplayHelper.dp2px(context, 24);
        int paddingVer = QMUIDisplayHelper.dp2px(context, 16);
        TextView tv = new TextView(context);
        tv.setTextSize(14);
        tv.setBackgroundColor(ContextCompat.getColor(context, R.color.qmui_config_color_gray_9));
        tv.setTextColor(Color.DKGRAY);
        tv.setPadding(paddingHor, paddingVer, paddingHor, paddingVer);
        return new QMUIStickySectionAdapter.ViewHolder(tv);
    }
}
