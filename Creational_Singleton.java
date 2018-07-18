/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-17 23:06:22
 * @Descrition:  单例模式
 */
// =====================================================
// @Descrition:  应用场景举例
// =====================================================
// ● 要求生成唯一序列号的环境；
// ● 在整个项目中需要一个共享访问点或共享数据，例如一个Web页面上的计数器，可以
// 不用把每次刷新都记录到数据库中，使用单例模式保持计数器的值，并确保是线程安全的；
// ● 创建一个对象需要消耗的资源过多，如要访问IO和数据库等资源；
// ● 需要定义大量的静态常量和静态方法（如工具类）的环境，可以采用单例模式（当
// 然，也可以直接声明为static的方式）。
// =====================================================
// @Descrition:  注意事项
// =====================================================
// 考虑对象复制问题
// 	在Java中，对象默认是不可以被复制的，若实现了
// 	Cloneable接口，并实现了clone方法，则可以直接通过对象复制方式创建一个新对象，对象
// 	复制是不用调用类的构造函数，因此即使是私有的构造函数，对象仍然可以被复制。在一般
// 	情况下，类复制的情况不需要考虑，很少会出现一个单例类会主动要求被复制的情况，解决
// 	该问题的最好方法就是单例类不要实现Cloneable接口。
 

/**
* 饿汉式：类加载的时候就创建实例
**/
class Single {
	// 加载类的时候就初始化单例
	private static Single s = new Single();
	// 私有的构造函数
	private Single() {

	}

	// 获取单例的公共接口
	public static Single getInstance(){
		return s;
	}
}



/**
* 懒汉式：调用的时候才创建实例
* 1.线程不安全的简单懒汉
* 2.简单加锁的懒汉
* 3.Double-check Locking的懒汉
* 4.基于静态内部类的懒汉
**/
class Single {
	private static Single s = null;
	private Single(){

	}

	// 1.线程不安全的懒汉
	public Single getInstance(){
		if (s == null){
			s = new Single();
		}
		return s;
	}

	// 2.简单加锁，但是每次取实例都需要判断锁且进入临界区
	public Single getInstance(){
		synchronized(Single.class){
			if (s == null){
				s = new Single();
			}
		}
		return s;
	}

	// 3.Double-check locking 的懒汉
	// 由于可能指令重排序，可能返回一个不完整的实例
	// 解决方法是将实例s 设为 private static volatile s = null;
	public Single getInstance(){
		if (s == null) {
			synchronized(Single.class){
				if (s == null) {
					s = new Single();
				}
			}
		}
		return s;
	}

	// 4.基于内部类的懒汉
	// private static Single s = null; 改为
	// private static class Inner {
	// 	final static Single s = null;
	// }
	// 继而添加下面的代码
	// public Single getInstance(){
	// 	return Inner.s;
	// }

	/**
	静态内部类的相关测试
	public class Outer {  
	    static {  
	        System.out.println("load outer class...");  
	    }  
	      
	    //静态内部类  
	    static class StaticInner {  
	        static {  
	            System.out.println("load static inner class...");  
	        }  
	          
	        static void staticInnerMethod() {  
	            System.out.println("static inner method...");  
	        }  
	    }  
	          
	    public static void main(String[] args) {  
	        Outer outer = new Outer();      //此刻其内部类是否也会被加载？  
	         System.out.println("===========分割线===========");  
	        Outer.StaticInner.staticInnerMethod();      //调用内部类的静态方法  
	    }  
	} 

	运行结果： 
	load outer class... 
	==========分割线========== 
	load static inner class... 
	static inner method... 

    调用构造方法时，外部类Outer被加载，但这时其静态内部类StaticInner却未被加载。
    直到调用该内部类的静态方法（在分割线以下），StaticInner才被加载。可以做类似的实验验证非静态内部类的情况。 
    
    结论：加载一个类时，其内部类不会同时被加载。一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。 

    根据内部类不会在其外部类被加载的同时被加载的事实，我们可以引申出单例模式的静态内部类实现懒汉式
	**/
}