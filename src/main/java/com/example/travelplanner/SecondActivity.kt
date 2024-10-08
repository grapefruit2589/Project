package com.example.travelplanner

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var loadPlannerButton: Button
    private lateinit var createNewPlannerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        sharedPreferences = getSharedPreferences("TravelPlanner", MODE_PRIVATE)
        loadPlannerButton = findViewById(R.id.loadPlannerButton)
        createNewPlannerButton = findViewById(R.id.createNewPlannerButton)

        // 새로운 플래너 생성 버튼 클릭 시
        createNewPlannerButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        // 저장된 플래너 불러오기 버튼 클릭 시
        loadPlannerButton.setOnClickListener {
            val savedPlanner = loadPlanner() // 저장된 플래너 로드
            if (savedPlanner != null) {
                // 저장된 플래너가 있을 경우 FourthActivity로 이동
                val intent = Intent(this, FourthActivity::class.java)
                intent.putExtra("SAVED_PLANNER", savedPlanner) // 저장된 플래너 전달
                startActivity(intent)
            } else {
                // 저장된 플래너가 없을 경우 안내 메시지
                Toast.makeText(this, "저장된 플래너가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadPlanner(): String? {
        // SharedPreferences에서 저장된 플래너 데이터 불러오기
        return sharedPreferences.getString("SAVED_PLANNER", null)
    }
}
