package com.ute.baitap3_2415141122111

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvHoTen: TextView
    private var hoTenHienTai: String = "Chưa có thông tin"

    // Khai bao Launcher theo chuan Activity Result API
    private lateinit var editLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHoTen = findViewById(R.id.tvHoTen)
        tvHoTen.text = hoTenHienTai

        // Dang ky Launcher NGAY TRONG onCreate (bat buoc, khong duoc dang ky trong click)
        editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val tenMoi = result.data?.getStringExtra("EXTRA_NEW_NAME")
                tenMoi?.let {
                    hoTenHienTai = it
                    tvHoTen.text = hoTenHienTai
                }
            }
        }

        findViewById<Button>(R.id.btnChinhSua).setOnClickListener {
            // Gui du lieu hien tai sang EditActivity qua Explicit Intent
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("EXTRA_CURRENT_NAME", hoTenHienTai)
            }
            editLauncher.launch(intent)
        }
    }
}