package com.kingbird.listrefreshtest.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.kingbird.listrefreshtest.GridDividerItemDecoration;
import com.kingbird.listrefreshtest.QDMainActivity;
import com.kingbird.listrefreshtest.R;
import com.kingbird.listrefreshtest.base.BaseRecyclerAdapter;
import com.kingbird.listrefreshtest.base.RecyclerViewHolder;
import com.kingbird.listrefreshtest.base.BaseFragment;
import com.kingbird.listrefreshtest.fragment.QDAboutFragment;
import com.kingbird.listrefreshtest.fragment.helper.QDNotchHelperFragment;
import com.kingbird.listrefreshtest.model.QDItemDescription;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.QMUIWindowInsetLayout;
import com.socks.library.KLog;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kingbird.listrefreshtest.utils.Const.ANIMATION_LISTVIEW;
import static com.kingbird.listrefreshtest.utils.Const.NOTCH_HELPER;
import static com.kingbird.listrefreshtest.utils.Const.PULL_FRAGMENT;
import static com.kingbird.listrefreshtest.utils.Const.SECTION_LAYOUT;

/**
 * @author cginechen
 * @date 2016-10-20
 */

public abstract class HomeController extends QMUIWindowInsetLayout {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private HomeControlListener mHomeControlListener;
    private ItemAdapter mItemAdapter;
    private int mDiffRecyclerViewSaveStateId = QMUIViewHelper.generateViewId();

    public HomeController(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.home_layout, this);
        ButterKnife.bind(this);
        initTopBar();
        initRecyclerView();
    }

    protected void startFragment(BaseFragment fragment) {
        if (mHomeControlListener != null) {
            mHomeControlListener.startFragment(fragment);
        }
    }

    public void setHomeControlListener(HomeControlListener homeControlListener) {
        mHomeControlListener = homeControlListener;
    }

    protected abstract String getTitle();

    private void initTopBar() {
        KLog.e("topBar标题：" + getTitle());
        mTopBar.setTitle(getTitle());
//        mTopBar.setTitle(getTitle()).setTextColor(R.color.colorWhite);
//        mTopBar.setTitle("列表刷新").setTextColor(ContextCompat.getColor(this, R.color.qmui_config_color_white));
//        mTopBar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        mTopBar.addRightImageButton(R.mipmap.icon_topbar_about, R.id.topbar_right_about_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                QDAboutFragment fragment = new QDAboutFragment();
                startFragment(fragment);
            }
        });
    }

    private void initRecyclerView() {
        mItemAdapter = getItemAdapter();
        mItemAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                QDItemDescription item = mItemAdapter.getItem(pos);
                KLog.e("QDItemDescription:" + item);
                try {
                    BaseFragment fragment = item.getDemoClass().newInstance();
                    if (fragment instanceof QDNotchHelperFragment) {
                        Context context = getContext();
//                        Intent intent = MainActivity.of(context, QDNotchHelperFragment.class);
                        Intent intent = QDMainActivity.of(context, QDNotchHelperFragment.class);
                        context.startActivity(intent);
                        if (context instanceof Activity) {
                            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    } else {
                        startFragment(fragment);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mRecyclerView.setAdapter(mItemAdapter);
        int spanCount = 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), spanCount));
    }

    protected abstract ItemAdapter getItemAdapter();

    public interface HomeControlListener {
        void startFragment(BaseFragment fragment);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        int id = mRecyclerView.getId();
        mRecyclerView.setId(mDiffRecyclerViewSaveStateId);
        super.dispatchSaveInstanceState(container);
        mRecyclerView.setId(id);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        int id = mRecyclerView.getId();
        mRecyclerView.setId(mDiffRecyclerViewSaveStateId);
        super.dispatchRestoreInstanceState(container);
        mRecyclerView.setId(id);
    }

    static class ItemAdapter extends BaseRecyclerAdapter<QDItemDescription> {

        public ItemAdapter(Context ctx, List<QDItemDescription> data) {
            super(ctx, data);
        }

        @Override
        public int getItemLayoutId(int viewType) {
            return R.layout.home_item_layout;
        }

        @Override
        public void bindData(RecyclerViewHolder holder, int position, QDItemDescription item) {
            if (item != null) {
                holder.getTextView(R.id.item_name).setText(item.getName());
                if (item.getIconRes() != 0) {
                    int iconRes;
                    String str = item.getDemoClass().toString();
                    String strClass = str.substring(str.lastIndexOf(".") + 1);
                    KLog.e("截取后的数据：" + strClass);
//                holder.getImageView(R.id.item_icon).setImageResource(item.getIconRes());
                    switch (strClass) {
                        case NOTCH_HELPER:
                            iconRes = R.mipmap.icon_grid_status_bar_helper;
                            break;
                        case ANIMATION_LISTVIEW:
                            iconRes = R.mipmap.icon_grid_anim_list_view;
                            break;
                        case SECTION_LAYOUT:
                            iconRes = R.mipmap.icon_grid_sticky_section;
                            break;
                        case PULL_FRAGMENT:
                            iconRes = R.mipmap.icon_grid_pull_layout;
                            break;
                        default:
//                            iconRes = R.mipmap.icon_grid_status_bar_helper;
                            iconRes = item.getIconRes();
                            break;
                    }
                    holder.getImageView(R.id.item_icon).setImageResource(iconRes);
                }
            }
        }
    }
}
