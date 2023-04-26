package com.ast.astcul.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ConditionBean implements Parcelable {

    //step名称
    private String conditionName;
    //每日赠送次数
    private int freeStepDaily;
    //金币初始值
    private int startGoldSize;
    //金币最大值
    private int maxGoldSize;
    //金币每递增所需步数
    private int goldIncreaseStep;
    //金币每递增数值
    private int goldIncreaseSize;
    //进度每递增所需步数
    private int progressIncreaseStep;
    //进度每递增数值
    private int progressIncreaseSize;
    //活动币每获取所需步数
    private int coinGetStep;
    //活动币每获取数值
    private int coinGetSize;

    protected ConditionBean(Parcel in) {
        conditionName = in.readString();
        freeStepDaily = in.readInt();
        startGoldSize = in.readInt();
        maxGoldSize = in.readInt();
        goldIncreaseStep = in.readInt();
        goldIncreaseSize = in.readInt();
        progressIncreaseStep = in.readInt();
        progressIncreaseSize = in.readInt();
        coinGetStep = in.readInt();
        coinGetSize = in.readInt();
    }

    public ConditionBean() {
    }

    public static final Creator<ConditionBean> CREATOR = new Creator<ConditionBean>() {
        @Override
        public ConditionBean createFromParcel(Parcel in) {
            return new ConditionBean(in);
        }

        @Override
        public ConditionBean[] newArray(int size) {
            return new ConditionBean[size];
        }
    };

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public int getFreeStepDaily() {
        return freeStepDaily;
    }

    public void setFreeStepDaily(int freeStepDaily) {
        this.freeStepDaily = freeStepDaily;
    }

    public int getStartGoldSize() {
        return startGoldSize;
    }

    public void setStartGoldSize(int startGoldSize) {
        this.startGoldSize = startGoldSize;
    }

    public int getMaxGoldSize() {
        return maxGoldSize;
    }

    public void setMaxGoldSize(int maxGoldSize) {
        this.maxGoldSize = maxGoldSize;
    }

    public int getGoldIncreaseStep() {
        return goldIncreaseStep;
    }

    public void setGoldIncreaseStep(int goldIncreaseStep) {
        this.goldIncreaseStep = goldIncreaseStep;
    }

    public int getGoldIncreaseSize() {
        return goldIncreaseSize;
    }

    public void setGoldIncreaseSize(int goldIncreaseSize) {
        this.goldIncreaseSize = goldIncreaseSize;
    }

    public int getProgressIncreaseStep() {
        return progressIncreaseStep;
    }

    public void setProgressIncreaseStep(int progressIncreaseStep) {
        this.progressIncreaseStep = progressIncreaseStep;
    }

    public int getProgressIncreaseSize() {
        return progressIncreaseSize;
    }

    public void setProgressIncreaseSize(int progressIncreaseSize) {
        this.progressIncreaseSize = progressIncreaseSize;
    }

    public int getCoinGetStep() {
        return coinGetStep;
    }

    public void setCoinGetStep(int coinGetStep) {
        this.coinGetStep = coinGetStep;
    }

    public int getCoinGetSize() {
        return coinGetSize;
    }

    public void setCoinGetSize(int coinGetSize) {
        this.coinGetSize = coinGetSize;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(conditionName);
        dest.writeInt(freeStepDaily);
        dest.writeInt(startGoldSize);
        dest.writeInt(maxGoldSize);
        dest.writeInt(goldIncreaseStep);
        dest.writeInt(goldIncreaseSize);
        dest.writeInt(progressIncreaseStep);
        dest.writeInt(progressIncreaseSize);
        dest.writeInt(coinGetStep);
        dest.writeInt(coinGetSize);
    }

}
