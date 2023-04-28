package com.ast.astcul.adapter;

import android.widget.TextView;

import com.ast.astcul.R;
import com.ast.astcul.beans.StepBean;
import com.ast.astcul.views.ConditionView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class OperationAdapter  extends BaseQuickAdapter<StepBean, BaseViewHolder> {

    public OperationAdapter() {
        super(R.layout.item_operation);
    }

    @Override
    protected void convert(BaseViewHolder helper, StepBean item) {
        TextView tvOperationName = helper.getView(R.id.tv_operation_name);
        tvOperationName.setText(item.getConditionName());
        ConditionView cdv1 = helper.getView(R.id.cdv_operation_gold);
        cdv1.setConditionInt(item.getGoldSize());
        ConditionView cdv2 = helper.getView(R.id.cdv_operation_count);
        cdv2.setConditionInt(item.getRepeatTime()+1);
    }

}
