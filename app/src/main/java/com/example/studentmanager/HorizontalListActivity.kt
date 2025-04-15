package com.example.studentmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HorizontalListActivity : AppCompatActivity() {

    private val items = listOf(
        "Item 0", "Item 1", "Item 2", "Item 3", "Item 4",
        "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"
    )

    private val thumbs = listOf(
        R.drawable.thumb0, R.drawable.thumb1, R.drawable.thumb2, R.drawable.thumb3,
        R.drawable.thumb4, R.drawable.thumb5, R.drawable.thumb6, R.drawable.thumb7,
        R.drawable.thumb8, R.drawable.thumb9, R.drawable.thumb10
    )

    private val image = listOf(
        R.drawable.wall0, R.drawable.wall1, R.drawable.wall2, R.drawable.wall3, R.drawable.wall4,
        R.drawable.wall5, R.drawable.wall6, R.drawable.wall7, R.drawable.wall8, R.drawable.wall9,
        R.drawable.wall10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.horizontal_list) // ✅ Sửa lại layout chính

        val horizontalLayout = findViewById<LinearLayout>(R.id.linear_layout)
        val imageViewLarger = findViewById<ImageView>(R.id.image_view_lager)

        for (i in items.indices) {
            val itemView = LayoutInflater.from(this).inflate(R.layout.horizontal_layout, horizontalLayout, false)
            val imageView = itemView.findViewById<ImageView>(R.id.image_view)
            val textView = itemView.findViewById<TextView>(R.id.text_view)

            imageView.setImageResource(thumbs[i])
            textView.text = items[i]

            itemView.setOnClickListener {
                imageViewLarger.setImageResource(image[i])
            }

            horizontalLayout.addView(itemView)
        }
    }
}
