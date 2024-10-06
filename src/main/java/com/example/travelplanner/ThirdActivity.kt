package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var genderGroup: RadioGroup
    private lateinit var ageGroup: RadioGroup
    private lateinit var companionGroup: RadioGroup
    private lateinit var style1: CheckBox
    private lateinit var style2: CheckBox
    private lateinit var style3: CheckBox
    private lateinit var style4: CheckBox
    private lateinit var style5: CheckBox
    private lateinit var style6: CheckBox
    private lateinit var style7: CheckBox
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        // UI 요소 연결
        genderGroup = findViewById(R.id.genderGroup)
        ageGroup = findViewById(R.id.ageGroup)
        companionGroup = findViewById(R.id.companionGroup)
        style1 = findViewById(R.id.style1)
        style2 = findViewById(R.id.style2)
        style3 = findViewById(R.id.style3)
        style4 = findViewById(R.id.style4)
        style5 = findViewById(R.id.style5)
        style6 = findViewById(R.id.style6)
        style7 = findViewById(R.id.style7)
        submitButton = findViewById(R.id.submitButton)

        // 제출 버튼 클릭 시 입력값 처리
        submitButton.setOnClickListener {
            // 성별 선택 확인
            val selectedGenderId = genderGroup.checkedRadioButtonId
            if (selectedGenderId == -1) {
                Toast.makeText(this, "성별을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 나이 선택 확인
            val selectedAgeId = ageGroup.checkedRadioButtonId
            if (selectedAgeId == -1) {
                Toast.makeText(this, "나이를 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 여행 스타일 선택 확인
            val selectedStyles = mutableListOf<String>()
            if (style1.isChecked) selectedStyles.add(style1.tag.toString())
            if (style2.isChecked) selectedStyles.add(style2.tag.toString())
            if (style3.isChecked) selectedStyles.add(style3.tag.toString())
            if (style4.isChecked) selectedStyles.add(style4.tag.toString())
            if (style5.isChecked) selectedStyles.add(style5.tag.toString())
            if (style6.isChecked) selectedStyles.add(style6.tag.toString())
            if (style7.isChecked) selectedStyles.add(style7.tag.toString())

            // 여행 스타일 체크 확인
            if (selectedStyles.isEmpty()) {
                Toast.makeText(this, "여행 스타일을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 동반자 수 선택 확인
            val selectedCompanionId = companionGroup.checkedRadioButtonId
            if (selectedCompanionId == -1) {
                Toast.makeText(this, "동반자 수를 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 입력값 처리 (다음 화면으로 이동)
            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }
    }
}