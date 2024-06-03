package com.example.recyclingapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BlogsActivity : AppCompatActivity() {


    private lateinit var mainButton: RelativeLayout
    private lateinit var profileButton: LinearLayout
    private lateinit var blogButton: LinearLayout
    private lateinit var settings : Button

    private lateinit var recyclerViewBlogs: RecyclerView
    private lateinit var blogsAdapter: BlogsAdapter

    private lateinit var blogsScreen: LinearLayout

    private lateinit var webImage: ImageView
    private lateinit var webText: TextView

    private lateinit var fadeIn: Animation
    private lateinit var fadeOut: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blogs_screen)

        mainButton = findViewById(R.id.main_button)
        profileButton = findViewById(R.id.profile_button)
        blogButton = findViewById(R.id.blogs_button)
        settings = findViewById(R.id.setting_button)
        blogsScreen = findViewById(R.id.blogs_screen_1)

        webImage = findViewById(R.id.web_design)
        webImage.setBackgroundResource(R.drawable.main_icon_web_press)
        webText = findViewById(R.id.blogs)
        webText.setTextColor(Color.parseColor("#547E38"))

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        overridePendingTransition(0, 0) // non animation
        blogsScreen.startAnimation(fadeIn)

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
            overridePendingTransition(0, 0); // non animation
        }




    }
    private fun getSampleBlogs(): List<Blog> {
        // Replace this with your actual data source (e.g., fetching from a server)
        val blogs = mutableListOf<Blog>()
        blogs.add(Blog(getString(R.string.blog1_title), getString(R.string.blog1_description), "https://www.nytimes.com/2024/04/05/climate/plastic-recycling.html"))

        blogs.add(Blog(getString(R.string.blog2_title), getString(R.string.blog2_description), "https://www.epa.gov/recycle/reducing-waste-what-you-can-do"))

        blogs.add(Blog(getString(R.string.blog3_title), getString(R.string.blog3_description), "http://example.com/article3"))

        blogs.add(Blog(getString(R.string.blog4_title), getString(R.string.blog4_description), "https://www.greenbiz.com/article/health-and-climate-top-consumers-concerns-about-food-system-report-finds"))

        blogs.add(Blog(getString(R.string.blog5_title), getString(R.string.blog5_description), "https://earth911.com/how-to-recycle/recycling-mystery-underwear/"))

        blogs.add(Blog(getString(R.string.blog6_title), getString(R.string.blog6_description), "https://www.epa.gov/recycle/preventing-wasted-food-home"))
        blogs.add(Blog("", "", ""))
        return blogs

    }
}
data class Blog (
    val title: String,
    val description: String,
    val link: String
)
