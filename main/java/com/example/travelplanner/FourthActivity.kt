package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class FourthActivity : AppCompatActivity() {

    private lateinit var selectCompleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        // RecyclerView for displaying saved planners
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dummy data (replace with actual saved planners)
        val savedPlanners = listOf(
            Planner("2024-10-01 to 2024-10-05", "Hotel A", "Jeju Island", 2),
            Planner("2024-11-15 to 2024-11-20", "Resort B", "Udo Island", 1)
        )

        // Set adapter with dummy data
        val adapter = PlannerAdapter(savedPlanners)
        recyclerView.adapter = adapter

        // '선택 완료' 버튼 클릭 시 다섯번째 화면으로 이동
        selectCompleteButton = findViewById(R.id.selectCompleteButton)
        selectCompleteButton.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }
    }
}
