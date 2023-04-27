package com.ast.astcul.adapter;

import android.widget.TextView;

import com.ast.astcul.R;
import com.ast.astcul.beans.ConditionRuleBean;
import com.ast.astcul.views.ConditionView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;



public class ConditionRuleAdapter extends BaseQuickAdapter<ConditionRuleBean, BaseViewHolder> {
    public ConditionRuleAdapter() {
        super(R.layout.item_condition);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConditionRuleBean item) {
        TextView tvConditionName = helper.getView(R.id.tv_cd_name);
        tvConditionName.setText(item.getConditionName());
        ConditionView cdv1 = helper.getView(R.id.cdv_free_step);
        cdv1.setConditionInt(item.getFreeStepDaily());
        ConditionView cdv2 = helper.getView(R.id.cdv_gold_increase_step);
        cdv2.setConditionInt(item.getGoldIncreaseStep());
        ConditionView cdv3 = helper.getView(R.id.cdv_gold_increase_size);
        cdv3.setConditionInt(item.getGoldIncreaseSize());
        ConditionView cdv4 = helper.getView(R.id.cdv_progress_increase_step);
        cdv4.setConditionInt(item.getProgressIncreaseStep());
        ConditionView cdv5 = helper.getView(R.id.cdv_progress_increase_size);
        cdv5.setConditionInt(item.getProgressIncreaseSize());
        ConditionView cdv6 = helper.getView(R.id.cdv_coin_get_step);
        cdv6.setConditionInt(item.getCoinGetStep());
        ConditionView cdv7 = helper.getView(R.id.cdv_coin_get_size);
        cdv7.setConditionInt(item.getCoinGetSize());
        ConditionView cdv8 = helper.getView(R.id.cdv_gold_start_size);
        cdv8.setConditionInt(item.getStartGoldSize());
        ConditionView cdv9 = helper.getView(R.id.cdv_gold_max_size);
        cdv9.setConditionInt(item.getMaxGoldSize());
    }

}
