/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-20 23:38:15
 * @Descrition:  桥梁模式
 * Decouple an abstraction from its implementation
 * so that the two can vary independently.
 */
// 为解决类继承的"强侵入"缺点而提出的设计模式
// 该模式下，实现可以不受抽象的约束，不用再绑定在一个固定的抽象层次上
// 桥梁模式的使用场景
	// ● 不希望或不适用使用继承的场景
	// 例如继承层次过渡、无法更细化设计颗粒等场景，需要考虑使用桥梁模式。
	// ● 接口或抽象类不稳定的场景
	// 明知道接口不稳定还想通过实现或继承来实现业务需求，那是得不偿失的，也是比较失
	// 败的做法。
	// ● 重用性要求较高的场景
	// 设计的颗粒度越细，则被重用的可能性就越大，而采用继承则受父类的限制，不可能出
	// 现太细的颗粒度。

// =====================================================
// @Descrition:  通用代码
// =====================================================

// 实现类
public interface Implementor {
	public void doSomething();
	public void soAnything();
} 
// 具体实现类 业务逻辑1
public class ConcreteImplementor1 implements Implementor {
	public void doSomething(){

	}
	public void doAnything(){

	}
 }
// 具体实现类2 业务逻辑2
 public class ConcreteImplementor2 implements Implementor {
 	public void doSomething(){

 	}
 	public void doAnything(){

 	}
 }


// 抽象类
public abstract class Abstraction {
	private Implementor imp;

	public Abstraction(Implementor imp){
		this.imp = imp;
	}

	public void request(){
		this.imp.doSomething();
	}
	public Implementor getImp(){
		return imp;
	}
}
// 具体抽象类
public class RefinedAbstraction extends Abstraction {
	public RefinedAbstraction(Implementor imp){
		super(imp);
	}

	public void request(){
		super.request();
		super.getImp().doAnything();
	}
}


// 测试客户端
public class Client {
	public static void main(String[] args){
		Implementor imp = new ConcreteImplementor1();
		Abstraction abs = new RefinedAbstraction(imp);
		abs.request();
	}
}



// =====================================================
// @Descrition: 实例代码 
// =====================================================
