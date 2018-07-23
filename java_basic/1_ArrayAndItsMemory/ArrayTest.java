/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-23 16:23:02
 * @Descrition:  java数组及其内存管理
 * 所有局部变量都是存储在各自方法的栈内存中的，但是引用型变量指向的对象则是存储在堆内存中的。
 * 堆内存中的对象在没有引用变量指向的时候是不能访问的，此时会成为不可访问的对象。
 */
public class ArrayTest {
	public static void main(String[] args){
		testInitStaticAndDynamic();
		testInitBasicAndReference();
		testTwoDimensionArray();
	}

	// =====================================================
	// @Descrition:  静态初始化和动态初始化
	// =====================================================
	private void testInitStaticAndDynamic(){
		// 采用静态初始化方式初始化第一个数组
		String[] books = new String[]{
			"疯狂java讲义",
			"轻量级java EE 企业应用实践",
			"疯狂Ajax讲义",
			"疯狂XML讲义",
		}

		// 采用静态初始化的简化形式初始化第二个数组
		String[] names = {
			"孙悟空",
			"猪八戒",
			"白骨精",
		}

		// 采用动态初始化初始化第三个数组
		String[] strArr = new String[5];

		// 访问三个数组的长度
		System.out.println("第一个数组的长度是 " + books.length);
		System.out.println("第二个数组的长度是 " + names.length);
		System.out.println("第三个数组的长度是 " + strArr.length);

		// 以下语句让数组变量books指向的堆内存中的数组对象发生改变
		books = names;
		// 以下语句让数组变量strArr指向的堆内存中的数组对象发生改变
		strArr = names;
		// 上述两个语句执行之后，main函数栈区中的数组变量 books，names，strArr都指向了同一个堆内存区域
	}

	// =====================================================
	// @Descrition:  基本类型数组初始化和引用类型数组初始化
	// =====================================================
	private void testInitBasicAndReference(){
		// 定义一个int[] 类型是数组变量// 在栈区中生成一个引用变量a,指向null
		// 此时a 未指向真实对象，所以不可访问真实对象的属性或者方法，否则会空指针错误
		// 但是像这样 System.out.println(a); 则不会有任务问题
		int[] a; 

		// 静态初始化数组//堆内存中分配了一块连续区域，存储这4个int型的对象，同时a指向第一个元素
		// 此时可以调用 a.length 或者 a[2] 来访问对象的属性及方法了
		a = new int[]{1,2,3,4};

		// 测试引用类型数组初始化
		class Person {
			private int age;
			private double height;
			public Person(int age, double height){
				this.age = age;
				this.height = height;
			}

			public void printInfo(){
				System.out.println("我的年龄是：" + this.age + "我的身高是：" + this.height);
			}
		}

		{
			// 定义一个Person类型数组 students,在本方法的栈区中生成一个students引用变量，指向null
			Person[] students;
			// 动态初始化,堆区生成两个引用对象students[0] students[1]，指向都为null
			students = new Student[2];
			System.out.println("students所引用数组的长度是" + students.length);

			Person zhang = new Person(23,179);
			Person lee = new Person(25,180);
			student[0] = zhang;

			zhang.printInfo();
			student[0].printInfo();
		}
	}

	// =====================================================
	// @Descrition:  测试多维数组
	// =====================================================
	private void testTwoDimensionArray(){
		// 定义一个二维数组
		int[][] a;
 		// 动态初始化其第一维
 		a = new int[4][];
 		for (int i = 0; i < a.length ; i ++) {
 			System.out.println("a[i]" + a[i]);
 		}

 		// 初始化a数组的第一个元素
 		a[0] = new int[2];
 		a[0][1] = 666;
 		for (int i = 0; i < a[0].length ; i ++) {
 			System.out.println("a[0][i]" + a[0][i]);
 		}
	}
 }
