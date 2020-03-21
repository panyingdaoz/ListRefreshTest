package com.kingbird.listrefreshtest.fragment.home;

import android.content.Context;

import com.kingbird.listrefreshtest.manager.QDDataManager;

/**
 * @author cginechen
 * @date 2016-10-20
 */
public class HomeComponentsController extends HomeController {

    public HomeComponentsController(Context context) {
        super(context);
    }

    @Override
    protected String getTitle() {
        return "Components";
    }

    @Override
    protected ItemAdapter getItemAdapter() {
        return new ItemAdapter(getContext(), QDDataManager.getInstance().getComponentsDescriptions());
    }
}
