/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-22 20:43:54
 * @Descrition:  门面模式
 * Provide a unified interface to a set of interfaces in a subsystem.
 * Facade defines a higher-level interface that makes the subsystem easier to use.
 */
// 门面模式的优点
	● 减少系统的相互依赖
	想想看，如果我们不使用门面模式，外界访问直接深入到子系统内部，相互之间是一种
	强耦合关系，你死我就死，你活我才能活，这样的强依赖是系统设计所不能接受的，门面模
	式的出现就很好地解决了该问题，所有的依赖都是对门面对象的依赖，与子系统无关。
	● 提高了灵活性
	依赖减少了，灵活性自然提高了。不管子系统内部如何变化，只要不影响到门面对象，
	任你自由活动。
	● 提高安全性
	想让你访问子系统的哪些业务就开通哪些逻辑，不在门面上开通的方法，你休想访问
	到。

// 门面模式的缺点
	● 不符合开闭原则

// 门面模式的注意事项
	● 门面过大时注意按功能拆分为多个门面
	● 不同系统访问子系统的时候可以考虑提供不同的门面
	● 门面不参与子系统内的业务逻辑
		门面内的方法不能调用两个子系统的方法，因为这样是参与到了子系统中

// =====================================================
// @Descrition:  通用代码
// =====================================================
// 子系统，假设由ClassA ClassB ClassC 三个类组成
public class ClassA {
	public void doSomethingA(){

	}
}
public class ClassB {
	public void doSomethingB(){

	}
}
public class ClassC {
	public void doSomethingC(){

	}
}

// 另外的封装类来封装复杂逻辑，而不是在门面中书写逻辑
public class Context {
	private ClassA a = new ClassA();
	private ClassC c = new ClassC();

	public void complexMethod(){
		this.c.doSomethingC();
		this.a.doSomethingA();
	}
}


// 门面类，对外提供访问子系统的方法
public class Facade {
	private ClassA a = new ClassA();
	private ClassB b = new ClassB();
	private ClassC c = new ClassC();
	private Context context = new Context();

	public void methodA(){
		this.a.doSomethingA();
	}
	public void methodB(){
		this.b.doSomethingB();
	}
	public void methodC(){
		// 注意这样写 就是让门面参与了子系统的逻辑
		// 子系统需依赖门面逻辑才能被调用
		// 造成门面违反了单一职责原则(门面只是提供对外接口，不参与子系统逻辑)
		// 也破坏了子系统的封装性
		// this.c.doSomethingC();
		// this.a.doSomethingA();

		// 应该另外提供一个封装类对复杂逻辑进行封装Context
		this.context.complexMethod();
	}
}
