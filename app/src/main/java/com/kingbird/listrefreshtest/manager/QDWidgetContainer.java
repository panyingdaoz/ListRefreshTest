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
//        mWidgets.put(QDBottomSheetFragment.class, new QDItemDescription(QDBottomSheetFragment.class, "QMUIBottomSheet", 2131492875, ""));
//        mWidgets.put(QDCollapsingTopBarLayoutFragment.class, new QDItemDescription(QDCollapsingTopBarLayoutFragment.class, "QMUICollapsingTopBarLayout", 2131492877, ""));
//        mWidgets.put(QDDialogFragment.class, new QDItemDescription(QDDialogFragment.class, "QMUIDialog", 2131492881, ""));
//        mWidgets.put(QDEmptyViewFragment.class, new QDItemDescription(QDEmptyViewFragment.class, "QMUIEmptyView", 2131492883, ""));
//        mWidgets.put(QDFloatLayoutFragment.class, new QDItemDescription(QDFloatLayoutFragment.class, "QMUIFloatLayout", 2131492884, ""));
//        mWidgets.put(QDGroupListViewFragment.class, new QDItemDescription(QDGroupListViewFragment.class, "QMUIGroupListView", 2131492885, ""));
//        mWidgets.put(QDLayoutFragment.class, new QDItemDescription(QDLayoutFragment.class, "QMUILayout", 2131492889, ""));
//        mWidgets.put(QDLinkTextViewFragment.class, new QDItemDescription(QDLinkTextViewFragment.class, "QMUILinkTextView", 2131492890, ""));
//        mWidgets.put(QDPopupFragment.class, new QDItemDescription(QDPopupFragment.class, "QMUIPopups", 2131492892, ""));
//        mWidgets.put(QDPriorityLinearLayoutFragment.class, new QDItemDescription(QDPriorityLinearLayoutFragment.class, "QMUIPriorityLinearLayout", 2131492884, ""));
//        mWidgets.put(QDProgressBarFragment.class, new QDItemDescription(QDProgressBarFragment.class, "QMUIProgressBar", 2131492893, ""));
//        mWidgets.put(QDPullRefreshFragment.class, new QDItemDescription(QDPullRefreshFragment.class, "QMUIPullRefreshLayout", 2131492895, ""));
//        mWidgets.put(QDRadiusImageView2ScaleTypeFragment.class, new QDItemDescription(QDRadiusImageView2ScaleTypeFragment.class, "QMUIRadiusImageView2 ScaleType", 0, ""));
//        mWidgets.put(QDRadiusImageView2UsageFragment.class, new QDItemDescription(QDRadiusImageView2UsageFragment.class, "QMUIRadiusImageView2 usage", 0, ""));
//        mWidgets.put(QDRadiusImageViewFragment.class, new QDItemDescription(QDRadiusImageViewFragment.class, "QMUIRadiusImageView", 2131492897, ""));
//        mWidgets.put(QDRadiusImageViewScaleTypeFragment.class, new QDItemDescription(QDRadiusImageViewScaleTypeFragment.class, "QMUIRadiusImageView ScaleType", 0, ""));
//        mWidgets.put(QDRadiusImageViewUsageFragment.class, new QDItemDescription(QDRadiusImageViewUsageFragment.class, "QMUIRadiusImageView usage", 0, ""));
//        mWidgets.put(QDRecyclerViewDraggableScrollBarFragment.class, new QDItemDescription(QDRecyclerViewDraggableScrollBarFragment.class, "QMUIRVDraggableScrollBar", 2131492899, ""));
//        mWidgets.put(QDSliderFragment.class, new QDItemDescription(QDSliderFragment.class, "QMUISlider", 2131492900, ""));
//        mWidgets.put(QDSpanTouchFixTextViewFragment.class, new QDItemDescription(QDSpanTouchFixTextViewFragment.class, "QMUISpanTouchFixTextView", 2131492902, ""));
//        mWidgets.put(QDTabSegmentFixModeFragment.class, new QDItemDescription(QDTabSegmentFixModeFragment.class, "固定宽度，内容均分", 0, ""));
//        mWidgets.put(QDTabSegmentFragment.class, new QDItemDescription(QDTabSegmentFragment.class, "QMUITabSegment", 2131492906, ""));
//        mWidgets.put(QDTabSegmentScrollableModeFragment.class, new QDItemDescription(QDTabSegmentScrollableModeFragment.class, "内容自适应，超过父容器则滚动", 0, ""));
//        mWidgets.put(QDTabSegmentSpaceWeightFragment.class, new QDItemDescription(QDTabSegmentSpaceWeightFragment.class, "内容自适应，添加weight控制间距", 0, ""));
//        mWidgets.put(QDTipDialogFragment.class, new QDItemDescription(QDTipDialogFragment.class, "QMUITipDialog", 2131492907, ""));
//        mWidgets.put(QDVerticalTextViewFragment.class, new QDItemDescription(QDVerticalTextViewFragment.class, "QMUIVerticalTextView", 2131492908, ""));
//        mWidgets.put(QDQQFaceFragment.class, new QDItemDescription(QDQQFaceFragment.class, "QMUIQQFaceView", 2131492896, ""));
//        mWidgets.put(QDQQFacePerformanceTestFragment.class, new QDItemDescription(QDQQFacePerformanceTestFragment.class, "性能观测[微笑]", 0, ""));
//        mWidgets.put(QDQQFaceUsageFragment.class, new QDItemDescription(QDQQFaceUsageFragment.class, "QQ表情使用展示", 0, ""));
//        mWidgets.put(QDGridSectionLayoutFragment.class, new QDItemDescription(QDGridSectionLayoutFragment.class, "Sticky Section for Grid", 0, ""));
//        mWidgets.put(QDListSectionLayoutFragment.class, new QDItemDescription(QDListSectionLayoutFragment.class, "Sticky Section for List", 0, ""));
//        mWidgets.put(QDListWithDecorationSectionLayoutFragment.class, new QDItemDescription(QDListWithDecorationSectionLayoutFragment.class, "Sticky Section for List(With Decoration)", 0, ""));
        mWidgets.put(QDSectionLayoutFragment.class, new QDItemDescription(QDSectionLayoutFragment.class, "QMUIStickySectionLayout", 2131492904, "https://github.com/Tencent/QMUI_Android/wiki/QMUIStickySectionLayout"));
//        mWidgets.put(QDRVSwipeActionFragment.class, new QDItemDescription(QDRVSwipeActionFragment.class, "QMUIRVItemSwipeAction", 2131492898, ""));
//        mWidgets.put(QDRVSwipeDeleteWithNoActionFragment.class, new QDItemDescription(QDRVSwipeDeleteWithNoActionFragment.class, "Swipe Left: Delete With No Action", 0, ""));
//        mWidgets.put(QDRVSwipeMutiActionFragment.class, new QDItemDescription(QDRVSwipeMutiActionFragment.class, "Swipe Left: Muti Actions", 0, ""));
//        mWidgets.put(QDRVSwipeMutiActionOnlyIconFragment.class, new QDItemDescription(QDRVSwipeMutiActionOnlyIconFragment.class, "Swipe Left: Muti Actions With Only Icon", 0, ""));
//        mWidgets.put(QDRVSwipeMutiActionWithIconFragment.class, new QDItemDescription(QDRVSwipeMutiActionWithIconFragment.class, "Swipe Left: Muti Actions With Icon", 0, ""));
//        mWidgets.put(QDRVSwipeSingleDeleteActionFragment.class, new QDItemDescription(QDRVSwipeSingleDeleteActionFragment.class, "Swipe Left: Single Action And Allow Deletion", 0, ""));
//        mWidgets.put(QDRVSwipeUpDeleteFragment.class, new QDItemDescription(QDRVSwipeUpDeleteFragment.class, "Swipe Up: Long Press To Swipe Delete", 0, ""));
//        mWidgets.put(QDFitSystemWindowViewPagerFragment.class, new QDItemDescription(QDFitSystemWindowViewPagerFragment.class, "QDFitSystemWindowViewPagerFragment", 0, ""));
//        mWidgets.put(QDLoopViewPagerFragment.class, new QDItemDescription(QDLoopViewPagerFragment.class, "QDLoopViewPagerFragment", 0, ""));
//        mWidgets.put(QDViewPagerFragment.class, new QDItemDescription(QDViewPagerFragment.class, "QMUIViewPager", 2131492891, ""));
        mWidgets.put(QDAnimationListViewFragment.class, new QDItemDescription(QDAnimationListViewFragment.class, "QMUIAnimationListView", 2131492874, ""));
//        mWidgets.put(QDArchTestFragment.class, new QDItemDescription(QDArchTestFragment.class, "QMUIFragment", 2131492889, ""));
        mWidgets.put(QDContinuousNestedScroll1Fragment.class, new QDItemDescription(QDContinuousNestedScroll1Fragment.class, "webview + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll2Fragment.class, new QDItemDescription(QDContinuousNestedScroll2Fragment.class, "webview + part sticky header + viewpager", 0, ""));
        mWidgets.put(QDContinuousNestedScroll3Fragment.class, new QDItemDescription(QDContinuousNestedScroll3Fragment.class, "recyclerview + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll4Fragment.class, new QDItemDescription(QDContinuousNestedScroll4Fragment.class, "(header + recyclerview + bottom) + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll5Fragment.class, new QDItemDescription(QDContinuousNestedScroll5Fragment.class, "(header + webview + bottom) + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll6Fragment.class, new QDItemDescription(QDContinuousNestedScroll6Fragment.class, "linearLayout + recyclerview", 0, ""));
        mWidgets.put(QDContinuousNestedScroll7Fragment.class, new QDItemDescription(QDContinuousNestedScroll7Fragment.class, "(header + webview + bottom) + (part sticky header + viewpager)", 0, ""));
        mWidgets.put(QDContinuousNestedScroll8Fragment.class, new QDItemDescription(QDContinuousNestedScroll8Fragment.class, "(header + recyclerView + bottom) + (part sticky header + viewpager)", 0, ""));
        mWidgets.put(QDContinuousNestedScrollFragment.class, new QDItemDescription(QDContinuousNestedScrollFragment.class, "QMUIContinuousNestedScrollLayout", 2131492879, "https://github.com/Tencent/QMUI_Android/wiki/QMUIContinuousNestedScrollLayout"));
//        mWidgets.put(QDSnapHelperFragment.class, new QDItemDescription(QDSnapHelperFragment.class, "用SnapHelper实现RecyclerView按页滚动", 2131492891, ""));
//        mWidgets.put(QDSwipeDeleteListViewFragment.class, new QDItemDescription(QDSwipeDeleteListViewFragment.class, "ListView滑动删除", 0, ""));
//        mWidgets.put(QDWebViewBridgeFragment.class, new QDItemDescription(QDWebViewBridgeFragment.class, "Webview Bridge", 0, ""));
//        mWidgets.put(QDWebViewFixFragment.class, new QDItemDescription(QDWebViewFixFragment.class, "修复 css-env-safe-area-inset", 0, ""));
//        mWidgets.put(QDWebViewFragment.class, new QDItemDescription(QDWebViewFragment.class, "QMUIWebView", 2131492910, ""));
//        mWidgets.put(QDColorHelperFragment.class, new QDItemDescription(QDColorHelperFragment.class, "QMUIColorHelper", 2131492878, ""));
//        mWidgets.put(QDDeviceHelperFragment.class, new QDItemDescription(QDDeviceHelperFragment.class, "QMUIDeviceHelper", 2131492880, ""));
//        mWidgets.put(QDDrawableHelperFragment.class, new QDItemDescription(QDDrawableHelperFragment.class, "QMUIDrawableHelper", 2131492882, ""));
        mWidgets.put(QDNotchHelperFragment.class, new QDItemDescription(QDNotchHelperFragment.class, "QMUINotchHelper", 2131492903, ""));
//        mWidgets.put(QDSpanFragment.class, new QDItemDescription(QDSpanFragment.class, "Span", 2131492901, ""));
//        mWidgets.put(QDStatusBarHelperFragment.class, new QDItemDescription(QDStatusBarHelperFragment.class, "QMUIStatusBarHelper", 2131492903, ""));
//        mWidgets.put(QDViewHelperAnimationFadeFragment.class, new QDItemDescription(QDViewHelperAnimationFadeFragment.class, "Fade 进退场动画", 0, ""));
//        mWidgets.put(QDViewHelperAnimationSlideFragment.class, new QDItemDescription(QDViewHelperAnimationSlideFragment.class, "Slide 进退场动画", 0, ""));
//        mWidgets.put(QDViewHelperBackgroundAnimationBlinkFragment.class, new QDItemDescription(QDViewHelperBackgroundAnimationBlinkFragment.class, "做背景闪动动画", 0, ""));
//        mWidgets.put(QDViewHelperBackgroundAnimationFullFragment.class, new QDItemDescription(QDViewHelperBackgroundAnimationFullFragment.class, "做背景变化动画", 0, ""));
//        mWidgets.put(QDViewHelperFragment.class, new QDItemDescription(QDViewHelperFragment.class, "QMUIViewHelper", 2131492909, ""));
//        mWidgets.put(QDButtonFragment.class, new QDItemDescription(QDButtonFragment.class, "QMUIRoundButton", 2131492876, ""));
    }

    public static QDWidgetContainer getInstance() {
        return sInstance;
    }

    public QDItemDescription get(Class<? extends BaseFragment> fragment) {
        return mWidgets.get(fragment);
    }
}

