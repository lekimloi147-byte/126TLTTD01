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

    private lateinit var tvName: TextView
    private lateinit var tvClass: TextView
    private lateinit var tvGpa: TextView
    private var currentName: String = "Lê Kim Lợi"
    private var currentClass: String = "24sk1"
    private var currentGpa: String = "3.8"

    private lateinit var editLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvName = findViewById(R.id.tvName)
        tvClass = findViewById(R.id.tvClass)
        tvGpa = findViewById(R.id.tvGpa)

        updateUI()
        editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    currentName = data.getStringExtra("EXTRA_NAME") ?: currentName
                    currentClass = data.getStringExtra("EXTRA_CLASS") ?: currentClass
                    currentGpa = data.getStringExtra("EXTRA_GPA") ?: currentGpa

                    updateUI() // Cập nhật lại giao diện sau khi nhận dữ liệu mới
                }
            }
        }

        findViewById<Button>(R.id.btnChinhSua).setOnClickListener {
            // Gửi toàn bộ dữ liệu hiện tại sang EditActivity
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("EXTRA_NAME", currentName)
                putExtra("EXTRA_CLASS", currentClass)
                putExtra("EXTRA_GPA", currentGpa)
            }
            editLauncher.launch(intent)
        }
    }

    private fun updateUI() {
        tvName.text = "Họ tên: $currentName"
        tvClass.text = "Lớp: $currentClass"
        tvGpa.text = "GPA: $currentGpa"
    }
}