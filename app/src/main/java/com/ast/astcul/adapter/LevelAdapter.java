package com.ast.astcul.adapter;

import android.widget.TextView;

import com.ast.astcul.R;
import com.ast.astcul.beans.LevelBean;
import com.ast.astcul.views.ConditionView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class LevelAdapter extends BaseQuickAdapter<LevelBean, BaseViewHolder> {
    public LevelAdapter() {
        super(R.layout.item_result);
    }

    @Override
    protected void convert(BaseViewHolder helper, LevelBean item) {
        TextView tvLevelName = helper.getView(R.id.tv_level_name);
        tvLevelName.setText(item.getLevelName());
        ConditionView cdv1 = helper.getView(R.id.cdv_level_progress);
        cdv1.setConditionInt(item.getTotalProgress());
        ConditionView cdv2 = helper.getView(R.id.cdv_level_coin);
        cdv2.setConditionInt(item.getTotalCoin());
        ConditionView cdv3 = helper.getView(R.id.cdv_level_gold);
        cdv3.setConditionInt(item.getTotalGold());
        ConditionView cdv4 = helper.getView(R.id.cdv_level_gold_per_coin);
        cdv4.setConditionFloat((float) item.getTotalGold()/item.getTotalCoin());
    }

}
