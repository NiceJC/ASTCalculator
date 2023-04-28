package com.ast.astcul.beans;

import java.util.List;

public class LevelBean {

    //档位名称
    private String levelName;
    //总共花费的金币
    private int totalGold;
    //总共的进度
    private int totalProgress;
    //总共获得的活动币
    private int totalCoin;
    //step记录
    private List<StepBean> stepListRecord;

    @Override
    public String toString() {
        return "{" +
                levelName + '：' +
                ", 总金币=" + totalGold +
                ", 总进度=" + totalProgress +
                ", 总活动币=" + totalCoin +
                '}';
    }


    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int getTotalProgress() {
        return totalProgress;
    }

    public void setTotalProgress(int totalProgress) {
        this.totalProgress = totalProgress;
    }

    public int getTotalCoin() {
        return totalCoin;
    }

    public void setTotalCoin(int totalCoin) {
        this.totalCoin = totalCoin;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public List<StepBean> getStepListRecord() {
        return stepListRecord;
    }

    public void setStepListRecord(List<StepBean> stepListRecord) {
        this.stepListRecord = stepListRecord;
    }
}
