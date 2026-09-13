package local.pushkin.espressotraining

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text_name)
        val buttonHello = findViewById<Button>(R.id.button_hello)
        val buttonShowAlertDialog = findViewById<Button>(R.id.button_show_alert_dialog)
        val buttonShowToast = findViewById<Button>(R.id.button_show_toast)
        val buttonOpenGoogle = findViewById<Button>(R.id.button_open_google)
        val buttonOneSecondActivity = findViewById<Button>(R.id.button_open_second_activity)
        val result = findViewById<TextView>(R.id.text_result)

        buttonHello.setOnClickListener {
            if (editText.text.isBlank()) {
                editText.error = "Input a name"
            } else {
                result.text = editText.text.toString()
            }
        }

        buttonShowAlertDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .show()
        }

        buttonShowToast.setOnClickListener {
            Toast.makeText(
                this,
                "Hello from Toast",
                Toast.LENGTH_SHORT
            ).show()
        }

        buttonOpenGoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://google.com")
            }
            startActivity(intent)
        }

        buttonOneSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val cats1 = listOf("Barsik", "Murzik", "Vasya", "Pushok")
        val cats2 = (1..100).map { "Cat $it" }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cats1
        )

        findViewById<ListView>(R.id.cat_list).adapter = adapter

        val catList = findViewById<ListView>(R.id.cat_list)

        catList.setOnItemClickListener { parent, view, position, id ->
            result.text = cats1[position]
        }


        val recyclerView = findViewById<RecyclerView>(R.id.cat_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CatAdapter(
            cats = cats2,
            onCatClick = { cat -> result.text = cat }
        )

    }
}