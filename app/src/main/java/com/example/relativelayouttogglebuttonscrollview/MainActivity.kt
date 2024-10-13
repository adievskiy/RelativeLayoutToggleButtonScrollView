package com.example.relativelayouttogglebuttonscrollview

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var downloadBTN: Button
    private lateinit var textViewTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Электронная книга"

        textViewTV = findViewById(R.id.textViewTV)

        downloadBTN = findViewById(R.id.downloadBTN)
        downloadBTN.setOnClickListener {
            val database = Database()
            val bookWords = loadBook(database.text)
            textViewTV.text = bookWords.joinToString(" ")
        }
    }

    private fun loadBook(text: String): List<String> {
        return text.split("\\s+".toRegex()).filter { it.isNotEmpty() }
    }
}