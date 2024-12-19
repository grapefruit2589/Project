package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bluetoothHandler: BluetoothHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothHandler = BluetoothHandler("device_address", this)

        // 권한 요청
        bluetoothHandler.requestBluetoothPermissions(this)

        // 하단 네비게이션 버튼 설정
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        // 기본 프래그먼트를 TodayFragment로 설정
        if (savedInstanceState == null) {
            loadFragment(TodayFragment())
        }

        // 네비게이션 버튼 클릭 리스너
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_today -> {
                    loadFragment(TodayFragment())
                    true
                }
                R.id.nav_tomorrow -> {
                    loadFragment(TomorrowFragment())
                    true
                }
                else -> false
            }
        }
    }

    // 프래그먼트를 교체하는 함수
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // BluetoothHandler의 onRequestPermissionsResult 호출
        bluetoothHandler.onRequestPermissionsResult(requestCode, grantResults)
    }
}
