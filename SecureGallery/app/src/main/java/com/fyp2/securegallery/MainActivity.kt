package com.fyp2.securegallery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp2.securegallery.adapter.ThumbnailAdapter
import com.fyp2.securegallery.util.Storage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Sign out button
        val btnSignOut: Button = findViewById<Button>(R.id.btnSignOut)
        btnSignOut.setOnClickListener {
            // Clear user existence flag
            Storage().saveExist(false)

            // Go to registration screen
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish() // close MainActivity
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val thumbnails = listOf(
            "https://picsum.photos/id/22/367/267",
            "https://picsum.photos/id/23/367/267",
            "https://picsum.photos/id/24/367/267",
            "https://picsum.photos/id/25/367/267",
            "https://picsum.photos/id/26/367/267",
            "https://picsum.photos/id/27/367/267",
            "https://picsum.photos/id/28/367/267",
            "https://picsum.photos/id/29/367/267",
            "https://picsum.photos/id/30/367/267",
            "https://picsum.photos/id/31/367/267",
            "https://picsum.photos/id/32/367/267",
            "https://picsum.photos/id/33/367/267",
            "https://picsum.photos/id/34/367/267",
            "https://picsum.photos/id/35/367/267",
            "https://picsum.photos/id/36/367/267",
            "https://picsum.photos/id/37/367/267",
            "https://picsum.photos/id/38/367/267",
            "https://picsum.photos/id/39/367/267",
            "https://picsum.photos/id/40/367/267",
            "https://picsum.photos/id/41/367/267",
            "https://picsum.photos/id/42/367/267",
            "https://picsum.photos/id/43/367/267",
            "https://picsum.photos/id/44/367/267",
            "https://picsum.photos/id/45/367/267",
            "https://picsum.photos/id/46/367/267",
            "https://picsum.photos/id/47/367/267",
            "https://picsum.photos/id/48/367/267",
            "https://picsum.photos/id/49/367/267",
            "https://picsum.photos/id/50/367/267",
            "https://picsum.photos/id/51/367/267",
            "https://picsum.photos/id/52/367/267",
            "https://picsum.photos/id/53/367/267",
            "https://picsum.photos/id/54/367/267",
            "https://picsum.photos/id/55/367/267",
            "https://picsum.photos/id/56/367/267",
            "https://picsum.photos/id/57/367/267",
            "https://picsum.photos/id/58/367/267",
            "https://picsum.photos/id/59/367/267",
            "https://picsum.photos/id/60/367/267",
            "https://picsum.photos/id/61/367/267",
            "https://picsum.photos/id/62/367/267",
            "https://picsum.photos/id/63/367/267",
            "https://picsum.photos/id/64/367/267",
            "https://picsum.photos/id/65/367/267",
            "https://picsum.photos/id/66/367/267",
            "https://picsum.photos/id/67/367/267",
            "https://picsum.photos/id/68/367/267",
            "https://picsum.photos/id/69/367/267",
            "https://picsum.photos/id/70/367/267",
            "https://picsum.photos/id/71/367/267"
        )

        val adapter = ThumbnailAdapter(thumbnails)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)
    }
}