package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_planner.*

class NewPlannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_planner)

        createPlannerButton.setOnClickListener {
            // 입력값을 바탕으로 플래너 생성
            val intent = Intent(this, PlannerDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
