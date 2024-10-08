package com.example.travelplanner

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SixthActivity : AppCompatActivity() {

    private lateinit var savePlannerButton: Button
    private lateinit var modifyPlannerButton: Button
    private lateinit var recommendationsTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        // UI 요소 연결
        savePlannerButton = findViewById(R.id.savePlannerButton)
        modifyPlannerButton = findViewById(R.id.modifyPlannerButton)
        recommendationsTextView = findViewById(R.id.recommendationsTextView)

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("TravelPlanner", MODE_PRIVATE)

        // Intent에서 추천 텍스트를 받아와서 TextView에 설정
        val recommendations = intent.getStringExtra("RECOMMENDATIONS")
        recommendationsTextView.text = recommendations ?: "추천 텍스트가 없습니다."

        // 플래너 저장하기 버튼 클릭 시
        savePlannerButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("플래너를 저장하시겠습니까?")
                .setPositiveButton("예") { dialog, id ->
                    // 현재 추천 텍스트를 저장
                    val savedPlannerData = recommendationsTextView.text.toString()
                    savePlanner(savedPlannerData) // 저장 메소드 호출

                    // 첫 번째 화면으로 이동
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish() // 현재 액티비티 종료
                }
                .setNegativeButton("아니요") { dialog, id -> dialog.dismiss() }
            val alert = builder.create()
            alert.show()
        }

        // 플래너 수정하기 버튼 클릭 시
        modifyPlannerButton.setOnClickListener {
            // 다섯 번째 화면으로 돌아가기
            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun savePlanner(plannerData: String) {
        // SharedPreferences에 플래너 데이터 저장
        val editor = sharedPreferences.edit()
        editor.putString("SAVED_PLANNER", plannerData)
        val isSaved = editor.commit() // commit()을 사용하여 저장 결과 확인
        if (isSaved) {
            Log.d("SixthActivity", "플래너 데이터 저장 성공: $plannerData")
        } else {
            Log.d("SixthActivity", "플래너 데이터 저장 실패")
        }
    }
}
