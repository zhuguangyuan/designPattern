#include<jni.h>
#include<stdio.h>
#include<NativeTest.h>
JNIEXPORT void JNICALL java_nativeTest( JNIEnv * env, jobject thisobj){
	puts("hello,boy.good job!");
	return;
}
