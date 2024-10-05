package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val loadPlannerButton = findViewById<Button>(R.id.loadPlannerButton)
        val newPlannerButton = findViewById<Button>(R.id.newPlannerButton)

        loadPlannerButton.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java) // 네 번째 화면으로 이동
            startActivity(intent)
        }

        newPlannerButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java) // 세 번째 화면으로 이동
            startActivity(intent)
        }
    }
}
