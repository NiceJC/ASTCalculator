package com.ast.astcul.beans;

public class StepBean {
    //花费的金币
    private int goldSize;
    //提升的进度
    private int progressSize;
    //获得的活动币
    private int coinGet;
//    //获得的节点币
//    private int coinPoint;


    @Override
    public String toString() {
        return "点一下{" +
                "金币=" + goldSize +
                ", 进度=" + progressSize +
                ", 活动币=" + coinGet +
                '}';
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

//    public int getCoinPoint() {
//        return coinPoint;
//    }
//
//    public void setCoinPoint(int coinPoint) {
//        this.coinPoint = coinPoint;
//    }
}
