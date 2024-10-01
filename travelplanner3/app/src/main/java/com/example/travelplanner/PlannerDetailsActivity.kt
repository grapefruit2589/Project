package com.example.travelplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_planner_details.*

class PlannerDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planner_details)

        // 플래너 정보를 표시하고 수정 가능하게 함
        savePlannerButton.setOnClickListener {
            // 수정된 플래너 저장
        }

        purchasePackageButton.setOnClickListener {
            // 패키지 구매 화면으로 이동
        }
    }
}
