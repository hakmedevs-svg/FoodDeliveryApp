# FoodDeliveryApp

تطبيق Android بسيط لتوصيل الطعام يوضح المطاعم القريبة وفقاً للموقع الحالي للمستخدم، مع تفاصيل المطعم وتقييمه وسعر التوصيل ووقت التوصيل.

## مميزات المشروع
- شاشة رئيسية تعرض قائمة المطاعم القريبة
- استخدام صلاحية الموقع للمستخدم
- حساب المسافة التقريبية بين المستخدم والمطعم
- تفاصيل المطعم عند النقر عليه
- تصميم مناسب لتطبيقات الطلبات والتوصيل

## المتطلبات
- Android Studio Ladybug أو أحدث
- JDK 17
- Android SDK

## التشغيل
1. افتح المشروع في Android Studio.
2. دع Android Studio يقوم بمزامنة Gradle.
3. اختر جهاز حقيقي أو محاكي.
4. اضغط Run.

## هيكل المشروع
- app/src/main/java/com/example/fooddelivery
- app/src/main/res/layout
- app/src/main/res/values
- app/src/main/AndroidManifest.xml

## ملاحظات
هذا التطبيق هو نسخة أولية (MVP) ومصممة لتكون قاعدة جيدة لتوسيعها لاحقاً بإضافة:
- Firebase Auth
- Firebase Firestore / Realtime Database
- خرائط Google Maps
- نظام الدفع
- تتبع الطلبات
