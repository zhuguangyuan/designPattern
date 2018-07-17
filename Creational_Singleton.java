// java设计模式-单例模式.java

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