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

        boton_regresar.setOnClickListener {
            val intent = Intent(this, CropActivity::class.java)
            intent.putExtra("cultivo", cultivo)
            startActivity(intent)
        }

        try {
            db.collection("crops/${auth.currentUser!!.email}/cultivos/${cultivo}/tiempos_trabajados")
                .get()
                .addOnSuccessListener { result ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    var total = 0.0
                    val numberFormat = DecimalFormat("#,###.##")
                    for (document in result) {
                        total += document.data["minutos"].toString().toDouble()
                    }
                    txt_total_tiempo.text = "Total de Horas: ${numberFormat.format(total / 60)}"
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error al consultar BD", e) }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

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