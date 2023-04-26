package farrukh.example.reasa.adapter
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import farrukh.example.reasa.R
import farrukh.example.reasa.model.Button
import farrukh.example.reasa.model.Category
import farrukh.example.reasa.model.Featured

class CategoriesAdapter(
    var array: MutableList<Button>,
    var list: MutableList<Featured>,
    var context: Context,
    var listener: CategoriesAdapter.ItemClick

) : RecyclerView.Adapter<CategoriesAdapter.MyHolder>() {
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn = itemView.findViewById<MaterialButton>(R.id.cat_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = array.get(position)
        var categories = mutableListOf<Featured>()
        holder.btn.text = item.name


//        listener.OnItemClick(categories)

        holder.btn.iconSize = 40
        holder.btn.setIconResource(item.drawable)
        holder.btn.setOnClickListener {
            btn_listener(holder.btn)
        }


    }
    private fun btn_listener(btn:MaterialButton){
        if (btn.currentTextColor.equals(Color.BLUE)){
            btn.setTextColor(Color.WHITE)
            btn.setBackgroundColor(Color.BLUE)
            btn.setIconTintResource(R.color.white)
            btn.iconSize = 40
        }
        else{
            btn.setTextColor(Color.BLUE)
            btn.setBackgroundColor(Color.WHITE)
            btn.setIconTintResource(R.color.blue)
            btn.setStrokeColorResource(R.color.blue)
            btn.strokeWidth = 2
            btn.iconSize = 40
        }
    }

    interface ItemClick {
        fun OnItemClick(featured: MutableList<Featured>)
    }
}