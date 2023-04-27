package com.ast.astcul.beans;

public class ConditionStatusBean {

    //花金规则
    private ConditionRuleBean rule;
    //规则对应的步数
    private int currentStepIndex;


//    //当前的点金
//    private int currentGoldSize;
//    //当前的点金步数（小于递增step）
//    private int currentGoldStep;
//    //当前的进度
//    private int currentProgress;

    public ConditionRuleBean getRule() {
        return rule;
    }

    public void setRule(ConditionRuleBean rule) {
        this.rule = rule;
    }

    public int getCurrentStepIndex() {
        return currentStepIndex;
    }

    public void setCurrentStepIndex(int currentStepIndex) {
        this.currentStepIndex = currentStepIndex;
    }

    //    public int getCurrentGoldSize() {
//        return currentGoldSize;
//    }
//
//    public void setCurrentGoldSize(int currentGoldSize) {
//        this.currentGoldSize = currentGoldSize;
//    }
//
//    public int getCurrentGoldStep() {
//        return currentGoldStep;
//    }
//
//    public void setCurrentGoldStep(int currentGoldStep) {
//        this.currentGoldStep = currentGoldStep;
//    }
//
//    public int getCurrentProgress() {
//        return currentProgress;
//    }
//
//    public void setCurrentProgress(int currentProgress) {
//        this.currentProgress = currentProgress;
//    }
}
