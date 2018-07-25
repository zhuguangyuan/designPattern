public class ImutableString {
	public static void main(String[] args) {
		// 字符串常量池中创建了一个 Hello,str指向它
		String str = "Hello";
		System.out.println(System.identityHashCode(str));

		// 字符串常量池中创建了一个 Hello,java，str指向它，
		// 但是无引用的Hello不会被回收，造成内存泄露
		str = str + "，java";
		System.out.println(System.identityHashCode(str));

		// 字符串常量池中创建了一个 Hello,java，crazyid.org， str指向它，
		// 但是无引用的Hello Hello,java都不会被回收，造成内存泄露
		str = str + "，crazyid.org";
		System.out.println(System.identityHashCode(str));
	}
}