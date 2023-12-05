package com.example.progressindicator

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.progressindicator.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val statValues: ArrayList<Float> = ArrayList()
    private lateinit var barChart: BarChart
    private val MAX_X_VALUE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        barChart = binding.perfectCallChart

//        val list : ArrayList<BarEntry> = ArrayList()
//
//        list.add(BarEntry(100f,100f))
//        list.add(BarEntry(101f,101f))
//        list.add(BarEntry(102f,102f))
//        list.add(BarEntry(103f,103f))
//        list.add(BarEntry(104f,104f))
//        list.add(BarEntry(105f,105f))
//
//        val barDataSet = BarDataSet(list, "List")
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
//
//        barDataSet.valueTextColor = Color.BLACK
//        val barData = BarData(barDataSet)
//
//        barChart.setFitBars(true)

        getStats()
    }

    private fun getStats(){
        val value1 : ArrayList<BarEntry> = ArrayList()

        statValues.clear()
        for(i in 0 until MAX_X_VALUE){
            value1.add(
                BarEntry(
                    i.toFloat(),
                    (Math.random() *80).toFloat()
                )
            )
        }

        displayData(value1)
    }
    private fun displayData(orderData:ArrayList<BarEntry>){
        val data: BarData = createChartData(orderData)
        configureBarChart()
        prepareChartData(data)
    }

    private fun createChartData(orderData: ArrayList<BarEntry>): BarData {
        val barDataSet = BarDataSet(orderData, "List")
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)

        barDataSet.valueTextColor = Color.BLACK
        return BarData(barDataSet)
    }
    private fun prepareChartData(data: BarData){
        barChart.data = data
        barChart.barData.barWidth = 0.5f
        barChart.invalidate()

    }
    private fun configureBarChart(){
        barChart.setPinchZoom(false)
        barChart.setDrawBarShadow(false)
        barChart.setDrawGridBackground(false)

        barChart.description.isEnabled = true
        val xAxis = barChart.xAxis
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawGridLines(true)

        val leftAxis = barChart.axisLeft
        leftAxis.setDrawGridLines(true)
        leftAxis.spaceTop=35f
        leftAxis.axisMinimum = 0f

        barChart.axisRight.isEnabled = false
        barChart.xAxis.axisMinimum = 1f
        barChart.xAxis.axisMaximum = 31f
    }
}