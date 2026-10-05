package com.ute.baitap3_2415141122111

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val edtTen = findViewById<EditText>(R.id.edtTen)

        // Nhan ten hien tai tu Intent va dien san vao EditText
        val tenHienTai = intent.getStringExtra("EXTRA_CURRENT_NAME") ?: ""
        edtTen.setText(tenHienTai)

        findViewById<Button>(R.id.btnLuu).setOnClickListener {
            val tenMoi = edtTen.text.toString().trim()

            // Dong goi ten moi vao Intent ket qua
            val resultIntent = Intent().apply {
                putExtra("EXTRA_NEW_NAME", tenMoi)
            }
            // Bat buoc goi setResult TRUOC finish()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}