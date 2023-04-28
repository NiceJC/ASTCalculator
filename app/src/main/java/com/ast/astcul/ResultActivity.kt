package com.ast.astcul

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ast.astcul.adapter.LevelAdapter
import com.ast.astcul.adapter.OperationAdapter
import com.ast.astcul.beans.ConditionRuleBean
import com.ast.astcul.beans.ConditionStatusBean
import com.ast.astcul.beans.PointBean
import com.ast.astcul.beans.LevelBean
import com.ast.astcul.beans.StepBean
import com.ast.astcul.views.ConditionView

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initData()
        initView()
        startCalculate()
    }

    private val TAG = "calculate"

    private val resultAdapter by lazy {
        LevelAdapter()
    }
    private val operationAdapter by lazy {
        OperationAdapter()
    }

    private val stepList = mutableListOf<StepBean>()
    private val levelList = mutableListOf<LevelBean>()

    //金币条件
    private val conditionStatusList = mutableListOf<ConditionStatusBean>()

    //进度条件
    private lateinit var pointList: List<PointBean>

    //目标活动币
    private var targetCoin: Int = 0

    //活动天数
    private var date: Int = 0

    private fun initData() {
        val conditionList: List<ConditionRuleBean> =
            intent.getParcelableArrayListExtra("condition") ?: listOf()
        conditionList.forEachIndexed { index, conditionRuleBean ->
            conditionStatusList.add(ConditionStatusBean().apply {
                rule = conditionRuleBean
                currentStepIndex = 0
            })
        }
        pointList = intent.getParcelableArrayListExtra("point") ?: listOf()
        targetCoin = intent.getIntExtra("target", 0)
        date = intent.getIntExtra("date", 0)
    }

    private fun initView() {
        findViewById<View>(R.id.ll_level_detail).visibility = View.GONE
        findViewById<RecyclerView>(R.id.recyclerview_results).adapter = resultAdapter
        findViewById<RecyclerView>(R.id.recyclerview_operation).adapter = operationAdapter

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        resultAdapter.setOnLevelClick {
            showLevelDetail(it)
        }
        findViewById<RecyclerView>(R.id.recyclerview_results).adapter = resultAdapter

        operationAdapter
    }

    //当前单日进度
    private var totalProgressPerDay: Int = 0
    //当前单日币
    private var totalCoinPerDay: Int = 0

    private fun startCalculate() {
        calculateForTargetProgress(0)
        pointList.forEachIndexed { index, pointBean ->
//            if(index==3)
            calculateForTargetProgress(pointBean.pointProgress)
        }
        calculateForTargetCoin()
        resultAdapter.setNewData(levelList)
    }

    //计算目标进度（以进度为准）
    private fun calculateForTargetProgress(targetProgress: Int) {
        //使用免费次数
        conditionStatusList.forEachIndexed { index, condition ->
            for (i in 1..condition.rule.freeStepDaily) {
                clickStep(condition)
            }
        }
        if (targetProgress != 0) {
            var targetProgressPerDay = targetProgress / date
            if (targetProgress % date > 0) {
                targetProgressPerDay++
            }
            while (totalProgressPerDay < targetProgressPerDay) {
                //遍历所有路线 计算成本 选成本低的
                var bestIndex = 0
                var bestPrice = 0f
                conditionStatusList.forEachIndexed { index, conditionStatusBean ->
                    var goldSize = 0
                    var progressSize = 0
                    conditionStatusBean.run {
                        if (currentStepIndex + 1 < rule.freeStepDaily) {
                            goldSize = 0
                        } else {
                            val multiple =
                                (currentStepIndex - rule.freeStepDaily) / rule.goldIncreaseStep

                            goldSize = rule.goldIncreaseSize * multiple + rule.startGoldSize
                        }
                        if (currentStepIndex + 1 >= rule.progressIncreaseStep && (currentStepIndex + 1) % rule.progressIncreaseStep == 0) {
                            progressSize = rule.progressIncreaseSize
                        }
                        if (progressSize > targetProgressPerDay - totalProgressPerDay) {
                            progressSize = targetProgressPerDay - totalProgressPerDay
                        }
                    }
                    if (bestPrice == 0f || bestPrice > (goldSize.toFloat() / progressSize)) {
                        bestPrice = goldSize.toFloat() / progressSize
                        bestIndex = index
                    }
                }
                clickStep(conditionStatusList[bestIndex])
            }
        }
        generateLevel()
        clearCalculateInfo()
    }

    //以目标币数 计算
    private fun calculateForTargetCoin() {
        if(levelList.isNullOrEmpty()){
            Toast.makeText(this, "错了错了", Toast.LENGTH_SHORT).show()
            return
        }
        if(targetCoin>levelList.last().totalCoin){
            Toast.makeText(this, "暂不支持拉爆计算", Toast.LENGTH_SHORT).show()
            return
        }
        if(targetCoin<=levelList.first().totalCoin){
            Toast.makeText(this, "白嫖你也要算？", Toast.LENGTH_SHORT).show()
            return
        }
        var baseLevelIndex=0
        levelList.forEachIndexed { index, levelBean ->
            if(targetCoin>=levelBean.totalCoin){
                baseLevelIndex=index
            }
        }
        for (i in 0 until  baseLevelIndex){
            targetCoin-=pointList[i].rewardCoin
        }
        var targetCoinPerDay=targetCoin/date
        var targetCoinFinalLack=targetCoin-(targetCoinPerDay*date)
        if(targetCoinPerDay==0){
            Toast.makeText(this, "请直接参照档位", Toast.LENGTH_SHORT).show()
            return
        }
        //使用免费次数
        conditionStatusList.forEachIndexed { index, condition ->
            for (i in 1..condition.rule.freeStepDaily) {
                clickStep(condition)
            }
        }

        while (totalCoinPerDay < targetCoinPerDay) {
            //遍历所有路线 计算成本 选成本低的
            var bestIndex = 0
            var bestPrice = 0f
            conditionStatusList.forEachIndexed { index, conditionStatusBean ->
                var goldSize = 0
                var coinGet=0f
                conditionStatusBean.run {
                    if (currentStepIndex + 1 < rule.freeStepDaily) {
                        goldSize = 0
                    } else {
                        val multiple =
                            (currentStepIndex - rule.freeStepDaily) / rule.goldIncreaseStep

                        goldSize = rule.goldIncreaseSize * multiple + rule.startGoldSize
                    }
                    coinGet=rule.coinGetSize.toFloat()/rule.coinGetStep
                    if (coinGet > targetCoinPerDay - totalCoinPerDay) {
                        coinGet = (targetCoinPerDay - totalCoinPerDay.toFloat())
                    }
                }
                if (bestPrice == 0f || bestPrice > (goldSize.toFloat() / coinGet)) {
                    bestPrice = goldSize.toFloat() / coinGet
                    bestIndex = index
                }
            }
            clickStep(conditionStatusList[bestIndex])
        }
        generateLevel()
        clearCalculateInfo()

    }


        //点击花金
    private fun clickStep(condition: ConditionStatusBean) {

        condition.run {
            currentStepIndex++
            var goldSize = 0
            var progressSize = 0
            var coinGet = 0
            var repeatTime=0
            if (currentStepIndex <= rule.freeStepDaily) {
                goldSize = 0
                repeatTime=currentStepIndex-1
            } else {
                val multiple = (currentStepIndex - 1 - rule.freeStepDaily) / rule.goldIncreaseStep
                goldSize = rule.goldIncreaseSize * multiple + rule.startGoldSize
                repeatTime=(currentStepIndex - 1 - rule.freeStepDaily) % rule.goldIncreaseStep
            }
            if (currentStepIndex >= rule.progressIncreaseStep && (currentStepIndex) % rule.progressIncreaseStep == 0) {
                progressSize = rule.progressIncreaseSize
            }
            if (currentStepIndex >= rule.coinGetStep && (currentStepIndex) % rule.coinGetStep == 0) {
                coinGet = rule.coinGetSize
            }

            val stepBean = StepBean().apply {
                this.conditionName = condition.rule.conditionName
                this.goldSize = goldSize
                this.progressSize = progressSize
                this.coinGet = coinGet
                this.repeatTime=repeatTime
            }
            stepList.add(stepBean)
            totalProgressPerDay += progressSize
            totalCoinPerDay+=coinGet
        }
    }

    //生成一档
    private fun generateLevel() {
        val levelName = if (levelList.size == 0) {
            "白嫖档"
        } else if (levelList.size > pointList.size) {
            "目标档"
        } else {
            "节点" + levelList.size + "档"
        }
        var levelCoin = 0
        var levelGold = 0
        var levelProgress = 0
        stepList.forEachIndexed { index, stepBean ->
            levelCoin += stepBean.coinGet
            levelGold += stepBean.goldSize
            levelProgress += stepBean.progressSize
            Log.d(TAG, stepBean.toString())
        }
        //乘以天数 得到总的
        levelCoin *= date
        levelGold *= date
        levelProgress *= date

        //活动币要再加上节点币
        pointList.forEachIndexed { index, pointBean ->
            if (levelProgress >= pointBean.pointProgress) {
                levelCoin += pointBean.rewardCoin
            }
        }

        val levelBean = LevelBean().apply {
            this@apply.levelName = levelName
            this@apply.totalGold = levelGold
            this@apply.totalProgress = levelProgress
            this@apply.totalCoin = levelCoin
            this@apply.stepListRecord = mutableListOf<StepBean>().apply {
                addAll(stepList)
            }
        }
        levelList.add(levelBean)
    }

    private fun showLevelDetail(levelBean: LevelBean){
        findViewById<LinearLayout>(R.id.ll_level_detail).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tv_detail_title).text = levelBean.levelName+"详情"
        findViewById<ConditionView>(R.id.cdv_progress_date).conditionInt=levelBean.totalProgress/date
        findViewById<ConditionView>(R.id.cdv_gold_date).conditionInt=levelBean.totalGold/date
        //避免麻烦 这里直接吧stepList按操作拆分开
        val operationList= mutableListOf<StepBean>()
        for(i in levelBean.stepListRecord.size-1 downTo  0){
            levelBean.stepListRecord[i].apply {
                var hasSet=false
                operationList.forEachIndexed { index, operationBean ->
                    if(operationBean.conditionName.equals(this.conditionName)){
                        hasSet=true
                    }
                }
                if(!hasSet){
                    operationList.add(this)
                }
            }
        }
        operationAdapter.setNewData(operationList)
    }

    //重置信息  以备再次计算
    private fun clearCalculateInfo() {
        totalProgressPerDay = 0
        totalCoinPerDay=0
        stepList.clear()
        conditionStatusList.forEachIndexed { index, conditionStatusBean ->
            conditionStatusBean.currentStepIndex = 0
        }
    }
}




