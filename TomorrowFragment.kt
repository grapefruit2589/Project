package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TomorrowFragment : Fragment(R.layout.fragment_tomorrow) {
    private lateinit var tempModel: Interpreter
    private lateinit var rainModel: Interpreter
    private var predictedTemp: Double = 0.0
    private var rainExpected: Double = 0.0

    private lateinit var tempTextView: TextView
    private lateinit var rainTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TextView 초기화
        tempTextView = view.findViewById(R.id.tempTextView)
        rainTextView = view.findViewById(R.id.rainTextView)

        // 모델 로드
        loadModels()
        predictTemperature()
        predictRain()

        // 결과를 화면에 표시
        tempTextView.text = getString(R.string.predicted_temp_format, predictedTemp)
        rainTextView.text = getString(R.string.rain_expected_format, rainExpected)
    }

    private fun loadModels() {
        try {
            // model_temp.tflite 로드
            context?.assets?.openFd("model_temp.tflite")?.let { fileDescriptor ->
                val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
                val fileChannel = inputStream.channel
                val startOffset = fileDescriptor.startOffset
                val declaredLength = fileDescriptor.declaredLength
                val tempModelBuffer: MappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
                tempModel = Interpreter(tempModelBuffer)
            }

            // rain_model.tflite 로드
            context?.assets?.openFd("rain_model.tflite")?.let { fileDescriptor ->
                val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
                val fileChannel = inputStream.channel
                val startOffset = fileDescriptor.startOffset
                val declaredLength = fileDescriptor.declaredLength
                val rainModelBuffer: MappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
                rainModel = Interpreter(rainModelBuffer)
            }
        } catch (e: Exception) {
            Log.e("TomorrowFragment", "Error loading models", e)
        }
    }

    private fun predictTemperature() {
        val input = floatArrayOf(25.6f, 60.0f) // 예시 데이터
        val output = FloatArray(1) // 예측 결과를 저장할 배열
        tempModel.run(input, output) // 예측 수행
        predictedTemp = output[0].toDouble() // 결과 저장
    }

    private fun predictRain() {
        val input = floatArrayOf(25.6f, 60.0f) // 예시 데이터
        val output = FloatArray(1) // 예측 결과를 저장할 배열
        rainModel.run(input, output) // 예측 수행
        rainExpected = output[0].toDouble() // 결과 저장
    }
}
