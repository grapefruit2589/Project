package com.example.travelplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FifthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
