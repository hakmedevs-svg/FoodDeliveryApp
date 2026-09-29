package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R

class TermsOfUseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_of_use)

        val termsContent = findViewById<TextView>(R.id.termsContent)
        termsContent.text = getTermsText()
    }

    private fun getTermsText(): String {
        return """شروط الاستخدام

آخر تحديث: 2024

1. قبول الشروط
باستخدام تطبيقنا، فإنك توافق على هذه الشروط.

2. الاستخدام المسموح به
- استخدم التطبيق لأغراض قانونية فقط
- لا تقم بنسخ أو توزيع محتوى التطبيق
- احترم حقوق الملكية الفكرية

3. المسؤولية
- نسعى لضمان دقة المعلومات ولكن لا نضمنها
- لسنا مسؤولين عن التأخيرات في التوصيل
- لا نتحمل مسؤولية الأضرار غير المباشرة

4. الحسابات المستخدمة
- أنت مسؤول عن سرية بيانات حسابك
- أخبرنا فوراً بأي نشاط غير مصرح

5. إنهاء الخدمة
يحق لنا إيقاف الخدمة للمستخدمين الذين ينتهكون هذه الشروط.

6. تعديل الشروط
قد نعدل هذه الشروط في أي وقت. الاستمرار في الاستخدام يعني قبولك للتعديلات.

7. القانون الساري
تخضع هذه الشروط لقوانين جمهورية العراق.
        """.trimIndent()
    }
}
