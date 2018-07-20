/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-18 18:36:51
 * @Descrition:  装饰模式 Structural_Decorator.java
 * Attach additional responsibilites to an object dynamically
 * keeping the same interface.Decorator provide a flexible 
 * alternative to subclassing for extending functionality.
 */
// =====================================================
// @Descrition:  通用代码
// =====================================================
// ● 装饰类和被装饰类可以独立发展，而不会相互耦合。换句话说，Component类无须知
// 道Decorator类，Decorator类是从外部来扩展Component类的功能，而Decorator
// 也不用知道具体的构件。
// ● 装饰模式是继承关系的一个替代方案。我们看装饰类Decorator，不管装饰多少层，
// 返回的对象还是Component，实现的还是is-a的关系。而且类继承是静态增加功能，装饰
// 则是动态增加功能。
// ● 装饰模式可以动态地扩展一个实现类的功能。比如 Father-Son-GrandSon,要在Son中
// 增加功能，此时为避免对GrandSon的影响，应该考虑装饰模式而不是修改Son类。
// ● 缺点是复杂，应该避免多层装饰。

// 抽象基础组件类
public abstract class Component {
	public abstract void operate();
}
public class ConcreteComponent extends Component {
	@override 
	public void operate(){
		System.out.println("do something ...");
	}
}

// 装饰者抽象类，实现了基础组件
public abstract class Decorator extends Component {
	private Component component = null;
	public Decorator(Component component){
		this.component = component;
	}

	@override
	public void operate(){
		this.component.operate();
	}
}
// 具体装饰类1
public class ConcreteDecorator1 extends Decorator {
	private Component component = null;
	public ConcreteDecorator1(Component component){
		this.component = component;
	}

	@override
	public void operate(){
		this.method1();
		super.operate();
	}
	private void method1(){
		System.out.println("具体装饰类1增加了装饰。。。");
	}
}
// 具体装饰类2
public class ConcreteDecorator2 extends Decorator {
	private Component component = null;
	public ConcreteDecorator2(Component component){
		this.component = component;
	}

	@override
	public void operate(){
		this.method2();
		super.operate();
	}
	private void method2(){
		System.out.println("具体装饰类2增加了装饰...");
	}
}

// 测试客户端
public class Client {
	public static void main(String[] args) {
		Component component = new ConcreteComponent();

		Decotator decorator1 = new ConcreteDecorator1(component);
		Decorator decorator2 = new ConcreteDecorator2(component);
		component.operate();
	}
}