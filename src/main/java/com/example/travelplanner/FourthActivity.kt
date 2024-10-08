package com.example.travelplanner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FourthActivity : AppCompatActivity() {

    private lateinit var savedPlannerTextView: TextView // 저장된 플래너를 표시할 TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        savedPlannerTextView = findViewById(R.id.savedPlannerTextView) // TextView 초기화

        // Intent에서 저장된 플래너 데이터를 받아오기
        val savedPlannerData = intent.getStringExtra("SAVED_PLANNER")
        savedPlannerTextView.text = savedPlannerData ?: "저장된 플래너가 없습니다."
    }
}
