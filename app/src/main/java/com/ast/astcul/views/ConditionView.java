package com.ast.astcul.views;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ast.astcul.R;

import java.text.DecimalFormat;

public class ConditionView extends LinearLayout {
    public ConditionView(Context context) {
        this(context,null);
    }


    public ConditionView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ConditionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 读取配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ConditionView);
        concatText = array.getString(R.styleable.ConditionView_concatText);
        numText = array.getString(R.styleable.ConditionView_numText);
        inputEnable=array.getBoolean(R.styleable.ConditionView_enableInput,true);

        array.recycle();
        initViews(context);
    }


    TextView concatTextView;
    EditText numInputView;
    String concatText;
    String numText;
    boolean inputEnable;


    private void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_condition, this, true);
        numInputView=view.findViewById(R.id.et_input);
        concatTextView=view.findViewById(R.id.tv_concat);
        concatTextView.setText(concatText);
        numInputView.setText(numText);
        numInputView.setEnabled(inputEnable);
    }

    public int getConditionInt(){
        String result=numInputView.getText().toString();
        if(result.equals("")){
            return 0;
        }
        return Integer.parseInt(result);
    }
    public void setConditionInt(int num){
        numInputView.setText(String.valueOf(num));
    }
    public void setConditionFloat(float num){
        DecimalFormat fnum = new DecimalFormat("#0.0");
        numInputView.setText(fnum.format(num));
    }
    public void setTextWatcher(TextWatcher textWatcher){
        numInputView.addTextChangedListener(textWatcher);
    }
}
