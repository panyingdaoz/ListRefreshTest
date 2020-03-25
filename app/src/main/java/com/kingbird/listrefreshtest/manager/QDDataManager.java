package com.kingbird.listrefreshtest.manager;

import com.kingbird.listrefreshtest.base.BaseFragment;
import com.kingbird.listrefreshtest.fragment.helper.QDNotchHelperFragment;
import com.kingbird.listrefreshtest.fragment.components.QDPullFragment;
import com.kingbird.listrefreshtest.fragment.lab.QDAnimationListViewFragment;
import com.kingbird.listrefreshtest.fragment.lab.QDContinuousNestedScrollFragment;
import com.kingbird.listrefreshtest.fragment.section.QDSectionLayoutFragment;
import com.kingbird.listrefreshtest.model.QDItemDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cginechen
 * @date 2016-10-21
 */

public class QDDataManager {
    private static QDDataManager sInstance;
    private QDWidgetContainer mWidgetContainer;

    private List<Class<? extends BaseFragment>> mComponentsNames;
    private List<Class<? extends BaseFragment>> mUtilNames;
    private List<Class<? extends BaseFragment>> mLabNames;

    public QDDataManager() {
        mWidgetContainer = QDWidgetContainer.getInstance();
        initComponentsDesc();
        initUtilDesc();
        initLabDesc();
    }

    public static QDDataManager getInstance() {
        if (sInstance == null) {
            sInstance = new QDDataManager();
        }
        return sInstance;
    }


    /**
     * Components
     */
    private void initComponentsDesc() {
        mComponentsNames = new ArrayList<>();
        mComponentsNames.add(QDSectionLayoutFragment.class);
        mComponentsNames.add(QDContinuousNestedScrollFragment.class);
        mComponentsNames.add(QDPullFragment.class);
    }

    /**
     * Helper
     */
    private void initUtilDesc() {
        mUtilNames = new ArrayList<>();
        mUtilNames.add(QDNotchHelperFragment.class);
    }

    /**
     * Lab
     */
    private void initLabDesc() {
        mLabNames = new ArrayList<>();
        mLabNames.add(QDAnimationListViewFragment.class);
    }

    public QDItemDescription getDescription(Class<? extends BaseFragment> cls) {
        return mWidgetContainer.get(cls);
    }

    public String getName(Class<? extends BaseFragment> cls) {
        QDItemDescription itemDescription = getDescription(cls);
        if (itemDescription == null) {
            return null;
        }
        return itemDescription.getName();
    }

    public String getDocUrl(Class<? extends BaseFragment> cls) {
        QDItemDescription itemDescription = getDescription(cls);
        if (itemDescription == null) {
            return null;
        }
        return itemDescription.getDocUrl();
    }

    public List<QDItemDescription> getComponentsDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        for (int i = 0; i < mComponentsNames.size(); i++) {
            list.add(mWidgetContainer.get(mComponentsNames.get(i)));
        }
        return list;
    }

    public List<QDItemDescription> getUtilDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        for (int i = 0; i < mUtilNames.size(); i++) {
            list.add(mWidgetContainer.get(mUtilNames.get(i)));
        }
        return list;
    }

    public List<QDItemDescription> getLabDescriptions() {
        List<QDItemDescription> list = new ArrayList<>();
        for (int i = 0; i < mLabNames.size(); i++) {
            list.add(mWidgetContainer.get(mLabNames.get(i)));
        }
        return list;
    }
}
