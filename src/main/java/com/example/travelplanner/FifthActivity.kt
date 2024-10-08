package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travelplanner.databinding.ActivityFifthBinding
import com.opencsv.CSVReader
import java.io.InputStreamReader



class FifthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFifthBinding // Activity의 데이터 바인딩 객체

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFifthBinding.inflate(layoutInflater) // 데이터 바인딩 객체 초기화
        setContentView(binding.root)

        // CSV 파일을 읽고 형식화된 데이터를 TextView에 설정
        val csvData = readCSVFromRawResource()
        val formattedCSVData = formatCSVData(csvData)
        val top10Lines = formattedCSVData.lines().take(2).joinToString("\n") {
            it.take(100) // 각 줄에서 최대 100자까지만 가져오기
        }

        binding.recommendationItem3.text = top10Lines // 추천 텍스트 출력

        // 플래너 생성 버튼 클릭 리스너
        binding.createPlannerButton.setOnClickListener {
            val intent = Intent(this, SixthActivity::class.java).apply {
                putExtra("RECOMMENDATIONS", top10Lines) // 추천 텍스트를 Intent에 추가
            }
            startActivity(intent)
        }
    }

    // CSV 파일을 읽어서 2차원 배열로 반환
    private fun readCSVFromRawResource(): List<Array<String>> {
        val inputStream = resources.openRawResource(R.raw.merged_travel_data)
        val reader = CSVReader(InputStreamReader(inputStream))

        val csvData = mutableListOf<Array<String>>()

        var nextLine: Array<String>?
        while (reader.readNext().also { nextLine = it } != null) {
            nextLine?.let {
                csvData.add(it)
            }
        }
        reader.close()
        return csvData
    }

    // 2차원 배열을 문자열로 변환하여 출력 형식으로 준비
    private fun formatCSVData(csvData: List<Array<String>>): String {
        val stringBuilder = StringBuilder()
        for (row in csvData) {
            stringBuilder.append(row.joinToString(", ")).append("\n")
        }
        return stringBuilder.toString()
    }
}

