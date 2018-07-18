/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-18 07:58:54
 * @Descrition:  原型模式示例
 */
// 不通过new 来产生一个对象，而是通过对象复制来实现
// 实现一个接口，然后重写clone方法，就完成了原型模式
// Creational_Propotype.java
// =====================================================
// @Descrition:  原型模式的优点
// =====================================================
// ● 性能优良
// 原型模式是在内存二进制流的拷贝，要比直接new一个对象性能好很多，特别是要在一
// 个循环体内产生大量的对象时，原型模式可以更好地体现其优点。
// ● 逃避构造函数的约束
// 这既是它的优点也是缺点，直接在内存中拷贝，构造函数是不会执行的（参见13.4
// 节）。优点就是减少了约束，缺点也是减少了约束，需要大家在实际应用时考虑。
// =====================================================
// @Descrition:  原型模式使用场景
// =====================================================
// ● 资源优化场景
// 类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等。
// ● 性能和安全要求的场景
// 通过new产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。
// ● 一个对象多个修改者的场景
// 一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑
// 使用原型模式拷贝多个对象供调用者使用。
// ● 一般和工厂方法模式一起出现
// =====================================================
// @Descrition:  注意事项
// =====================================================
// 1.克隆对象时构造函数是不执行的
// 2.浅拷贝与深拷贝
	// 浅拷贝时不会被拷贝的情况：
	// 类的成员变量/方法内的变量/可变引用对象
	
	// 	深拷贝，对浅拷贝不会拷贝的变量进行独立拷贝
	// 或者自己写二进制流来拷贝对象
// 3.clone 和 final
	//final关键字修饰的变量不可重新赋值，所以若要使用clone方法，就不要在类的实例变量上加final关键字
public class PrototypeClass implements Cloneable {
	@Override
	public PrototypeClass clone(){
		PrototypeClass prototypeClass = null;
		try {
			prototypeClass = (PrototypeClass)super.clone();
		} catch( CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return prototypeClass;
	}
}


// =====================================================
// @Descrition:  邮件实例
// =====================================================
public class AdvTemplate {
	private String advSubject = "xxx银行国庆信用卡抽奖活动";
	private String advContext = "国庆抽奖活动通知，只要刷卡就送你一百万！";

	public String getAdvSubject() {
		return this.advSubject;
	} 
	public String getAdvContext() {
		return this.advContext;
	}
}

public class Mail {
	private String receiver; // 信件接收者
	private String subject;  // 信件主题
	private String appellation; // 信件称谓
	private String context; //信件内容
	private String tail;  // "...版权所有"

	public Mail(AdvTemplate advTemplate) {
		this.context = adv.getAdvContext;
		this.subject = adv.getAdvSubject;
	}

	//getter/setter
	public String getReceiver() {
		return receiver;
	}
	public setReceiver(String receiver) {
		this.receiver = receiver;
	}
	//...
}

public class Client {
	private static int MAX_COUNT = 666;
	public static void main(String[] args) {
		int i = 0;
		Mail mail = new Mail(new AdvTemplate());
		while(i < MAX_COUNT) {
			mail.setAppellation(getRandString(5) + "先生/女士 ");
			mail.setReceiver(getRandString(5) + "@" + getRandString(8) + "");
			sendMail(mail);
			i ++;
		}
	}

	public static void sendMail(Mail mail) {
		System.out.println("标题：" + mail.getSubject() + 
			"\t 收件人：" + mail.getReceiver() + 
			"\t ... 发送成功！");
	}

	public static String getRandString(int maxLength) {
		String source = "sadfghjklkjhgfdghjkjhgfd";
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < maxLength ; i ++ ) {
			sb.append(source.charAt(rand.nextInt(source.length())));
		}
		return sb.toString();
	}
}