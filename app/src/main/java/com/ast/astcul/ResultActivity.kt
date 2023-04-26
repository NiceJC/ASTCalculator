package com.ast.astcul

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ast.astcul.adapter.LevelAdapter
import com.ast.astcul.beans.ConditionBean
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
    private lateinit var conditionList: List<ConditionBean>

    //进度条件
    private lateinit var pointList: List<PointBean>

    //目标活动币
    private var targetCoin: Int = 0

    //活动天数
    private var date: Int = 0

    private fun initData() {
        conditionList = intent.getParcelableArrayListExtra("condition") ?: listOf()
        pointList = intent.getParcelableArrayListExtra("point") ?: listOf()
        targetCoin = intent.getIntExtra("target", 0)
        date = intent.getIntExtra("date", 0)
    }

    private fun initView() {
        findViewById<RecyclerView>(R.id.recyclerview_results).adapter=resultAdapter
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
    }

    private var totalGold: Int = 0
    private var totalProgress: Int = 0
    private var totalCoin: Int = 0

    private fun startCalculate() {
        useFreeStep()
        generateLevel()


        resultAdapter.setNewData(levelList)
    }

    //每天  先用去免费次数
    private fun useFreeStep() {
        conditionList.forEachIndexed { index, condition ->
            for (i in 1..condition.freeStepDaily) {
                clickStep(condition)
            }
        }
    }

    //点击花金
    private fun clickStep(condition: ConditionBean) {

        val stepBean=StepBean().apply {
            goldSize;
            progressSize
            coinGet;
            coinPoint;
        }

    }
    //生成一档
    private fun generateLevel() {
        val levelName = if(levelList.size==0){
            "白嫖档"
        }else if(levelList.size>pointList.size){
            "目标档"
        }else{
            "节点"+levelList.size+"档"
        }
        val levelBean= LevelBean().apply {
            this@apply.levelName=levelName
            this@apply.totalGold=this@ResultActivity.totalGold
            this@apply.totalProgress=this@ResultActivity.totalProgress
            this@apply.totalCoin=this@ResultActivity.totalCoin
        }
        levelList.add(levelBean)
    }
}




