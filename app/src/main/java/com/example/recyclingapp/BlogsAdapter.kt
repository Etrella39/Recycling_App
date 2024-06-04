package com.example.recyclingapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BlogsAdapter(private val blogs: List<Blog>) : RecyclerView.Adapter<BlogsAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_item, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blog = blogs[position]
        holder.title.text = blog.title
        holder.description.text = blog.description
        holder.readButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(blog.link))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = blogs.size

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.blog_title)
        val description: TextView = itemView.findViewById(R.id.blog_description)
        val readButton: Button = itemView.findViewById(R.id.read_more_button)
    }

}
