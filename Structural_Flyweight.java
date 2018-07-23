/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-22 22:49:07
 * @Descrition:  享元模式，池技术的重要实现技术
 * Use sharing to support large numbers of fine-grained objects efficiently.
 * 使用共享对象可以有效地支持大量细粒度的对象
 */
// 享元模式的优点和缺点
	// 享元模式是一个非常简单的模式，它可以大大减少应用程序创建的对象，降低程序内存
	// 的占用，增强程序的性能，但它同时也提高了系统复杂性，需要分离出外部状态和内部状
	// 态，而且外部状态具有固化特性，不应该随内部状态改变而改变，否则导致系统的逻辑混
	// 乱。
// 享元模式的使用场景
	// 在如下场景中则可以选择使用享元模式。
	// ● 系统中存在大量的相似对象。
	// ● 细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关，也就是说对象没
	// 有特定身份。
	// ● 需要缓冲池的场景。
// 对象状态分类
	// 内部状态:存储在对象内部且不会随着环境改变而改变。
	// 	可以作为一个对象的动态附加信息，不必直接存储在某个具体对象中
	// 	属于可共享的部分。如 id 等
	// 外部状态:对象得以依赖的一个标记，随着环境的改变而改变。
// =====================================================
// @Descrition:  通用代码
// =====================================================

// 享元抽象类,即一个产品的抽象类
public abstract class Flyweight {
	protected final String extrinsic;
	// 享元角色必须接受外部状态
	public Flyweight(String extrinsic){
		this.extrinsic = extrinsic;
	}

	private String intrinsic;
	// 内部状态的getter/setter
	public String getIntrinsic(){
		return intrinsic;
	}
	public void setIntrinsic(String intrinsic){
		this.intrinsic = intrinsic;
	}

	// 业务操作
	public abstract void operate();
}

public class ConcreteFlyweight1 extends Flyweight {
	public ConcreteFlyweight1(String extrinsic){
		super(extrinsic);
	}
	public void operate(){
		// 业务逻辑
	}
}
public class ConcreteFlyweight2 extends Flyweight {
	public ConcreteFlyweight2(String extrinsic){
		super(extrinsic);
	}
	public void operate(){
		// 业务逻辑
	}
}


// 享元工厂
public class FlyweightFactory {
	// 对象容器池
	private static HashMap<String,Flyweight> pool = new HashMap<String,Flyweight>();
	// 根据外部状态获取容器池中的对象
	public static Flyweight getFlyweight(String extrinsic){
		Flyweight flyweight = null;
		if (pool.containsKey(extrinsic)){
			flyweight = pool.get(extrinsic);
		} else {
			flyweight = new ConcreteFlyweight1(extrinsic);
			pool.put(extrinsic,flyweight);
		}
		return flyweight;
	}
}

// =====================================================
// @Descrition:  实例代码
// =====================================================
// 没有使用享元模式时
public class SignInfo {
	private String id;
	private String location;
	private String subject;
	private String postAdress;

	// getter/setter 方法集
}
public class SignInfoFactory {
	public static SignInfo getSignInfo(){
		return new SignInfo();
	}
}
public class Client {
	public static void main(String[] args) {
		SignInfo signInfo = SignInfoFactory.getSignInfo();
		// 对signInfo进行其他业务处理
	}
}


// 使用享元模式
public class SignInfo4Pool extends SignInfo {
	private String Key;
	public SignInfo4Pool(String key){
		this.key = key;
	}

	// getter/setter 方法集
}
public class SignInfoFactory {
	private static HashMap<String,SignInfo> pool = new HashMap<String,SignInfo>();

	@Deprecated
	public static SignInfo(){
		return new SignInfo();
	}
	public static SignInfo getSignInfo(String key){
		SignInfo result = null;
		if(!pool.containKey(key)){
			System.out.println("---- 建立对象，并放置到对象池中 ---");
			result = new SignInfo4Pool(key);
		}else{
			System.out.println("---- 直接从对象池中取得对象 ----");
			result = pool.get(key);
		}
		return result;
	}
}

public class Client {
	public static void main(String[] args) {
		int lenOfSubject = 4;
		int lenOfLocation = 30;
		for(int i = 0; i < lenOfSubject; i ++){
			String subject = "科目" + i;
			for (int j = 0; j < lenOfLocation ; j ++) {
				String key = subject + "考试地点" + j;
				SignInfoFactory.getSignInfo(key);
			}
		}

		SignInfo signInfo = SignInfoFactory.getSignInfo("科目1考试地点1");
	}
}


// =====================================================
// @Descrition:  展示出线程安全问题 的代码
// 解决方案： 对象池中的享元对象尽量多，知道满足业务需求为止？？？
// =====================================================

// SignInfoFactory里新建对象改为 new SignInfo();

// 创建一个多线程
public class MultiThread extends Thread {
	private SignInfo signInfo;
	public MultiThread(SignInfo signInfo){
		this.signInfo = signInfo;
	}
	public void run(){
		if (!signInfo.getId().equals(signInfo.getLocation())){
			System.out.println("编号： " + signInfo.getId());
			System.out.println("考试地址： " + signInfo.getLocation());
			System.out.println("线程不安全了！");
		}
	}
}

public class Client {
	public static void main(String[] args) {
		SignInfoFactory.getSignInfo("科目1");
		SignInfoFactory.getSignInfo("科目2");
		SignInfoFactory.getSignInfo("科目3");
		SignInfoFactory.getSignInfo("科目4");

		SignInfo signInfo = SignInfoFactory.getSignInfo("科目2");
		while(true){
			signInfo.setId("zhuguangyuan");
			signInfo.setLocation("zhuguangyuan");
			(new MultiThread(signInfo)).start();

			signInfo.setId("brucezhu");
			signInfo.setLocation("brucezhu");
			(new MultiThread(signInfo)).start();
		}
	}
}


// =====================================================
// @Descrition:  尽量用java基本类型作为外部状态，而不是用自己编写的类
// 此例对比两者执行的耗时来说明性能问题
// 用java基本类型作为外部状态性能明显好于自己编写的类作为外部状态的情况
// =====================================================
// 外部状态类
public class ExtrinsicState {
	private String subject;
	private String location;

	// subject和location 的 getter/setter 方法，略

	// 注意ExtrinsicState要作为HashMap中的key 必须覆写
	// equals 和 hashCode两个方法，否则会造成通过键值搜索失败的情况
	// 如 map.get(Object)、map.contains(Object) 返回失败结果
	@Override
	public boolean equals(Object obj){
		if (obj instanceof ExtrinsicState){
			boolean isEquals = false;
			ExtrinsicState state = (ExtrinsicState)obj;
			isEquals = state.getLocation().equals(this.location) 
			isEquals = isEquals && state.getSubject().equals(this.subject);
			return isEquals;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return subject.hashCode() + location.hashCode();
	}
}

// SignInfo 类中引入 ExtrinsicState 作为外部状态对象

// 享元工厂
public class SignInfoFactory {
	private static HashMap<ExtrinsicState,SignInfo> pool = new HashMap<ExtrinsicState,SignInfo>();
	public static SignInfo getSignInfo(ExtrinsicState key){
		SignInfo result = null;
		if (!pool.containsKey(key)){
			result = new SignInfo(key);
			pool.put(key,result);
		} else {
			result = pool.get(key);
		}
		return result;
	}
}

// 测试客户端
public class Client {
	public static void main(String[] args) {
		ExtrinsicState state1 = new ExtrinsicState();
		state1.setSubject("科目1");
		state1.setLocation("杭州");
		SignInfoFactory.getSignInfo(state1);

		ExtrinsicState state2 = new ExtrinsicState();
		state2.setSubject("科目1");
		state2.setLocation("杭州");
		long currentTime = System.currentTimeMillis()
		for (int i=0; i < 1000000 ; i ++) {
			SignInfoFactory.getSignInfo(state2);
		}
		long tailTime = System.currentTimeMillis();
		System.out.println("执行时间1：" + (tailTime - currentTime) + "ms");	

		String ke1 = "科目1杭州";
		String ke2 = "科目1杭州";
		SignInfoFactory.getSignInfo(key1);
		long currentTime2 = System.currentTimeMillis();
		for (int i = 0; i < 1000000 ; i ++) {
			SignInfoFactory.getSignInfo(key2);
		}
		long tailTime2 = System.currentTimeMillis();
		System.out.println("执行时间2：" + (tailTime2 - currentTime2) + "ms");
	}
}