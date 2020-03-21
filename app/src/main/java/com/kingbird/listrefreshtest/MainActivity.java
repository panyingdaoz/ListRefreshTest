package com.kingbird.listrefreshtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kingbird.listrefreshtest.fragment.BaseFragment;
import com.kingbird.listrefreshtest.fragment.QDNotchHelperFragment;
import com.kingbird.listrefreshtest.fragment.QDPullHorizontalTestFragment;
import com.kingbird.listrefreshtest.fragment.QDPullRefreshAndLoadMoreTestFragment;
import com.kingbird.listrefreshtest.fragment.QDPullVerticalTestFragment;
import com.kingbird.listrefreshtest.fragment.QDWebExplorerFragment;
import com.kingbird.listrefreshtest.fragment.home.HomeFragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScroll1Fragment;
import com.kingbird.listrefreshtest.manager.QDSkinManager;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.arch.annotation.DefaultFirstFragment;
import com.qmuiteam.qmui.arch.annotation.FirstFragments;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinMaker;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.util.QMUIViewOffsetHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kingbird.listrefreshtest.fragment.QDWebExplorerFragment.EXTRA_TITLE;
import static com.kingbird.listrefreshtest.fragment.QDWebExplorerFragment.EXTRA_URL;

/**
 * @ClassName: MainActivity
 * @Description: java类作用描述
 * @Author: Pan
 * @CreateDate: 2020/3/19 16:38:49
 */
@FirstFragments(
        value = {
                HomeFragment.class,
//                QDArchTestFragment.class,
//                QDArchSurfaceTestFragment.class,
                QDNotchHelperFragment.class,
                QDWebExplorerFragment.class,
                QDContinuousNestedScroll1Fragment.class,
//                QDTabSegmentFixModeFragment.class,
                QDPullVerticalTestFragment.class,
                QDPullHorizontalTestFragment.class,
                QDPullRefreshAndLoadMoreTestFragment.class
//                QDRVSwipeMutiActionFragment.class,
//                QDPopupFragment.class
        })
//@DefaultFirstFragment(HomeFragment.class)
@LatestVisitRecord
public class MainActivity extends QMUIFragmentActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    private QMUIPopup mGlobalAction;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    private QMUISkinManager.OnSkinChangeListener mOnSkinChangeListener = new QMUISkinManager.OnSkinChangeListener() {
        @Override
        public void onSkinChange(int oldSkin, int newSkin) {
            if (newSkin == QDSkinManager.SKIN_WHITE) {
                QMUIStatusBarHelper.setStatusBarLightMode(MainActivity.this);
            } else {
                QMUIStatusBarHelper.setStatusBarDarkMode(MainActivity.this);
            }
        }
    };

    @Override
    protected int getContextViewId() {
        KLog.e("id：" + R.id.qmuidemo);
        return R.id.qmuidemo;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ButterKnife.bind(this);
//        initTopBar();
//    }

    @Override
    protected RootView onCreateRootView(int fragmentContainerId) {
        KLog.e("fragmentContainerId："+fragmentContainerId);
        return new CustomRootView(this, fragmentContainerId);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        QMUISkinManager.defaultInstance(this).addSkinChangeListener(mOnSkinChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        KLog.e("执行 renderSkinMakerBtn");
        renderSkinMakerBtn();
    }

    @Override
    protected void onStop() {
        super.onStop();
        QMUISkinManager.defaultInstance(this).removeSkinChangeListener(mOnSkinChangeListener);
    }

    private void renderSkinMakerBtn() {
        Fragment baseFragment = getCurrentFragment();
        if (baseFragment instanceof BaseFragment) {
            if (MyApplication.openSkinMake) {
                ((BaseFragment) baseFragment).openSkinMaker();
            } else {
                QMUISkinMaker.getInstance().unBindAll();
            }
        }
    }

    private void initTopBar() {
        mTopBar.setTitle("列表刷新").setTextColor(ContextCompat.getColor(this, R.color.qmui_config_color_white));
        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.e("点击Topbar");
//                replaceFragment(new QDPullFragment());
            }
        });
    }

    //Fragment启动方法
    private void replaceFragment(Fragment fragment) {
        // 1.获取FragmentManager，在活动中可以直接通过调用getFragmentManager()方法得到
        fragmentManager = getSupportFragmentManager();
        // 2.开启一个事务，通过调用beginTransaction()方法开启
        transaction = fragmentManager.beginTransaction();
        // 3.向容器内添加或替换碎片，一般使用replace()方法实现，需要传入容器的id和待添加的碎片实例
        transaction.replace(R.id.fr_container, fragment);  //fr_container不能为fragment布局，可使用线性布局相对布局等。
        // 4.使用addToBackStack()方法，将事务添加到返回栈中，填入的是用于描述返回栈的一个名字
        transaction.addToBackStack(null);
        // 5.提交事物,调用commit()方法来完成
        transaction.commit();
    }

    public static Intent createWebExplorerIntent(Context context, String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_URL, url);
        bundle.putString(EXTRA_TITLE, title);
        return of(context, QDWebExplorerFragment.class, bundle);
    }

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment) {
        return QMUIFragmentActivity.intentOf(context, MainActivity.class, firstFragment);
    }

    public static Intent of(@NonNull Context context,
                            @NonNull Class<? extends QMUIFragment> firstFragment,
                            @Nullable Bundle fragmentArgs) {
        return QMUIFragmentActivity.intentOf(context, MainActivity.class, firstFragment, fragmentArgs);
    }

    private void showGlobalActionPopup(View v) {
        String[] listItems = new String[]{
                "Change Skin",
                MyApplication.openSkinMake ? "Close SkinMaker(Developing)" : "Open SkinMaker(Developing)",
                "Export SkinMaker Result"
        };
        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    final String[] items = new String[]{"蓝色（默认）", "黑色", "白色"};
                    new QMUIDialog.MenuDialogBuilder(MainActivity.this)
                            .addItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    QDSkinManager.changeSkin(which + 1);
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                } else if (i == 1) {
                    MyApplication.openSkinMake = !MyApplication.openSkinMake;
                    renderSkinMakerBtn();
                    KLog.e("点击item 2");
                } else if (i == 2) {
                    QMUISkinMaker.getInstance().export(MainActivity.this);
                    KLog.e("点击item 3");
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

    class CustomRootView extends RootView {

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

        @SuppressLint("RtlHardcoded")
        public CustomRootView(Context context, int fragmentContainerId) {
            super(context);

            btnSize = QMUIDisplayHelper.dp2px(context, 56);

            fragmentContainer = new QMUIWindowInsetLayout(context);
            fragmentContainer.setId(fragmentContainerId);
            addView(fragmentContainer, new FrameLayout.LayoutParams(
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
            FrameLayout.LayoutParams globalBtnLp = new FrameLayout.LayoutParams(btnSize, btnSize);
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
