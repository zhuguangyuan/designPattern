/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-20 23:38:15
 * @Descrition:  桥梁模式
 * Decouple an abstraction from its implementation
 * so that the two can vary independently.
 */
// 有一个抽象类A，他有一个抽象方法a，实现的方式有两种
	// 继承：写一个子类继承抽象类，然后给出具体实现
		// 注意此处只是理论上可用，若为非Is-A的情况不能用继承，否则就是滥用继承，会造成代码逻辑混乱
	// 聚合：再写一个抽象类B，它的b方法实现了a方法。然后再由另外的实现类继承这个抽象类，此处实现类可以有多个类别，即可以有多种实现方式
		// 抽象类A维护一个对B的引用，调用B的方法b来实现a
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
// 抽象类
public abstract class Abstraction {
	private Implementor implementor;

	public Abstraction(Implementor implementor){
		this.implementor = implementor;
	}

	public void operation(){
		this.implementor.operationImpl();
	}
}
// 抽象类的修正
public class RefinedAbstraction extends Abstraction {
	public RefinedAbstraction(Implementor implementor){
		super(implementor);
	}

	public void otherOperation(){

	}
}


// 实现类
// 实现可能有多种，桥接模式将实现独立出来，让它们各自独立地变化，一种实现
// 的变化不会影响到另一种实现，从而达到应对变化的目的
public interface Implementor {
	public abstract void operationImpl();
} 
// 具体实现类 业务逻辑1
// 手机的实现，按品牌实现，有三星 华为 苹果。。。
// 消息的实现，按消息类别分，有普通 加急 特急。。。
// 
public class ConcreteImplementor1 implements Implementor {
	public void operationImpl(){
		// 具体操作1
	}
 }
// 具体实现类2 业务逻辑2
// 手机的实现，按软件实现，有短信 电话 通讯录。。。
// 消息的实现，按发送方式分，有短信 邮件 电话。。。
 public class ConcreteImplementor2 implements Implementor {
 	public void operationImpl(){
 		// 具体操作2
 	}
 }



// 测试客户端
public class Client {
	public static void main(String[] args){
		Implementor imp = new ConcreteImplementor1();
		Abstraction abs = new RefinedAbstraction(imp);
		abs.operation();
	}
}











/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-21 22:01:05
 * @Descrition:  
 * @Descrition: 实例代码 
 * 例子1 == 抽象类-消息 实现类-消息发送方式 == 参考 https://www.jianshu.com/p/775cb53a4da2
 * 例子2 == 抽象类-手机品牌 实现类-手机软件 == 参考《大话设计模式》
 * 例子3 == 抽象类-公司 实现类-产品 == 参考《设计模式之禅》
 */
// =====================================================
// @Descrition:  例子1 == 抽象类-消息 实现类-消息发送方式
// =====================================================
// 抽象类A
public abstract class AbstractMessage {
	MessageImplementor implementor;

	public AbstractMessage(MessageImplementor implementor){
		this.implementor = implementor;
	}
	public void sendMessage(String message, String toUser){
		this.implementor.send(message,toUser);
	}
}

// 抽象类A的修正类1
public class CommonMessage extends AbstractMessage {
	public CommonMessage(MessageImplementor implementor){
		super(implementor);
	}
	public void sendMessage(String message, String toUser){
		super.sendMessage(message,toUser);
	}
}
// 抽象类A的修正类2
public class UrgencyMessage extends AbstractMessage {
	public UrgencyMessage(MessageImplementor implementor){
		super(implementor);
	}
	public void sendMessage(String message, String toUser){
		message = "加急: " + message;
		super.sendMessage(message,toUser);
	}

	// 扩展自己的功能
	public Object watch(String messageId){
		return null;
	}
}
// 抽象类A的修正类3
public class SpecialUrgencyMessage extends AbstractMessage {

}




// 实现类B的抽象类
public abstract class MessageImplementor {
	public abstract void send(String message, String toUser);
}

// 实现类B的实现类1
public class MessageSMS extends MessageImplementor {
	public void send(String message, String toUser){
		System.out.println(String.format("使用SMS的方法，发送消息%s给%s",message,toUser));
	}
} 
// 实现类B的实现类2
public class MessageEmail extends MessageImplementor {
	public void send(String message, String toUser){
		System.out.println(String.format("使用Email的方法，发送消息",message,toUser));
	}
}
// 实现类B的实现3
public class MessagePhone extends MessageImplementor {
	public void send(String message, String toUser){
		System.out.println(String.format("使用Phone的方法，发送消息",message,toUser));
	}
}


// 测试客户端
public class Client {
	public static void main(String[] args){
		// 通过SMS 发送普通消息
		MessageImplementor implementor = new MessageSMS();
		AbstractMessage abstractMessage = new CommonMessage(implementor);
		abstractMessage.sendMessage("加班申请请速批","朱总");
		
		// 通过Email发送紧急消息
		implementor = new MessageEmail();
		abstractMessage = new UrgencyMessage(implementor);
		abstractMessage.sendMessage("加班申请请速批","朱总");

		// 通过Phone发送紧急消息
		implementor = new MessagePhone();
		abstractMessage = new UrgencyMessage(implementor);
		abstractMessage.sendMessage("加班申请请速批","朱总");
	}
}



// =====================================================
// @Descrition:  例子2 == 抽象类-手机品牌 实现类-手机软件
// =====================================================
// C# 实现，略

// =====================================================
// @Descrition:  例子3 == 抽象类-公司 实现类-产品
// =====================================================
// 这个例子与前两个例子不一样，这里用
public abstract class Corp {
	private Product product;
	public Corp(Product product){
		this.product = product;
	}
	public void makeMoney(){
		this.product.beProducted();
		this.product.beSelled();
	}
}
public class RealtyCorp extends Corp {
	public RealtyCorp(House house){
		super(house);
	}
	public void makeMoney(){
		super.makeMoney();
		System.out.println("房地产公司赚大钱了...");
	}
}
public class ShanZhaiCorp extends Corp {
	public ShanZhaiCorp(Product product){
		super(product);
	}
	public void makeMoney(){
		super.makeMoney();
		System.out.println("山寨公司也赚钱。。。");
	}
}



// 实现类
public abstract class Product {
	public abstract void beProducted();
	public abstract void beSelled();
}
public class House extends Product {
	public void beProducted(){
		// 房子被盖起来
	}
	public void beSelled(){
		// 房子被销售
	}
}
public class IPod extends Product {
	public void beProducted(){
		// IPod被生产出来
	}	
	public void beSelled(){
		// IPod被销售
	}
}


// 测试客户端
public class Client {
	public static void main(String[] args) {
		// 房地产公司赚钱
		House house = new House();
		RealtyCorp realtyCorp = new RealtyCorp(house);
		realty.makeMoney();

		// 山寨公司也赚钱
		Product product = new Product();
		ShanZhaiCorp shanzhaiCorp = new ShanZhaiCorp(product);
		shanzhaiCorp.makeMoney();
	}
}
