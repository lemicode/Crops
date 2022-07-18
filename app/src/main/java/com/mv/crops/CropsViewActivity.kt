package com.mv.crops

import Models.Crop
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mv.crops.adapters.CropAdapter

class CropsViewActivity : AppCompatActivity() {

    private lateinit var pieValuesList: ArrayList<PieEntry>
    private lateinit var pieDataSet: PieDataSet
    private lateinit var pieData: PieData
    private lateinit var pieChartDescription: Description

    private lateinit var recyclerView: RecyclerView
    private lateinit var cropList: ArrayList<Crop>
    private lateinit var cropAdapter: CropAdapter

    private lateinit var pieChart: PieChart

    private lateinit var auth: FirebaseAuth;
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_crops_view)

        val boton_regresar = findViewById<ImageView>(R.id.crops_view_btn_regresar)

        boton_regresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        auth = Firebase.auth

        pieChart = findViewById<PieChart>(R.id.pieChart)

        getPieChartData()

    }

    private fun getPieChartData() {

        cropList = ArrayList()
        pieValuesList = ArrayList()

        db.collection("crops/${auth.currentUser!!.email}/cultivos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    cropList.add(Crop(document.id))
                    pieValuesList.add(PieEntry(document.data["area"].toString().toFloat(), "${document.data["area"].toString()}ha   ${document.id}"))
                }
                initRecyclerView()
                drawPieChart()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

    }

//        ===============================================================
//        Pie Chart
//        ===============================================================
    private fun drawPieChart() {

        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 80f, 5f)

        pieChart.setDragDecelerationFrictionCoef(0.95f)

        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)

        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        pieChart.setHoleRadius(58f)
        pieChart.setTransparentCircleRadius(61f)

        pieChart.setDrawCenterText(true)

        pieChart.setRotationAngle(0f)

        pieChart.setRotationEnabled(true)
//        pieChart.setHighlightPerTapEnabled(true)

        pieChart.animateY(1400, Easing.EaseInOutQuad)

        pieChart.legend.isEnabled = true
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        pieChart.legend.isWordWrapEnabled = true
        pieChart.legend.xOffset = 20f
        pieChart.setEntryLabelColor(Color.BLUE)
        pieChart.setEntryLabelTextSize(5f)

        pieDataSet = PieDataSet(pieValuesList, "")
        pieDataSet.setDrawIcons(false)
        pieDataSet.setDrawValues(false)

        pieDataSet.sliceSpace = 3f
        pieDataSet.iconsOffset = MPPointF(0f, 40f)
        pieDataSet.selectionShift = 5f

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.rgb(232, 224, 49))
        colors.add(Color.rgb(232, 49, 109))
        colors.add(Color.rgb(232, 224, 49))
        pieDataSet.setColors(
            Color.rgb(232, 62, 49),
            Color.rgb(232, 224, 49),
            Color.rgb(132, 232, 49),
            Color.rgb(49, 232, 232),
            Color.rgb(232, 49, 109),
            Color.rgb(204, 153, 104),
            Color.rgb(145, 173, 168))

        pieData = PieData(pieDataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(15f)
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD)
        pieData.setValueTextColor(Color.BLUE)
        pieChart.setData(pieData)
        pieChart.setDrawEntryLabels(false)

        pieChartDescription = Description()
        pieChartDescription.text = ""

        pieChart.highlightValues(null)

        pieChart.invalidate()

    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.crops_view_recyclerview_horizontal)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        cropAdapter = CropAdapter(cropList)
        recyclerView.adapter = cropAdapter
        cropAdapter.setOnItemClickListener(object: CropAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@CropsViewActivity, "Seleccionó ${cropList[position].nombre}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CropsViewActivity, CropActivity::class.java)
                intent.putExtra("cultivo", cropList[position].nombre)
                startActivity(intent)
            }
        })
    }

}