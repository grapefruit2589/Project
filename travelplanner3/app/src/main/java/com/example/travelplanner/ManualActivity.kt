package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_manual.*

class ManualActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual)

        existingPlannerButton.setOnClickListener {
            // 기존 플래너 화면으로 이동
            val intent = Intent(this, ExistingPlannerActivity::class.java)
            startActivity(intent)
        }

        newPlannerButton.setOnClickListener {
            // 새로운 플래너 생성 화면으로 이동
            val intent = Intent(this, NewPlannerActivity::class.java)
            startActivity(intent)
        }
    }
}
