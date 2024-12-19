package com.example.weatherapp

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.util.*

class BluetoothHandler(private val deviceAddress: String, private val context: Context) {
    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }
    private var bluetoothSocket: BluetoothSocket? = null
    private val myUuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb") // RFCOMM UUID

    // 권한 요청 코드
    private val requestPermissionLocation = 1

    // Bluetooth 권한 체크 함수
    private fun checkBluetoothPermissions(): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    // 권한 요청 함수
    fun requestBluetoothPermissions(activity: AppCompatActivity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            // 권한이 필요함을 사용자에게 설명할 수 있는 경우
            Toast.makeText(activity, "위치 권한이 필요합니다.", Toast.LENGTH_LONG).show()
        }
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            requestPermissionLocation
        )
    }

    // Bluetooth 연결 함수
    fun connectToBluetoothDevice() {
        if (checkBluetoothPermissions()) {
            try {
                val device = bluetoothAdapter?.getRemoteDevice(deviceAddress)
                bluetoothSocket = device?.createRfcommSocketToServiceRecord(myUuid)
                bluetoothSocket?.connect()
                Log.d("BluetoothHandler", "Bluetooth 연결 성공")
            } catch (e: SecurityException) {
                Log.e("BluetoothHandler", "Bluetooth 연결 중 권한 오류 발생", e)
                Toast.makeText(context, "Bluetooth 연결 권한이 부족합니다.", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Log.e("BluetoothHandler", "Bluetooth 연결 오류", e)
                Toast.makeText(context, "Bluetooth 연결 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        } else {
            requestBluetoothPermissions(context as AppCompatActivity)
        }
    }

    fun disconnect() {
        try {
            bluetoothSocket?.close()
        } catch (e: IOException) {
            Log.e("BluetoothHandler", "Error closing Bluetooth socket", e)
        }
    }

    // 데이터 전송 함수
    fun sendData(data: String) {
        try {
            bluetoothSocket?.outputStream?.write(data.toByteArray())
            Log.d("BluetoothHandler", "데이터 전송 성공: $data")
        } catch (e: IOException) {
            Log.e("BluetoothHandler", "Error sending data", e)
        }
    }

    // 읽기 함수 예시
    fun readData(): String? {
        try {
            val inputStream = bluetoothSocket?.inputStream
            val buffer = ByteArray(1024)
            val bytesRead = inputStream?.read(buffer) ?: 0
            return String(buffer, 0, bytesRead)
        } catch (e: IOException) {
            Log.e("BluetoothHandler", "Error reading data", e)
        }
        return null
    }

    // 권한 요청 결과 처리 함수 (Activity에서 onRequestPermissionsResult 호출됨)
    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        when (requestCode) {
            requestPermissionLocation -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한이 승인되었으므로 Bluetooth 연결 시도
                    connectToBluetoothDevice()
                } else {
                    // 권한 거부 처리
                    Log.e("BluetoothHandler", "위치 권한이 거부되었습니다.")
                    Toast.makeText(context, "위치 권한이 거부되었습니다. Bluetooth를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
