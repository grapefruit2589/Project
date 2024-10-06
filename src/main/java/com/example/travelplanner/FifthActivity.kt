package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.travelplanner.databinding.ActivityFifthBinding
import com.example.travelplanner.R
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
        //val top10Lines = formattedCSVData.lines().take(10).joinToString("\n")
        val top10Lines = formattedCSVData.lines().take(2).joinToString("\n") {
            it.take(100) // 각 줄에서 최대 100자까지만 가져오기 (필요에 따라 조정 가능)
        }

        //binding.textView.text = formattedCSVData // TextView에 출력
        binding.recommendationItem3.text = top10Lines //formattedCSVData
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

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        // Assuming there will be a RecyclerView to show recommendations
        // and a button for creating the planner
        val createPlannerButton = findViewById<Button>(R.id.createPlannerButton)
        createPlannerButton.setOnClickListener {
            val intent = Intent(this, SixthActivity::class.java)
            startActivity(intent)
        }
    }
    }
    */
