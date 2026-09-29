#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_fooddelivery_FoodDeliveryApp_nativeMessage(JNIEnv *env, jobject /* obj */) {
    return env->NewStringUTF("FoodDelivery native module is ready");
}
