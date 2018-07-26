/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-26 18:11:27
 * @Descrition:  native方法测试
 */


public class NativeTest {
	public native void sayHello();

	static {
		System.loadLibrary("HelloImpl");
	}

	public static void main(String[] args){
		new NativeTest().sayHello();
	}
}