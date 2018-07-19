/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-18 18:26:50
 * @Descrition:  代理模式 Structural_Agent.java
 * Provide a surrogate or placeholder for another object to control access to it.
 */

// =====================================================
// @Descrition:  通用代码模块
// =====================================================
// 状态模式、策略模式、访问者模式本质上是在更特殊的场合采用了代理模式
 public interface Subject {
 	public void request();
 }

// 代理是有个性的，完全可以在原业务功能的基础上再实现其他接口
// 为真实对象预处理消息，过滤消息，消息转发，事后处理消息等
public interface IProxy {
	protected void before();
	protected void after();
}

 public class RealSubject implements Subject {
 	@override
 	public void request() {
 		// 实现业务逻辑
 	}
 }

 public class Proxy implements Subject {
 	private Subject subject = null;

 	public Proxy(){
 		this.subject = new Proxy();
 	}	
 	public Proxy(Subject subject){
 		this.subject = subject;
 	}

 	@override
 	public void request(){
 		this.before();
 		this.subject.request();
 		this.after();
 	}

 	@override
 	protected void before(){
 		// 预处理
 	}
 	@override
 	protected void after(){
 		// 善后处理
 	}
 }

 // =====================================================
 // @Descrition:  静态代理示例代码
 // =====================================================
 // 普通代理：通过代理找真实角色
 // 强制代理：强制通过真实角色找他指定的代理，才能访问真实角色的方法

// 接口
 public interface IGamePlayer {
 	public boolean login(String user, String password);
 	public void killBoss();
 	public void upgrade();

 	// // 强制代理需要增加一个获取代理的方法
 	// public IGamePlayer getProxy();
 }

// 真实角色 
 public class GamePlayer implements IGamePlayer {
 	private String name = "";

 	// 通过构造函数限制谁能创建对象，并同时传递姓名
 	public GamePlayer(IGamePlayer gamePlayer, String name) throws Exception{
 		if (gamePlayer == null) {
 			throw new Exception("不能创建真实角色！");
 		} else {
 			this.name = name;
 		}
 	}

 	public boolean login(String user, String password){
 		System.out.println("登录名为" + user + "的用户 " + this.name + " 登录成功！")
 		// 强制模式的所有方法 执行业务代码前需要判断是否为代理访问
 		// if(this.isProxy()) {
 		// 	// 执行上边的业务代码
 		// }else{
 		// 	System.out.println("请使用代理访问！");
 		// }
 	}
 	public void killBoss(){
 		System.out.println(this.name + "在打怪！");
 		// 强制模式的所有方法 执行业务代码前需要判断是否为代理访问
 		// if(this.isProxy()) {
 		// 	// 执行上边的业务代码
 		// }else{
 		// 	System.out.println("请使用代理访问！");
 		// }
 	}
 	public void upgrade(){
 		System.out.println(this.name + "又升了一级！");
 		// 强制模式的所有方法 执行业务代码前需要判断是否为代理访问
 		// if(this.isProxy()) {
 		// 	// 执行上边的业务代码
 		// }else{
 		// 	System.out.println("请使用代理访问！");
 		// }
 	}

 	// // 强制代理需要通过真实对象找到他指定的代理
 	// private IGamePlayer proxy = null;
 	// public IGamePlayer getProxy(){
 	// 	this.proxy = new GamePlayerProxy(this);
 	// 	return this.proxy;
 	// }

 	// 强制代理需要的所有函数需要判断是否为代理访问
 	// private boolean isProxy(){
 	// 	if (this.proxy == null){
 	// 		return false;
 	// 	}else{
 	// 		return true;
 	// 	}
 	// }
 }

// 代理者
public class GamePlayerProxy implements IGamePlayer {
	private IGamePlayer gamePlayer = null;

	// 普通代理模式，通过代理获取真实对象
	public GamePlayerProxy(String name){
		try {
			gamePlayer = new GamePlayer(this,name);
		} catch( Exception e) {
			e.printStackTrace();
		}
	}
	// // 强制代理模式的构造函数需要传入真实对象实例
	// // 上面的构造函数需改为下述函数
	// public GamePlayerProxy(GamePlayer gamePlayer){
	// 	this.gamePlayer = gamePlayer;
	// }

	public boolean login(String user, String password){
		this.gamePlayer.login(user,password);
	}
	public void killBoss(){
		this.gamePalyer.killBoss();
	}
	public void upgrade(){
		this.gamePlayer.upgrade();
	}

	// // 强制代理需要增加一个获得代理的方法
	// public IGamePlayer getProxy(){
	// 	return this;
	// }
}

// 测试客户端
public class Client {
	public static void main(String[] args) {
		// 普通模式时通过代理访问真实对象
		IGamePlayer proxy = new GamePlayerProxy("zhuguangyuan");
		proxy.login("zhuguangyuan","2727227");
		proxy.killBoss();
		proxy.upgrade();

		// // 直接通过真实对象也访问不了：
		 	// IGamePalyer player = new GamePlayer("zhuguangyuan");
			// player.login("zhuguangyuan","2727227");
			// player.killBoss();
			// player.upgrade();
			// 上述访问都会提示需通过代理来访问

		// // 强制模式时，通过创建代理来访问，也访问不了
			// IGamePlayer player = new GamePlayer("zhuguangyuan");
			// IGamePlayer proxy = new GamePlayerProxy(player);
			// proxy.login("zhuguangyuan","2727227");
			// proxy.killBoss();
			// proxy.upgrade();
			// 也提示需通过代理访问，原因是创建的代理并不是真实对象指定的代理

		// // 只有创建真实对象且取得真实对象指定的代理，才能正常访问：
			// IGamePlayer player = new GamePlayer("zhuguangyuan");
			// IGamePlayer proxy = player.getProxy();
			// proxy.login("zhuguangyuan","2727227");
			// proxy.killBoss();
			// proxy.upgrade();
	}
}


// =====================================================
// @Descrition:  动态代理
// 实现阶段不同关心代理谁，运行阶段才指定代理哪个对象
// 面向切面编程AOP(Aspect Oriented Programming)的核心机制
// AOP:对于日志、事务、权限等可以在系统设计阶段不用考虑，
//     在设计后通过AOP的方式切过去
// =====================================================

// JDK提供的动态代理接口
// InvocationHandler 接口

// 动态代理类
public class GamePlayIH implements InvocationHandler {
	Class cls = null;
	Object obj = null;

	public GamePlayIH(Object obj){
		this.obj = obj;
	}

	// 完成对被代理对象的方法的调用
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(this.obj, args);

		// 增加真实对象功能之外的提示，可以在动态代理类中完成
		// 以下为登录提示
		if (method.getName().equalsIgnoreCase("login")){
			System.out.println("仿佛有人在背后说我帅！");
		}

		return result;
	}
}

// 测试客户端
public class Client {
	public static void main(String[] args) {
		IGamePlayer player = new GamePlayer("zhuguangyuan");
		ClassLoader classLoader = player.getClass().getClassLoader();
		InvocationHandler handler = new GamePlayerIH(player);

		IGamePlayer proxy = (IGamePlayer)Proxy.newProxyInstance(
			classLoader,
			player.getClass().getInterfaces(),
			handler);
		proxy.login("zhuguangyuan","2727227");
		proxy.killBoss();
		proxy.upgrade();
	}
}


// =====================================================
// @Descrition:  动态代理的通用代码
// =====================================================
// 两条独立发展的线路：
	// 动态代理类实现代理的职责，
	// 业务逻辑类Subject实现业务逻辑功能，
	// 两者之间没有必然的相互耦合关系
// 通知Advice从另一个切面切入，最终在高层模块即Client中进行耦合
// 完成逻辑的封装任务。

// (1)Aspect(切面):通常是一个类，里面可以定义切入点和通知
// (2)JointPoint(连接点):程序执行过程中明确的点，一般是方法的调用
// (3)Advice(通知):AOP在特定的切入点上执行的增强处理，有before,after,afterReturning,afterThrowing,around
// (4)Pointcut(切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
// (5)AOP代理：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类


public interface Subject {
	public void doSomething(String str);
}
public class RealSubject implements Subject {
	@override 
	public void doSomething(String str){
		System.out.println("doSomething ..." + str);
	}
}

public interface IAdvice {
	public void exec();
}
public class BeforeAdvice implements IAdvice {
	public void exec(){
		System.out.println("我是前置通知，物品被执行了");
	}
}


public class MyInvocationHandler implements InvocationHandler {
	private Object target = null;
	public MyInvocationHandler(Object obj){
		this.target = obj;
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		return method.invoke(this.target, args);
	}
}


public class DynamicProxy<T> {
	public static <T> newProxyInstance(
			ClassLoader classLoader,
			Class<?>[] interfaces,
			MyInvocationHandler handler
		){
		if (true) {
			(new BeforeAdvice()).exec();
		}
		return ()Proxy.newProxyInstance(classLoader,interfaces,handler);
	}
}


// 测试客户端
public class Client {
	public static void main(String[] args) {
		Subject subject = new RealSubject();
		InvocationHandler handler = new MyInvocationHandler(subject);
		Subject proxy = DynamicProxy.newProxyInstance(
				subject.getClass().getClassLoader(),
				subject.getClass().getInterfaces(),
				handler);
		proxy.doSomething("finish!");	
	}
}