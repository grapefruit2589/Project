package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_existing_planner.*

class ExistingPlannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_planner)

        loadPlannerButton.setOnClickListener {
            // 기존 플래너를 로드하고 수정 화면으로 이동
            val intent = Intent(this, PlannerDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
