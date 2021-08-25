#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_scallop_muviss_di_Network_getAPIKey(JNIEnv* env, jobject /* this */) {
    std::string api_key = "<<<PRIVATE_API_KEY>>>";
    return env->NewStringUTF(api_key.c_str());
}