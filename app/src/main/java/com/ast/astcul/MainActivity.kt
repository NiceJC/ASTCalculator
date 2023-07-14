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
            coinGetStep=1
            coinGetSize=1
        })
        conditionList.add(ConditionRuleBean().apply {
            conditionName="点B"
            freeStepDaily=0
            startGoldSize=18
            maxGoldSize=8888
            goldIncreaseStep=1
            goldIncreaseSize=30
            progressIncreaseStep=1
            progressIncreaseSize=5
            coinGetStep=1
            coinGetSize=5
        })

        conditionList.add(ConditionRuleBean().apply {
            conditionName="点C"
            freeStepDaily=0
            startGoldSize=88
            maxGoldSize=8888
            goldIncreaseStep=1
            goldIncreaseSize=100
            progressIncreaseStep=1
            progressIncreaseSize=10
            coinGetStep=1
            coinGetSize=10
        })
        pointList.add(PointBean().apply {
            pointName="节点1"
            pointProgress=290
            rewardCoin=10+15+20+10
        })
        pointList.add(PointBean().apply {
            pointName="节点2"
            pointProgress=290+300-10
            rewardCoin=10+15+20+10
        })
        pointList.add(PointBean().apply {
            pointName="节点3"
            pointProgress=290+300+310-20
            rewardCoin=15+20+25+10
        })
        pointList.add(PointBean().apply {
            pointName="节点4"
            pointProgress=290+300+310+320-30
            rewardCoin=15+20+25+10
        })
        pointList.add(PointBean().apply {
            pointName="节点5"
            pointProgress=290+300+310+320+330-40
            rewardCoin=20+25+30+10
        })
        pointList.add(PointBean().apply {
            pointName="节点6"
            pointProgress=290+300+310+320+330+350-50
            rewardCoin=25+30+35+10
        })
        pointList.add(PointBean().apply {
            pointName="节点7"
            pointProgress=290+300+310+320+330+350+350-60
            rewardCoin=25+30+35+10
        })
//        pointList.add(PointBean().apply {
//            pointName="节点8"
//            pointProgress=30*24
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点9"
//            pointProgress=30*27
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点10"
//            pointProgress=30*30
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点11"
//            pointProgress=30*33
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点121"
//            pointProgress=30*36
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点13"
//            pointProgress=30*39
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点14"
//            pointProgress=30*42
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点15"
//            pointProgress=30*45
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点16"
//            pointProgress=30*48
//            rewardCoin=23*3
//        })
//        pointList.add(PointBean().apply {
//            pointName="节点17"
//            pointProgress=1880
//            rewardCoin=588
//        })
    }
    private fun initView(){
        conditionRuleAdapter.setNewData(conditionList)
        findViewById<RecyclerView>(R.id.recyclerview_condition).adapter=conditionRuleAdapter
        pointAdapter.setNewData(pointList)
        findViewById<RecyclerView>(R.id.recyclerview_points).adapter=pointAdapter

        findViewById<Button>(R.id.btn_start).setOnClickListener {
//            val target=findViewById<EditText>(R.id.et_target).text?.toString()
//            if(target.isNullOrEmpty()){
//                return@setOnClickListener
//            }
//            val date=findViewById<ConditionView>(R.id.cdv_days).conditionInt
//            if(date==0){
//                return@setOnClickListener
//            }
//            startActivity(Intent(this@MainActivity,ResultActivity::class.java).apply {
//                putParcelableArrayListExtra("condition",conditionList as ArrayList<out Parcelable>)
//                putExtra("point",pointList as ArrayList<out Parcelable>)
//                putExtra("target",target.toInt())
//                putExtra("date",date)
//            })
            startActivity(Intent(this@MainActivity,CakeActivity::class.java)     )

        }
    }


}

