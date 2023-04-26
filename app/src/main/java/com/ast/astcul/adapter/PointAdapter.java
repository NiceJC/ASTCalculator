package com.ast.astcul.adapter;

import android.widget.TextView;

import com.ast.astcul.R;
import com.ast.astcul.beans.PointBean;
import com.ast.astcul.views.ConditionView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class PointAdapter extends BaseQuickAdapter<PointBean, BaseViewHolder> {
    public PointAdapter() {
        super(R.layout.item_point);
    }

    @Override
    protected void convert(BaseViewHolder helper, PointBean item) {
        TextView tvPointName = helper.getView(R.id.tv_point_name);
        tvPointName.setText(item.getPointName());
        ConditionView cdv1 = helper.getView(R.id.cdv_point_progress);
        cdv1.setConditionInt(item.getPointProgress());
        ConditionView cdv2 = helper.getView(R.id.cdv_coin_reward);
        cdv2.setConditionInt(item.getRewardCoin());
    }

}
