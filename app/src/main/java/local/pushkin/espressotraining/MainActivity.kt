package local.pushkin.espressotraining

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextName)
        val button = findViewById<Button>(R.id.buttonHello)
        val result = findViewById<TextView>(R.id.textResult)

        button.setOnClickListener {
            result.text = editText.text.toString()
        }
    }
}