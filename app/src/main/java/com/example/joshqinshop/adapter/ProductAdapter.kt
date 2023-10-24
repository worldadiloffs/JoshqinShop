package com.example.joshqinshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.joshqinshop.R
import com.example.joshqinshop.model.Comment
import com.example.joshqinshop.model.Product

class ProductAdapter(var productList: MutableList<Product>, var onSelected: OnSelected): RecyclerView.Adapter<ProductAdapter.ProductAdapter>() {

    class ProductAdapter(item: View): RecyclerView.ViewHolder(item){
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productImg: ImageView = item.findViewById(R.id.product_img)
        val productRate:TextView = item.findViewById(R.id.product_rate)
        val productAddCart:ImageView = item.findViewById(R.id.add_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductAdapter(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductAdapter, position: Int) {
        val itemsViewModel = productList[position]
        holder.productName.text = itemsViewModel.title
        holder.productPrice.text = "$ " + itemsViewModel.price.toString()
        if (itemsViewModel.images[0] != ""){
            holder.productImg.load(itemsViewModel.images[0]){
                placeholder(R.drawable.ic_launcher_background)
//                kotlin.error(androidx.appcompat.R.drawable.abc_btn_radio_material)
            }
        }
        holder.productRate.text = itemsViewModel.rating.toString()

        holder.productAddCart.setOnClickListener{
            onSelected.onSelected(itemsViewModel)
        }

    }


    interface OnSelected{
        fun onSelected(product: Product)
    }
}