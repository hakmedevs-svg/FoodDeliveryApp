package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R

class PrivacyPolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        val policyContent = findViewById<TextView>(R.id.policyContent)
        policyContent.text = getPrivacyPolicyText()
    }

    private fun getPrivacyPolicyText(): String {
        return """سياسة الخصوصية

آخر تحديث: 2024

1. جمع المعلومات
نحن نجمع معلومات شخصية مثل رقم الهاتف والعنوان لتقديم خدمات توصيل الطعام.

2. استخدام المعلومات
تُستخدم معلوماتك حصرياً لـ:
- تسليم الطلبات
- تحسين خدماتنا
- التواصل معك بشأن طلباتك

3. حماية البيانات
نستخدم تشفير آمن لحماية بيانات الهاتف والعناوين.

4. الحقوق الخاصة بك
يحق لك:
- طلب حذف بياناتك
- الوصول إلى بياناتك
- تصحيح المعلومات الخاطئة

5. الاتصال بنا
إذا كان لديك أي استفسارات بخصوص سياسة الخصوصية، يرجى التواصل معنا.
        """.trimIndent()
    }
}
