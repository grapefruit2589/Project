package com.example.travelplanner

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SixthActivity : AppCompatActivity() {

    private lateinit var savePlannerButton: Button
    private lateinit var modifyPlannerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        // UI 요소 연결
        savePlannerButton = findViewById(R.id.savePlannerButton)
        modifyPlannerButton = findViewById(R.id.modifyPlannerButton)

        // 플래너 저장하기 버튼 클릭 시
        savePlannerButton.setOnClickListener {
            // 저장 확인 알림창 띄우기
            val builder = AlertDialog.Builder(this)
            builder.setMessage("플래너를 저장하시겠습니까?")
                .setPositiveButton("예") { dialog, id ->
                    // 네번째 화면에 플래너 저장하고 첫번째 화면으로 이동
                    savePlanner()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish() // 현재 액티비티 종료
                }
                .setNegativeButton("아니요") { dialog, id ->
                    // 아무것도 하지 않고 다이얼로그만 닫음
                    dialog.dismiss()
                }
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

    private fun savePlanner() {
        // 네번째 화면의 플래너 목록에 저장하는 로직 추가
        // 실제로는 SharedPreferences나 데이터베이스에 플래너 데이터를 저장하는 코드가 들어감
        // 현재는 더미 함수로 처리
    }
}
