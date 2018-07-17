/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-17 16:51:33
 * @Descrition:  建造者模式实例
 */
// 工厂模式关注的是对象的粗粒度创建过程，建造者模式关注的是细粒度创建过程
// 产品类 用到模板方法模式

// =====================================================
// @Descrition:  产品模型
// =====================================================
public abstract class CarModel {
	private ArrayList<String> sequence = new ArrayList<String>();

	protected abstract void start();
	protected abstract void stop();
	protected abstract void alarm();
	protected abstract void engineBoom();

	// 设置动作序列
	public final void setSequence(ArrayList sequence) {
		this.sequence = sequence;
	}

	// 汽车跑起来
	public final void run() {
		for (int i = 0; i < this.sequence.size() ; i ++ ) {
			String actionName = this.sequence.get(i);
			if (actionName.equalsIgnoreCase("start")) {
				this.start();
			} else if (actionName.equalsIgnoreCase("stop")) {
				this.stop();
			} else if (actionName.equalsIgnoreCase("alarm")) {
				this.alarm();
			} else if (actionName.equalsIgnoreCase("engineBoom")) {
				this.engineBoom();
			}
		}
	}
} 

public class BenzModel extends CarModel {
	@override
	protected void start() {
		System.out.println("奔驰车跑起来是这样子的。。。");
	}
	@override	
	protected void stop() {
		System.out.println("奔驰车应该这样子停车。。。");
	}
	@override	
	protected void alarm() {
		System.out.println("奔驰车喇叭声是这样子的。。。");
	}
	@override	
	protected void engineBoom() {
		System.out.println("奔驰车引擎声音是这样子的。。。");
	}
}


public class BMWModel extends CarModel {
	@override
	protected void start() {
		System.out.println("baoma车跑起来是这样子的。。。");
	}
	@override	
	protected void stop() {
		System.out.println("baoma车应该这样子停车。。。");
	}
	@override	
	protected void alarm() {
		System.out.println("baoma车喇叭声是这样子的。。。");
	}
	@override	
	protected void engineBoom() {
		System.out.println("baoma车引擎声音是这样子的。。。");
	}
}


// =====================================================
// @Descrition:  建造者模型
// =====================================================
public abstract class CarBuilder {
	public abstract void setSequence(ArrayList<String> sequence);
	public abstract CarModel getCarModel();
}

public class BenzBuilder extends CarBuilder {
	private BenzModel benz = new BenzModel();
	@override
	public void setSequence(ArrayList<String> sequence) {
		this.benz.setSequence(sequence);
	}
	@override
	public void getCarModel() {
		return this.benz;
	}
}

public class BMWBuilder extends CarBuilder {
	private BMWModel bmw = new BenzModel();
	@override
	public void setSequence(ArrayList<String> sequence) {
		this.bmw.setSequence(sequence);
	}
	@override
	public void getCarModel() {
		return this.bmw;
	}
}

// =====================================================
// @Descrition:  导演类，指定各个事件的先后顺序，产出不同车型
// =====================================================
public class Director {
	private ArrayList<String> sequence = new ArrayList();
	private BMWBuilder bmdBuilder = new BMWBuilder();
	private BenzBuilder benzBuilder = new BenzBuilder();

	// 建造A型奔驰车
	public BenzModel getBenzModelA() {
		this.sequence.clear();
		this.sequence.add("start");
		this.sequence.add("stop");
		this.benzBuilder.setSequence(this.sequence);
		return this.benzBuilder.getCarModel();
	}

	// 建造B型奔驰车...
	// 建造A型baoma车...
	// 建造B型baoma车...
	// 建造C型baoma车...
}

// =====================================================
// @Descrition:  客户端调用
// =====================================================
public class Client {
	public static void main(String[] args) {
		Director director = new Director();

		//1万辆A类型的奔驰车
		for(int i=0;i<10000;i++){
			director.getABenzModel().run();
		}

		//100万辆B类型的奔驰车
		for(int i=0;i<1000000;i++){
			director.getBBenzModel().run();
		}

		//1000万辆C类型的宝马车
		for(int i=0;i<10000000;i++){
			director.getCBMWModel().run();
		}
	}
}









/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-17 17:54:52
 * @Descrition:  建造者模式的通用代码
 */
// =====================================================
// @Descrition:  产品
// =====================================================

public class Product {
	public void doSomething();
}

// =====================================================
// @Descrition:  建造者
// =====================================================
public abstract class Builder {
	public abstract void setPart();
	public abstract Product buildProduct();
}

class ConcreteBuilder1 extends Builder {
	private Product product = new Product();

	@override
	public void setPart() {
		System.out.println("第一种顺序,此处的设置产生不同的产品");
	}

	@override
	public Product buildProduct() {
		System.out.println("返回一种产品");
		return this.product;
	}
}

class ConcreteBuilder2 extends Builder {
	private Product product = new Product();

	@override
	public void setPart() {
		System.out.println("第二种顺序,此处的设置产生不同的产品");
	}

	@override
	public Product buildProduct() {
		System.out.println("再次返回一种产品");
		return this.product;
	}	
}

// =====================================================
// @Descrition:  导演类
// =====================================================
public class Director {
	ConcreteBuilder1 cBuilder1 = new ConcreteBuilder1();
	// 指挥建造一个产品
	public Product getAproduct(){
		cBuilder1.setPart();
		return cBuilder.buildProduct();
	}
	// 指挥建造其他产品...
}

// =====================================================
// @Descrition:  客户端调用
// =====================================================
public class Client {
	public static void main(String[] args) {
		Director director = new Director();
		Product product = director.getAproduct();
		// ...
	}
}
