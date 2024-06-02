package com.example.recyclingapp

import Blog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BlogsActivity : AppCompatActivity() {


    private lateinit var mainButton: RelativeLayout
    private lateinit var profileButton: LinearLayout
    private lateinit var blogButton: LinearLayout
    private lateinit var settings : Button

    private lateinit var recyclerViewBlogs: RecyclerView
    private lateinit var blogsAdapter: BlogsAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blogs_scree)

        mainButton = findViewById(R.id.main_button)
        profileButton = findViewById(R.id.profile_button)
        blogButton = findViewById(R.id.blogs_button)

        settings = findViewById(R.id.setting_button)


       // Initialize RecyclerView
                recyclerViewBlogs = findViewById(R.id.recycler_view_blogs)
        recyclerViewBlogs.layoutManager = LinearLayoutManager(this)

        // Initialize and set adapter
        blogsAdapter = BlogsAdapter(getSampleBlogs()) // Replace with your data source
        recyclerViewBlogs.adapter = blogsAdapter



        profileButton.setOnClickListener {
            val intent = Intent(this@BlogsActivity, UserProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        mainButton.setOnClickListener {
            val intent = Intent(this@BlogsActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        settings.setOnClickListener() {
            val intent = Intent(this@BlogsActivity, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }




    }
    private fun getSampleBlogs(): List<Blog> {
        // Replace this with your actual data source (e.g., fetching from a server)
        val blogs = mutableListOf<Blog>()
        blogs.add(Blog("Article 1", "Description of Article 1"))
        blogs.add(Blog("Article 2", "Description of Article 2"))
        blogs.add(Blog("Article 3", "Description of Article 3"))
        // Add more articles as needed
        return blogs
    }
}