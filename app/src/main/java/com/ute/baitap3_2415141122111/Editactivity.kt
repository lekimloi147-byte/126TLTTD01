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

        // Ánh xạ đúng ID từ activity_edit.xml
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtClass = findViewById<EditText>(R.id.edtClass)
        val edtGpa = findViewById<EditText>(R.id.edtGpa)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        edtName.setText(intent.getStringExtra("EXTRA_NAME") ?: "")
        edtClass.setText(intent.getStringExtra("EXTRA_CLASS") ?: "")
        edtGpa.setText(intent.getStringExtra("EXTRA_GPA") ?: "")
        btnSave.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("EXTRA_NAME", edtName.text.toString().trim())
                putExtra("EXTRA_CLASS", edtClass.text.toString().trim())
                putExtra("EXTRA_GPA", edtGpa.text.toString().trim())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}