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



//        val cats = listOf("Barsik", "Murzik", "Vasya", "Pushok")
        val cats = (1..50).map {"Cat $it"}

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cats
        )

        findViewById<ListView>(R.id.catList).adapter = adapter

        val catList = findViewById<ListView>(R.id.catList)

        catList.setOnItemClickListener { parent, view, position, id ->
            result.text = cats[position]
        }
    }
}