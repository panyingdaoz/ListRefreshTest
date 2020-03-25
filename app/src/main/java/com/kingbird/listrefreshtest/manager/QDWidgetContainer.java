package com.kingbird.listrefreshtest.manager;

import com.kingbird.listrefreshtest.base.BaseFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullFragment;
import com.kingbird.listrefreshtest.fragment.helper.QDNotchHelperFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullHorizontalTestFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullRefreshAndLoadMoreTestFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullVerticalTestFragment;
import com.kingbird.listrefreshtest.fragment.lab.QDAnimationListViewFragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll1Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll2Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll3Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll4Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll5Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll6Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll7Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll8Fragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScrollFragment;
import com.kingbird.listrefreshtest.fragment.section.QDGridSectionLayoutFragment;
import com.kingbird.listrefreshtest.fragment.section.QDListSectionLayoutFragment;
import com.kingbird.listrefreshtest.fragment.section.QDListWithDecorationSectionLayoutFragment;
import com.kingbird.listrefreshtest.fragment.section.QDSectionLayoutFragment;
import com.kingbird.listrefreshtest.model.QDItemDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: QDWidgetContainer
 * @Description: java类作用描述
 * @Author: Pan
 * @CreateDate: 2020/3/20 09:08:08
 */
class QDWidgetContainer {

    private static QDWidgetContainer sInstance = new QDWidgetContainer();

    private Map<Class<? extends BaseFragment>, QDItemDescription> mWidgets;

    private QDWidgetContainer() {
        mWidgets = new HashMap<>();
        mWidgets.put(QDPullFragment.class, new QDItemDescription(QDPullFragment.class, "QMUIPullLayout", 2131492894, ""));
        mWidgets.put(QDPullHorizontalTestFragment.class, new QDItemDescription(QDPullHorizontalTestFragment.class, "PullLayout: Horizontal Test", 0, ""));
        mWidgets.put(QDPullRefreshAndLoadMoreTestFragment.class, new QDItemDescription(QDPullRefreshAndLoadMoreTestFragment.class, "PullLayout: Refresh And LoadMore", 0, ""));
        mWidgets.put(QDPullVerticalTestFragment.class, new QDItemDescription(QDPullVerticalTestFragment.class, "PullLayout: Vertical Test", 0, ""));
        mWidgets.put(QDGridSectionLayoutFragment.class, new QDItemDescription(QDGridSectionLayoutFragment.class, "Sticky Section for Grid", 0, ""));
        mWidgets.put(QDListSectionLayoutFragment.class, new QDItemDescription(QDListSectionLayoutFragment.class, "Sticky Section for List", 0, ""));
        mWidgets.put(QDListWithDecorationSectionLayoutFragment.class, new QDItemDescription(QDListWithDecorationSectionLayoutFragment.class, "Sticky Section for List(With Decoration)", 0, ""));
        mWidgets.put(QDSectionLayoutFragment.class, new QDItemDescription(QDSectionLayoutFragment.class, "QMUIStickySectionLayout", 2131492904, "https://github.com/Tencent/QMUI_Android/wiki/QMUIStickySectionLayout"));
        mWidgets.put(QDAnimationListViewFragment.class, new QDItemDescription(QDAnimationListViewFragment.class, "QMUIAnimationListView", 2131492874, ""));
        mWidgets.put(QDContinuousNestedScroll1Fragment.class, new QDItemDescription(QDContinuousNestedScroll1Fragment.class, "webview + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll2Fragment.class, new QDItemDescription(QDContinuousNestedScroll2Fragment.class, "webview + part sticky header + viewpager", 0, ""));
        mWidgets.put(QDContinuousNestedScroll3Fragment.class, new QDItemDescription(QDContinuousNestedScroll3Fragment.class, "recyclerview + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll4Fragment.class, new QDItemDescription(QDContinuousNestedScroll4Fragment.class, "(header + recyclerview + bottom) + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll5Fragment.class, new QDItemDescription(QDContinuousNestedScroll5Fragment.class, "(header + webview + bottom) + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll6Fragment.class, new QDItemDescription(QDContinuousNestedScroll6Fragment.class, "linearLayout + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll7Fragment.class, new QDItemDescription(QDContinuousNestedScroll7Fragment.class, "(header + webview + bottom) + (part sticky header + viewpager)", 0, ""));
        mWidgets.put(QDContinuousNestedScroll8Fragment.class, new QDItemDescription(QDContinuousNestedScroll8Fragment.class, "(header + recyclerView + bottom) + (part sticky header + viewpager)", 0, ""));
        mWidgets.put(QDContinuousNestedScrollFragment.class, new QDItemDescription(QDContinuousNestedScrollFragment.class, "QMUIContinuousNestedScrollLayout", 2131492879, "https://github.com/Tencent/QMUI_Android/wiki/QMUIContinuousNestedScrollLayout"));
        mWidgets.put(QDNotchHelperFragment.class, new QDItemDescription(QDNotchHelperFragment.class, "QMUINotchHelper", 2131492903, ""));
    }

    public static QDWidgetContainer getInstance() {
        return sInstance;
    }

    public QDItemDescription get(Class<? extends BaseFragment> fragment) {
        return mWidgets.get(fragment);
    }
}

