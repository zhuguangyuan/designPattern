/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-17 16:06:04
 * @Descrition:  模板方法模式，父类定义模板，子类实现模板中的抽象方法
 */	

public abstract class AbstractClass {
	// 要子类实现的基本方法
	protected abstract void method1();
	protected abstract void method2();

	// 模板方法,调用基本方法完成相关逻辑
	// 模板方法一般不能让子类重写，所以用final限定
	public final void templateMethod() {
		{
			System.out.println("其他公共代码段");
		}
		method1();
		method2();
	}
 }

 class ConcreteClass1 extends AbstractClass {
 	@override 
 	protected void method1() {
 		System.out.println("第一步的第一种实现");
 	}

 	@override
 	protected void method2() {
 		System.out.println("第二步的第一种实现");
 	}
 }

 class ConcreteClass2 extends AbstractClass {
 	@override 
 	protected void method1() {
 		System.out.println("第一步的第二种实现");
 	}

 	@override 
 	protected void method2() {
 		System.out.println("第二步的第二种实现");
 	}
 }
