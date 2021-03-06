package com.kingbird.listrefreshtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kingbird.listrefreshtest.fragment.helper.QDNotchHelperFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullHorizontalTestFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullRefreshAndLoadMoreTestFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullVerticalTestFragment;
import com.kingbird.listrefreshtest.fragment.QDWebExplorerFragment;
import com.kingbird.listrefreshtest.fragment.home.HomeFragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll1Fragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment;
import com.qmuiteam.qmui.arch.annotation.FirstFragments;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIViewOffsetHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kingbird.listrefreshtest.fragment.QDWebExplorerFragment.EXTRA_TITLE;
import static com.kingbird.listrefreshtest.fragment.QDWebExplorerFragment.EXTRA_URL;

@FirstFragments(
        value = {
                HomeFragment.class,
                QDNotchHelperFragment.class,
                QDWebExplorerFragment.class,
                QDContinuousNestedScroll1Fragment.class,
                QDPullVerticalTestFragment.class,
                QDPullHorizontalTestFragment.class,
                QDPullRefreshAndLoadMoreTestFragment.class
        })
@DefaultFirstFragment(HomeFragment.class)
@LatestVisitRecord
public class QDMainActivity extends QMUIFragmentActivity {

    private QMUIPopup mGlobalAction;

    @Override
    protected QMUIFragmentActivity.RootView onCreateRootView(int fragmentContainerId) {
        return new CustomRootView(this, fragmentContainerId);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void showGlobalActionPopup(View v) {
        String[] listItems = new String[]{
                "Change Skin",
                QDApplication.openSkinMake ? "Close SkinMaker(Developing)" : "Open SkinMaker(Developing)",
                "Export SkinMaker Result"
        };
        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    KLog.e("点击皮肤肤色");
                    final String[] items = new String[]{"蓝色（默认）", "黑色", "白色"};
                    new QMUIDialog.MenuDialogBuilder(QDMainActivity.this)
                            .addItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                } else if (i == 1) {
                    KLog.e("小菜单item2");
                    Toast.makeText(QDMainActivity.this, "小菜单item2", Toast.LENGTH_LONG);
                } else if (i == 2) {
                    KLog.e("小菜单item3");
                    Toast.makeText(QDMainActivity.this, "小菜单item3", Toast.LENGTH_LONG);
                }
                if (mGlobalAction != null) {
                    mGlobalAction.dismiss();
                }
            }
        };
        mGlobalAction = QMUIPopups.listPopup(this,
                QMUIDisplayHelper.dp2px(this, 250),
                QMUIDisplayHelper.dp2px(this, 300),
                adapter,
                onItemClickListener)
                .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
                .preferredDirection(QMUIPopup.DIRECTION_TOP)
                .shadow(true)
                .edgeProtection(QMUIDisplayHelper.dp2px(this, 10))
                .offsetYIfTop(QMUIDisplayHelper.dp2px(this, 5))
                .show(v);
    }

    @Override
    protected int getContextViewId() {
        return R.id.listrefreshtest;
    }


    public static Intent createWebExplorerIntent(Context context, String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_URL, url);
        bundle.putString(EXTRA_TITLE, title);
        return of(context, QDWebExplorerFragment.class, bundle);
    }

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment) {
        return QMUIFragmentActivity.intentOf(context, QDMainActivity.class, firstFragment);
    }

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment,
                            @Nullable Bundle fragmentArgs) {
        return QMUIFragmentActivity.intentOf(context, QDMainActivity.class, firstFragment, fragmentArgs);
    }

    class CustomRootView extends QMUIFragmentActivity.RootView {

        private QMUIWindowInsetLayout fragmentContainer;
        private QMUIRadiusImageView2 globalBtn;
        private QMUIViewOffsetHelper globalBtnOffsetHelper;
        private int btnSize;
        private final int touchSlop;
        private float touchDownX = 0;
        private float touchDownY = 0;
        private float lastTouchX = 0;
        private float lastTouchY = 0;
        private boolean isDragging;
        private boolean isTouchDownInGlobalBtn = false;

        public CustomRootView(Context context, int fragmentContainerId) {
            super(context);

            btnSize = QMUIDisplayHelper.dp2px(context, 56);

            fragmentContainer = new QMUIWindowInsetLayout(context);
            fragmentContainer.setId(fragmentContainerId);
            addView(fragmentContainer, new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


            globalBtn = new QMUIRadiusImageView2(context);
            globalBtn.setImageResource(R.mipmap.icon_theme);
            globalBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            globalBtn.setRadiusAndShadow(btnSize / 2,
                    QMUIDisplayHelper.dp2px(getContext(), 16), 0.4f);
            globalBtn.setBorderWidth(1);
            globalBtn.setBorderColor(QMUIResHelper.getAttrColor(context, R.attr.qmui_skin_support_color_separator));
            globalBtn.setBackgroundColor(QMUIResHelper.getAttrColor(context, R.attr.app_skin_common_background));
            globalBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showGlobalActionPopup(v);
                }
            });
            LayoutParams globalBtnLp = new LayoutParams(btnSize, btnSize);
            globalBtnLp.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            globalBtnLp.bottomMargin = QMUIDisplayHelper.dp2px(context, 60);
            globalBtnLp.rightMargin = QMUIDisplayHelper.dp2px(context, 24);
            QMUISkinValueBuilder builder = QMUISkinValueBuilder.acquire();
            builder.background(R.attr.app_skin_common_background);
            builder.border(R.attr.qmui_skin_support_color_separator);
            builder.tintColor(R.attr.app_skin_common_img_tint_color);
            QMUISkinHelper.setSkinValue(globalBtn, builder);
            builder.release();
            addView(globalBtn, globalBtnLp);
            globalBtnOffsetHelper = new QMUIViewOffsetHelper(globalBtn);
            touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            globalBtnOffsetHelper.onViewLayout();
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent event) {
            float x = event.getX(), y = event.getY();
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                isTouchDownInGlobalBtn = isDownInGlobalBtn(x, y);
                touchDownX = lastTouchX = x;
                touchDownY = lastTouchY = y;
            } else if (action == MotionEvent.ACTION_MOVE) {
                if (!isDragging && isTouchDownInGlobalBtn) {
                    int dx = (int) (x - touchDownX);
                    int dy = (int) (y - touchDownY);
                    if (Math.sqrt(dx * dx + dy * dy) > touchSlop) {
                        isDragging = true;
                    }
                }

                if (isDragging) {
                    int dx = (int) (x - lastTouchX);
                    int dy = (int) (y - lastTouchY);
                    int gx = globalBtn.getLeft();
                    int gy = globalBtn.getTop();
                    int gw = globalBtn.getWidth(), w = getWidth();
                    int gh = globalBtn.getHeight(), h = getHeight();
                    if (gx + dx < 0) {
                        dx = -gx;
                    } else if (gx + dx + gw > w) {
                        dx = w - gw - gx;
                    }

                    if (gy + dy < 0) {
                        dy = -gy;
                    } else if (gy + dy + gh > h) {
                        dy = h - gh - gy;
                    }
                    globalBtnOffsetHelper.setLeftAndRightOffset(
                            globalBtnOffsetHelper.getLeftAndRightOffset() + dx);
                    globalBtnOffsetHelper.setTopAndBottomOffset(
                            globalBtnOffsetHelper.getTopAndBottomOffset() + dy);
                }
                lastTouchX = x;
                lastTouchY = y;
            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                isDragging = false;
                isTouchDownInGlobalBtn = false;
            }
            return isDragging;
        }

        private boolean isDownInGlobalBtn(float x, float y) {
            return globalBtn.getLeft() < x && globalBtn.getRight() > x &&
                    globalBtn.getTop() < y && globalBtn.getBottom() > y;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(), y = event.getY();
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                isTouchDownInGlobalBtn = isDownInGlobalBtn(x, y);
                touchDownX = lastTouchX = x;
                touchDownY = lastTouchY = y;
            } else if (action == MotionEvent.ACTION_MOVE) {
                if (!isDragging && isTouchDownInGlobalBtn) {
                    int dx = (int) (x - touchDownX);
                    int dy = (int) (y - touchDownY);
                    if (Math.sqrt(dx * dx + dy * dy) > touchSlop) {
                        isDragging = true;
                    }
                }

                if (isDragging) {
                    int dx = (int) (x - lastTouchX);
                    int dy = (int) (y - lastTouchY);
                    int gx = globalBtn.getLeft();
                    int gy = globalBtn.getTop();
                    int gw = globalBtn.getWidth(), w = getWidth();
                    int gh = globalBtn.getHeight(), h = getHeight();
                    if (gx + dx < 0) {
                        dx = -gx;
                    } else if (gx + dx + gw > w) {
                        dx = w - gw - gx;
                    }

                    if (gy + dy < 0) {
                        dy = -gy;
                    } else if (gy + dy + gh > h) {
                        dy = h - gh - gy;
                    }
                    globalBtnOffsetHelper.setLeftAndRightOffset(
                            globalBtnOffsetHelper.getLeftAndRightOffset() + dx);
                    globalBtnOffsetHelper.setTopAndBottomOffset(
                            globalBtnOffsetHelper.getTopAndBottomOffset() + dy);
                }
                lastTouchX = x;
                lastTouchY = y;
            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                isDragging = false;
                isTouchDownInGlobalBtn = false;
            }
            return isDragging || super.onTouchEvent(event);
        }
    }
}
