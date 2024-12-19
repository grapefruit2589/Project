package com.example.weatherapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TodayFragment : Fragment(R.layout.fragment_today) {

    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var currentTimeTextView: TextView
    private lateinit var bluetoothHandler: BluetoothHandler
    private val deviceAddress = "XX:XX:XX:XX:XX:XX" // 아두이노 블루투스 MAC 주소 입력

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TextView 초기화
        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        humidityTextView = view.findViewById(R.id.humidityTextView)
        currentTimeTextView = view.findViewById(R.id.currentTimeTextView)

        // 현재 시각 표시
        updateCurrentTime()

        // Bluetooth 연결 처리
        bluetoothHandler = BluetoothHandler(deviceAddress, requireContext())

        // 권한 요청
        bluetoothHandler.requestBluetoothPermissions(requireActivity() as AppCompatActivity)

        // 연결 시도
        bluetoothHandler.connectToBluetoothDevice()

        // 데이터 읽기 및 표시
        readTemperatureAndHumidityData()
    }

    private fun updateCurrentTime() {
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        currentTimeTextView.text = getString(R.string.current_time_text, currentTime)
    }

    private fun readTemperatureAndHumidityData() {
        Thread {
            try {
                // Bluetooth로부터 데이터 읽기
                val data = bluetoothHandler.readData()
                if (data != null) {
                    val (temperature, humidity) = data.split(",").map { it.trim() }
                    requireActivity().runOnUiThread {
                        temperatureTextView.text = getString(R.string.temperature_text, temperature)
                        humidityTextView.text = getString(R.string.humidity_text, humidity)
                    }
                }
            } catch (e: IOException) {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "데이터 읽기 오류", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bluetoothHandler.disconnect() // Bluetooth 연결 해제
    }
}
