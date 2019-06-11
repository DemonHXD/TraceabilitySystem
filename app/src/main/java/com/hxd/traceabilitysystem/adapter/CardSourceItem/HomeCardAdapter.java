package com.hxd.traceabilitysystem.adapter.CardSourceItem;

/**
 * 项目名：    CharitySystem
 * 包名：      com.hxd.charitysystem.item_card_home.CardItemAdapter
 * 文件名:     HomeCardAdapter
 * 创建者:     HXD
 * 创建时间:   2019/4/29 10:25
 * 描述:       TODO
 */

import android.support.v7.widget.CardView;

public interface HomeCardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}

