package com.ast.astcul

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import com.ast.astcul.adapter.ConditionAdapter
import com.ast.astcul.adapter.PointAdapter
import com.ast.astcul.beans.ConditionBean
import com.ast.astcul.beans.PointBean
import com.ast.astcul.beans.StepBean
import com.ast.astcul.ui.theme.ASTCulTheme
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
    private val conditionList = mutableListOf<ConditionBean>()
    private val pointList = mutableListOf<PointBean>()

    private val conditionAdapter by lazy {
        ConditionAdapter()
    }
    private val pointAdapter by lazy{
        PointAdapter()
    }

    //初始化数据
    private fun initData(){
        conditionList.add(ConditionBean().apply {
            conditionName="点A"
            freeStepDaily=20
            startGoldSize=2
            maxGoldSize=120
            goldIncreaseStep=5
            goldIncreaseSize=2
            progressIncreaseStep=1
            progressIncreaseSize=1
            coinGetStep=4
            coinGetSize=1
        })
        conditionList.add(ConditionBean().apply {
            conditionName="点B"
            freeStepDaily=1
            startGoldSize=88
            maxGoldSize=888
            goldIncreaseStep=1
            goldIncreaseSize=100
            progressIncreaseStep=1
            progressIncreaseSize=8
            coinGetStep=1
            coinGetSize=2
        })

        pointList.add(PointBean().apply {
            pointName="节点1"
            pointProgress=288
            rewardCoin=40
        })
        pointList.add(PointBean().apply {
            pointName="节点2"
            pointProgress= 788
            rewardCoin= 60
        })
        pointList.add(PointBean().apply {
            pointName="节点3"
            pointProgress= 1088
            rewardCoin= 80
        })
        pointList.add(PointBean().apply {
            pointName="节点4"
            pointProgress= 1388
            rewardCoin= 140
        })
    }
    private fun initView(){
        conditionAdapter.setNewData(conditionList)
        findViewById<RecyclerView>(R.id.recyclerview_condition).adapter=conditionAdapter
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

