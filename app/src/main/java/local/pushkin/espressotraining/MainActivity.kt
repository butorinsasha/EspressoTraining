package local.pushkin.espressotraining

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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



        val cats1 = listOf("Barsik", "Murzik", "Vasya", "Pushok")
//        val cats1 = (1..100.map {"Cat $it"}

        val cats2 = (1..100).map {"Cat $it"}

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cats1
        )

        findViewById<ListView>(R.id.catList).adapter = adapter

        val catList = findViewById<ListView>(R.id.catList)

        catList.setOnItemClickListener { parent, view, position, id ->
            result.text = cats1[position]
        }



        val recyclerView = findViewById<RecyclerView>(R.id.catRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CatAdapter(
            cats = cats2,
            onCatClick = { cat -> result.text = cat }
        )

    }
}