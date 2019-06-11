package com.hxd.traceabilitysystem.adapter.CardSourceItem;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hjq.image.ImageLoader;
import com.hxd.traceabilitysystem.R;
import com.hxd.traceabilitysystem.bean.ProductionProcessBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：    CharitySystem
 * 包名：      com.hxd.charitysystem.item_card_home.CardItemAdapter
 * 文件名:     CardPagerAdapter
 * 创建者:     HXD
 * 创建时间:   2019/4/29 10:22
 * 描述:       TODO
 */


public class CardPagerAdapter extends PagerAdapter implements HomeCardAdapter {

    private List<CardView> mViews;
    private List<ProductionProcessBean.ProductionProcess> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(ProductionProcessBean.ProductionProcess item) {
        mViews.add(null);
        mData.add(item);
    }

    public void setInitData() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }


    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_source, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(final ProductionProcessBean.ProductionProcess item, View view) {
        ImageView image = view.findViewById(R.id.tv_image_itemSource);
        TextView title = view.findViewById(R.id.tv_title_itemSource);
        TextView place = view.findViewById(R.id.tv_place_itemSource);
        TextView productionTime = view.findViewById(R.id.tv_productionTime_itemSource);
        TextView function = view.findViewById(R.id.tv_function_itemSource);
        if (!item.getImage().equals("")) {
//            ImageLoader.loadImage(image, item.getImage());
            Glide.with(image.getContext()).load(item.getImage()).into(image);
        }
        title.setText(item.getName());
        place.setText(item.getPlace());
        productionTime.setText(item.getProductionTime());
        function.setText(item.getFunction());
    }

}
