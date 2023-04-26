package farrukh.example.reasa.adapter
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import farrukh.example.reasa.R
import farrukh.example.reasa.model.Featured
import farrukh.example.reasa.model.User

class Recomendation2Adapter(
    var array: MutableList<Featured>,
    var context: Context,
    var listener: Recomendation2Adapter.ItemClick
) : RecyclerView.Adapter<Recomendation2Adapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ImageView>(R.id.image_mini2)
        var card_view = itemView.findViewById<CardView>(R.id.card_mini_2)
        var like = itemView.findViewById<ImageView>(R.id.like)

        var hotel_name = itemView.findViewById<TextView>(R.id.name_mini2)
        var hotel_price = itemView.findViewById<TextView>(R.id.price_mini2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.recomendation_layout2, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.splash_first)
        val temp = array.get(position)

        holder.layout.setBackgroundResource(temp.img)
        holder.hotel_name.text = temp.name
        holder.hotel_price.text = temp.price.toString()

        val dialog = Dialog(context)

        dialog.setContentView(R.layout.dialog_1)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val removeBtn = dialog.findViewById<MaterialButton>(R.id.removeBtn)
        val cancelBtn = dialog.findViewById<MaterialButton>(R.id.cancelBtn)





        if (temp.state == true) {
//            Toast.makeText(context, "added to favorites", Toast.LENGTH_SHORT).show()
            holder.like.startAnimation(anim)
            holder.like.setImageResource(R.drawable.heart_icon)


        } else {
//            temp.state = false
            holder.like.startAnimation(anim)
//            Toast.makeText(context, "removed from favorites", Toast.LENGTH_SHORT).show()
//            tag = 0
            holder.like.setImageResource(R.drawable.heart_org)


        }

        holder.like.setOnClickListener {
            if (temp.state == false) {
                temp.state = true
                Toast.makeText(context, "added to favorites", Toast.LENGTH_SHORT).show()
                holder.like.startAnimation(anim)
                holder.like.setImageResource(R.drawable.heart_icon)
                listener.OnLikeClick(position,temp.state)

            } else {
                removeBtn.setOnClickListener {
                    dialog.dismiss()
                    temp.state = false
                    holder.like.startAnimation(anim)
                    holder.like.setImageResource(R.drawable.heart_org)
                    listener.OnLikeClick(position,temp.state)

                }
                cancelBtn.setOnClickListener {
                    dialog.dismiss()
                    temp.state = true
                    holder.like.setImageResource(R.drawable.heart_icon)
                    listener.OnLikeClick(position,temp.state)
                }

                dialog.show()
                holder.like.startAnimation(anim)



            }



        }


        holder.card_view.startAnimation(anim)
        holder.card_view.setOnClickListener {
            listener.OnItemClick(temp, temp.state, position)
        }
    }

    interface ItemClick {
        fun OnItemClick(
            featured: Featured,
            state: Boolean,
            position: Int
        )

        fun OnLikeClick(position: Int, status: Boolean)
    }
}