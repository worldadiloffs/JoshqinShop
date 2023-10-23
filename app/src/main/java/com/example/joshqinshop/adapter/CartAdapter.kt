package com.example.joshqinshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.joshqinshop.R
import com.example.joshqinshop.model.Cart
import com.example.joshqinshop.model.Comment
import com.example.joshqinshop.model.Product

class CartAdapter(var productList: MutableList<Cart>): RecyclerView.Adapter<CartAdapter.CartAdapter>() {

    class CartAdapter(item: View): RecyclerView.ViewHolder(item){
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
//        val productImg: ImageView = item.findViewById(R.id.product_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return CartAdapter(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: CartAdapter, position: Int) {
        val itemsViewModel = productList[position]
        holder.productName.text = itemsViewModel.title
        holder.productPrice.text = itemsViewModel.price.toString()
//        if (itemsViewModel.images[0] != ""){
//            holder.productImg.load(itemsViewModel.images[0]){
//                placeholder(R.drawable.ic_launcher_background)
////                kotlin.error(androidx.appcompat.R.drawable.abc_btn_radio_material)
//            }
//        }
    }
}