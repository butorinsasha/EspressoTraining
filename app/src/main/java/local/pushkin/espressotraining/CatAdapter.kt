package local.pushkin.espressotraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(
    private val cats: List<String>,
    private val onCatClick: (String) -> Unit
) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    class CatViewHolder(
        itemView: View,
        private val clickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val catName: TextView = itemView.findViewById(R.id.catName)

        init {
            itemView.setOnClickListener {
                clickListener(catName.text.toString())
            }
        }
    }

    // создаёт View строки
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat, parent, false)

        return CatViewHolder(view, onCatClick)
    }

    // помещает данные в строку
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.catName.text = cats[position]
    }

    // сообщает, сколько элементов всего
    override fun getItemCount(): Int {
        return cats.size
    }
}