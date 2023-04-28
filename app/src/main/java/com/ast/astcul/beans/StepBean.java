package com.ast.astcul.beans;

public class StepBean {
    private String conditionName;
    //花费的金币
    private int goldSize;
    //提升的进度
    private int progressSize;
    //获得的活动币
    private int coinGet;
    //相同金额点击次数
    private int repeatTime;


    @Override
    public String toString() {
        return  "{"+conditionName+"  "+
                "金币=" + goldSize +
                "重复=" + repeatTime+
                ", 进度=" + progressSize +
                ", 活动币=" + coinGet +
                '}';
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public int getGoldSize() {
        return goldSize;
    }

    public void setGoldSize(int goldSize) {
        this.goldSize = goldSize;
    }

    public int getProgressSize() {
        return progressSize;
    }

    public void setProgressSize(int progressSize) {
        this.progressSize = progressSize;
    }

    public int getCoinGet() {
        return coinGet;
    }

    public void setCoinGet(int coinGet) {
        this.coinGet = coinGet;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }
}
