package com.ast.astcul

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ast.astcul.adapter.LevelAdapter
import com.ast.astcul.beans.ConditionRuleBean
import com.ast.astcul.beans.ConditionStatusBean
import com.ast.astcul.beans.PointBean
import com.ast.astcul.beans.LevelBean
import com.ast.astcul.beans.StepBean

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initData()
        initView()
        startCalculate()
    }

    private val resultAdapter by lazy {
        LevelAdapter()
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
        findViewById<RecyclerView>(R.id.recyclerview_results).adapter = resultAdapter
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
    }

    //当前单日进度
    private var totalProgressPerDay: Int = 0

    //当前单日花金
    private var totalGoldPerDay: Int = 0

    //当前单日获得活动币
    private var totalCoinPerDay: Int = 0

    private fun startCalculate() {
        calculateForTargetProgress(0)
        pointList.forEachIndexed { index, pointBean ->
            calculateForTargetProgress(pointBean.pointProgress)
        }
        resultAdapter.setNewData(levelList)
    }

    //计算目标进度（以进度为准）
    private fun calculateForTargetProgress(targetProgress: Int) {
        if (targetProgress == 0) {
            //使用免费次数
            conditionStatusList.forEachIndexed { index, condition ->
                for (i in 1..condition.rule.freeStepDaily) {
                    clickStep(condition)
                }
            }
        } else {
            val targetProgressPerDay = targetProgress / date
            while (totalProgressPerDay < targetProgressPerDay) {
                //遍历所有路线 计算成本 选成本低的
                var bestIndex = 0
                var bestPrice = 0f
                conditionStatusList.forEachIndexed { index, conditionStatusBean ->
                    var goldSize = 0
                    var progressSize = 0
                    conditionStatusBean.run {
                        if (currentStepIndex < rule.freeStepDaily) {
                            goldSize = 0
                        } else {
                            goldSize =
                                rule.goldIncreaseSize * ((currentStepIndex - rule.freeStepDaily) / rule.goldIncreaseStep)
                        }
                        if (currentStepIndex >= rule.progressIncreaseStep && (currentStepIndex) % rule.progressIncreaseStep == 0) {
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


    //点击花金
    private fun clickStep(condition: ConditionStatusBean) {

        condition.run {
            var goldSize = 0
            var progressSize = 0
            var coinGet = 0
            if (currentStepIndex < rule.freeStepDaily) {
                goldSize = 0
            } else {
                goldSize =
                    rule.goldIncreaseSize * ((currentStepIndex - rule.freeStepDaily) / rule.goldIncreaseStep)
            }
            if (currentStepIndex >= rule.progressIncreaseStep && (currentStepIndex) % rule.progressIncreaseStep == 0) {
                progressSize = rule.progressIncreaseSize
            }
            if (currentStepIndex >= rule.coinGetStep && (currentStepIndex) % rule.coinGetStep == 0) {
                coinGet = rule.coinGetSize
            }

            val stepBean = StepBean().apply {
                this.goldSize = goldSize
                this.progressSize = progressSize
                this.coinGet = coinGet
            }
            stepList.add(stepBean)
            currentStepIndex++
            totalProgressPerDay += progressSize
            totalGoldPerDay += goldSize
            totalCoinPerDay += coinGet
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
        //计算节点币
        var allCoins = this@ResultActivity.totalCoinPerDay * date
        pointList.forEachIndexed { index, pointBean ->
            if (totalProgressPerDay * date >= pointBean.pointProgress) {
                allCoins += pointBean.rewardCoin
            }
        }

        val levelBean = LevelBean().apply {
            this@apply.levelName = levelName
            this@apply.totalGold = this@ResultActivity.totalGoldPerDay * date
            this@apply.totalProgress = this@ResultActivity.totalProgressPerDay
            this@apply.totalCoin = allCoins
        }
        levelList.add(levelBean)
    }

    //重置信息  以备再次计算
    private fun clearCalculateInfo() {
        totalProgressPerDay = 0
        totalGoldPerDay = 0
        totalCoinPerDay = 0
    }
}




