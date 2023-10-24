package com.example.joshqinshop.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.joshqinshop.R
import com.example.joshqinshop.model.Product
import com.example.joshqinshop.util.SharedP

class ChoosedAdapter(var context: Context, var onPressed: OnPressed) : RecyclerView.Adapter<ChoosedAdapter.MyViewHolder>(){
    val mySharedPreferences = SharedP.getInstance(context)
    var b = mySharedPreferences.getSelectedCarsList()
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var rasm = view.findViewById<ImageView>(R.id.choosed_image)
        var title = view.findViewById<TextView>(R.id.choosed_title)
        var reyting = view.findViewById<TextView>(R.id.choosed_rating)
        var korzina = view.findViewById<ImageView>(R.id.choosed_basket)
        var narx = view.findViewById<TextView>(R.id.choosed_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false))
    }

    override fun getItemCount(): Int {
        return b.size
    }


    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a = b[position]
        holder.narx.text = "$ " + a.price.toString() + ".00"
        holder.title.text = a.title
        holder.reyting.text = a.rating.toString()
        holder.rasm.load(a.thumbnail)
        holder.korzina.setOnClickListener {
            Log.d("Wishlist", "onBindViewHolder: $b")
            b.remove(a)
            mySharedPreferences.setSelectedCarsList(b)
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener {
            onPressed.onPressed(a)
        }
    }


    interface OnPressed{
        fun onPressed(product: Product)
    }
}