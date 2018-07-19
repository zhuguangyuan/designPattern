# designPattern
java 设计模式
- 创建型模式
- 结构型模式
	- 代理模式
		- 定义
			- 代理对象存在的价值主要在于拦截对真实业务对象的访问
			- 代理对象应该具有和目标对象相同的方法
		- java中的代理
			- java.lang.reflect.Proxy
				static Object newProxyInstance(
					ClassLoader loader, 		// 用哪个类加载器
					Class<T>[] interfaces,		// 哪个对象的代理对象
					InvocationHandler handler 	// 代理对象要做什么
					)
			- 上述方法的 interfaces 参数说明，要生成代理的真实对象，应该是实现了某些接口的类
				- 这些接口定义了这个对象所具有的行为
			- InvocationHandler接口只定义了一个invoke方法，因此对于这样的接口，我们不用单独去定义一个类来实现该接口，
	         	- 而是直接使用一个匿名内部类来实现该接口，
	         	new InvocationHandler() {
	         		Object object = new RealSubject();// 代理者要代理的对象
	         		// 客户端调用的所有真实对象的方法都经过此方法的过滤
	         		// 开发者可以在此检查用户权限，动态为某个对象增加额外功能等
	         		public Object invoke(Proxy proxy, Method method, String[] args) throws Throwable {
	         			return (Object) method.invoke(this.object, args);
	         		}
	         	}
	    - 应用实例
	   		- 在字符过滤器中使用动态代理解决字符乱码问题
	   		- 在字符过滤器中使用动态代理压缩服务器响应的内容后再传输到客户端
	   		- [实例代码链接](https://www.cnblogs.com/xdp-gacl/p/3971367.html) 
	   		- AOP编程
	   		- 拦截器原理
	- 装饰器模式
		- 

- 行为型模式