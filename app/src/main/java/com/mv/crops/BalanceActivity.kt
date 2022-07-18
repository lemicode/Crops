package com.mv.crops

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class BalanceActivity : AppCompatActivity() {

    private lateinit var barValuesList: ArrayList<BarEntry>
    private lateinit var barLabelsList: ArrayList<String>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData
    private lateinit var barChartDescription: Description
    private lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_balance)

        barChart = findViewById<BarChart>(R.id.barChart)

        getBarChartData()

    }

    private fun getBarChartData() {

        barValuesList = ArrayList()
        barValuesList.add(BarEntry(1f, 100f, "Lunes"))
        barValuesList.add(BarEntry(2f, 500f, "Martes"))
        barValuesList.add(BarEntry(3f, 200f, "Miercoles"))
        barValuesList.add(BarEntry(4f, 300f, "Jueves"))
        barValuesList.add(BarEntry(5f, 400f, "Viernes"))
        barValuesList.add(BarEntry(6f, 50f, "Sabado"))
        barValuesList.add(BarEntry(7f, 500f, "Domingo"))

        barLabelsList = ArrayList<String>()
        barLabelsList.add("Lunes")
        barLabelsList.add("Martes")
        barLabelsList.add("Miercoles")
        barLabelsList.add("Jueves")
        barLabelsList.add("Viernes")
        barLabelsList.add("Sabado")
        barLabelsList.add("Domingo")

        barChartDescription = Description()
        barChartDescription.text = ""

        drawBarChart()

    }

//        ===============================================================
//        Bar Chart
//        ===============================================================
    private fun drawBarChart() {

        barDataSet = BarDataSet(barValuesList, "Dias")
        barDataSet.setColors(
            Color.rgb(232, 62, 49),
            Color.rgb(232, 224, 49),
            Color.rgb(132, 232, 49),
            Color.rgb(49, 232, 232),
            Color.rgb(232, 49, 109),
            Color.rgb(204, 153, 104),
            Color.rgb(145, 173, 168))
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 10f
        barData = BarData(barDataSet)
        barChart.data = barData
        barChart.setDescription(barChartDescription)
        barChart.legend.isEnabled = false

        var xAxis = barChart.getXAxis()
        xAxis.setValueFormatter(MyAxisValueFormatter(barLabelsList))
        xAxis.position = XAxis.XAxisPosition.BOTTOM

    }

    class MyAxisValueFormatter: IAxisValueFormatter {

        private lateinit var mValues: ArrayList<String>

        constructor(values: ArrayList<String>) {
            this.mValues = values
        }

        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
            println(value)
            return mValues[value.toInt() - 1]
        }

    }

}