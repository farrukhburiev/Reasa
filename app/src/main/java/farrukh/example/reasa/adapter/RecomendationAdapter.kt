package farrukh.example.reasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import farrukh.example.reasa.R
import farrukh.example.reasa.model.Featured

class RecomendationAdapter(var offers:MutableList<Featured>):
    RecyclerView.Adapter<RecomendationAdapter.MyHolder>()  {
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.name)
        var price = itemView.findViewById<TextView>(R.id.price)
        var img = itemView.findViewById<ImageView>(R.id.home_img)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomendationAdapter.MyHolder {
        var myHolder = LayoutInflater.from(parent.context).inflate(R.layout.feature_layout,parent,false)

        return RecomendationAdapter.MyHolder(myHolder)
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    override fun onBindViewHolder(holder: RecomendationAdapter.MyHolder, position: Int) {
        var cont = offers.get(position)
        holder.name.text = cont.name
        holder.price.text = cont.price.toString()
        holder.img.setImageResource(cont.img)

    }


}