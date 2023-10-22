package com.example.joshqinshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.joshqinshop.R
import com.example.joshqinshop.model.Comment

class CommentAdapter(var commentList: MutableList<Comment>): RecyclerView.Adapter<CommentAdapter.CustomAdapter>() {

    class CustomAdapter(item: View): RecyclerView.ViewHolder(item){
        val userName: TextView = itemView.findViewById(R.id.username)
        val text: TextView = itemView.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_commment, parent, false)

        return CustomAdapter(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CustomAdapter, position: Int) {
        val itemsViewModel = commentList[position]
        holder.userName.text = itemsViewModel.user.username
        holder.text.text = itemsViewModel.body
    }
}