package com.ast.astcul

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ast.astcul.adapter.ConditionRuleAdapter
import com.ast.astcul.adapter.PointAdapter
import com.ast.astcul.beans.ConditionRuleBean
import com.ast.astcul.beans.PointBean
import com.ast.astcul.beans.StepBean
import com.ast.astcul.views.ConditionView
import java.util.ArrayList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private val stepList = mutableListOf<StepBean>()
    private val conditionList = mutableListOf<ConditionRuleBean>()
    private val pointList = mutableListOf<PointBean>()

    private val conditionRuleAdapter by lazy {
        ConditionRuleAdapter()
    }
    private val pointAdapter by lazy{
        PointAdapter()
    }

    //初始化数据
    private fun initData(){
        conditionList.add(ConditionRuleBean().apply {
            conditionName="点A"
            freeStepDaily=30
            startGoldSize=2
            maxGoldSize=120
            goldIncreaseStep=5
            goldIncreaseSize=2
            progressIncreaseStep=1
            progressIncreaseSize=1
            coinGetStep=4
            coinGetSize=0
        })
        conditionList.add(ConditionRuleBean().apply {
            conditionName="点B"
            freeStepDaily=0
            startGoldSize=888
            maxGoldSize=8888
            goldIncreaseStep=1
            goldIncreaseSize=1000
            progressIncreaseStep=1
            progressIncreaseSize=8
            coinGetStep=1
            coinGetSize=1
        })

        pointList.add(PointBean().apply {
            pointName="节点1"
            pointProgress=280
            rewardCoin=48
        })
        pointList.add(PointBean().apply {
            pointName="节点2"
            pointProgress= 550
            rewardCoin= 58
        })
        pointList.add(PointBean().apply {
            pointName="节点3"
            pointProgress= 950
            rewardCoin= 78
        })
        pointList.add(PointBean().apply {
            pointName="节点4"
            pointProgress= 1300
            rewardCoin= 78
        })
        pointList.add(PointBean().apply {
            pointName="节点5"
            pointProgress= 1450
            rewardCoin= 96
        })
        pointList.add(PointBean().apply {
            pointName="节点6"
            pointProgress= 1600
            rewardCoin= 96
        })
        pointList.add(PointBean().apply {
            pointName="节点7"
            pointProgress= 1800
            rewardCoin= 116
        })
    }
    private fun initView(){
        conditionRuleAdapter.setNewData(conditionList)
        findViewById<RecyclerView>(R.id.recyclerview_condition).adapter=conditionRuleAdapter
        pointAdapter.setNewData(pointList)
        findViewById<RecyclerView>(R.id.recyclerview_points).adapter=pointAdapter

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            val target=findViewById<EditText>(R.id.et_target).text?.toString()
            if(target.isNullOrEmpty()){
                return@setOnClickListener
            }
            val date=findViewById<ConditionView>(R.id.cdv_days).conditionInt
            if(date==0){
                return@setOnClickListener
            }
            startActivity(Intent(this@MainActivity,ResultActivity::class.java).apply {
                putParcelableArrayListExtra("condition",conditionList as ArrayList<out Parcelable>)
                putExtra("point",pointList as ArrayList<out Parcelable>)
                putExtra("target",target.toInt())
                putExtra("date",date)
            })
        }
    }

}

