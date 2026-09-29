package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val aboutContent = findViewById<TextView>(R.id.aboutContent)
        aboutContent.text = """حول التطبيق

تطبي�� توصيل الطعام
الإصدار: 1.0.0
بناء: 1

وصف التطبيق:
تطبيق متخصص في توصيل الطعام من المطاعم المتعاقدة معنا في جميع محافظات العراق.

المميزات:
• البحث عن المطاعم القريبة
• تتبع الطلبات
• عروض وتخفيضات حصرية
• دعم عملاء 24/7
• 13 لغة مختلفة

المطورون:
HakmeDev Team

شكراً لاستخدامك تطبيقنا!
        """.trimIndent()
    }
}
