package com.mv.crops

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginBottom
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import java.text.DecimalFormat

class BalanceActivity : AppCompatActivity() {

    private lateinit var barValuesList: ArrayList<BarEntry>
    private lateinit var barLabelsList: ArrayList<String>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData
    private lateinit var barChartDescription: Description
    private lateinit var barChart: BarChart
    private val db = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_balance)

        var cultivo = getIntent().getExtras()!!.getString("cultivo")

        val boton_regresar = findViewById<ImageView>(R.id.balance_btn_regresar)
        val txt_total_tiempo = findViewById<TextView>(R.id.balance_txt_total_tiempo)
        val txt_total_agua = findViewById<TextView>(R.id.balance_txt_total_agua)
        val txt_total_hectareas = findViewById<TextView>(R.id.balance_txt_total_hectareas)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, CropActivity::class.java)
            intent.putExtra("cultivo", cultivo)
            startActivity(intent)
        }

        try {

            barValuesList = ArrayList()
            barLabelsList = ArrayList<String>()

            db.collection("crops/${auth.currentUser!!.email}/cultivos/${cultivo}/tiempos_trabajados")
                .orderBy("fecha", Query.Direction.DESCENDING)
                .limit(7)
                .get()
                .addOnSuccessListener { result ->

                    var total = 0.0
                    var i : Float = 1f
                    val numberFormat = DecimalFormat("#,###.##")
                    for (document in result) {
                        Log.d(ContentValues.TAG, document.data.toString())
                        barValuesList.add(BarEntry(i, document.data["minutos"].toString().toFloat(), document.data["fecha"].toString()))
                        barLabelsList.add(document.data["fecha"].toString())
                        i += 1
                        total += document.data["minutos"].toString().toDouble()
                    }
                    if (total != null) {
                        txt_total_tiempo.text = "Total Horas Trabajadas: ${numberFormat.format(total / 60)}"
                    }

                    drawBarChart()
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error consultando BD", e) }

            db.collection("crops/${auth.currentUser!!.email}/cultivos/${cultivo}/agua_regada")
                .orderBy("fecha", Query.Direction.DESCENDING)
                .limit(7)
                .get()
                .addOnSuccessListener { result ->

                    var total = 0.0
                    var i : Float = 1f
                    val numberFormat = DecimalFormat("#,###.##")
                    for (document in result) {
                        Log.d(ContentValues.TAG, document.data.toString())
                        total += document.data["cantidad"].toString().toDouble()
                    }
                    if (total != null) {
                        txt_total_agua.text = "Total Agua Regada: ${numberFormat.format(total / 60)}"
                    }

                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error consultando BD", e) }

            db.collection("crops/${auth.currentUser!!.email}/cultivos/${cultivo}/hectareas_trabajadas")
                .orderBy("fecha", Query.Direction.DESCENDING)
                .limit(7)
                .get()
                .addOnSuccessListener { result ->

                    var total = 0.0
                    var i : Float = 1f
                    val numberFormat = DecimalFormat("#,###.##")
                    for (document in result) {
                        Log.d(ContentValues.TAG, document.data.toString())
                        total += document.data["cantidad"].toString().toDouble()
                    }
                    if (total != null) {
                        txt_total_hectareas.text = "Total Hectareas Trabajadas: ${numberFormat.format(total / 60)}"
                    }

                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error consultando BD", e) }

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }

//        ===============================================================
//        Bar Chart
//        ===============================================================
    private fun drawBarChart() {

        barChart = findViewById<BarChart>(R.id.barChart)

        barChartDescription = Description()
        barChartDescription.text = ""
        barChart.setDescription(barChartDescription)
        barChart.legend.isEnabled = false


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

        barChart.extraBottomOffset = 100f

        var xAxis = barChart.getXAxis()
        xAxis.setValueFormatter(MyAxisValueFormatter(barLabelsList))
        xAxis.labelRotationAngle = 90f
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        barChart.invalidate()

    }

    class MyAxisValueFormatter: IAxisValueFormatter {

        private lateinit var mValues: ArrayList<String>

        constructor(values: ArrayList<String>) {
            this.mValues = values
        }

        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
            return mValues[value.toInt()]
        }

    }

}