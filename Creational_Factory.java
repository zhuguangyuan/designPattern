// =====================================================
// 工厂方法模式：针对一个产品等级结构。每个具体工厂类只能创建一个产品
// 抽象工厂模式：针对多个产品等级结构。每个具体工厂类可以创建多个产品
// 工厂方法类，去掉抽象工厂类，并将创建产品的方法设为static 则变成简单工厂模式
// =====================================================


/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-17 09:37:28
 * @Descrition:  工厂方法模式代码实例
 */


// 产品类
abstract class Product {
	// 抽象方法
	public abstract void method1();
}
// 具体产品1
class ConcreteProduct1 extends Product {
	public void method1(){
		System.out.println("具体产品1");
	}
}
// 具体产品2
class ConcreteProduct2 extends Product {
	public void method1(){
		System.out.println("具体产品2");
	}
}


// 工厂类
abstract class Cractor {
	public abstract <T extends Product> T createProduct(Class<T> c);
}
// 具体工厂1
class ConcreteCreator extends Creator {
	public <T extends Product> T createProduct(Class<T> c){
		Product product = null;
		try{
			// 注意这里 Class.forName(类的全限定名称)，直接传进来的是类
			product = (Product)((Class.forName(c.getName())).newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T)product;
	}
}


// 客户端测试
public class Client {
	public static void main(String[] args) {
		Creator creator = new ConcreteCreator();
		Product product1 = creator.createProduct(ConcreteProduct1.class);
		Product product2 = creator.createProduct(ConcreteProduct2.class);
		/**
		* 继续业务处理
		*/
	}
}




/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-17 14:40:19
 * @Descrition:  抽象工厂实例
 */
// A产品系列
public abstract class AbstractProductA {
	public abstract void doSomethingA();
}
// 具体的A产品
class ProductA1 extends AbstractProductA {
	@override 
	public void doSomethingA() {
		System.out.println("Product A1");
	}
}
class ProductA2 extends AbstractProductA {
	@override 
	public void doSomethingA() {
		System.out.println("Product A2");
	}
}


// B产品系列
public abstract class AbstractProductB {
	public abstract void doSomethingB();
}

class ProductB1 extends AbstractProductB {
	@override
	public void doSomethingB() {
		System.out.println("Product B1");
	}
}
class ProductB2 extends AbstractProductB {
	@override 
	public void doSomethingB() {
		System.out.println("Product B2");
	}
}


// 产品工厂类
public abstract class AbstractFactory {
	public abstract AbstractProductA createProductA(Class t);
	public abstract AbstractProductB createProductB(Class t);
}

class Factory1 extends AbstractFactory {
	@override 
	public AbstractProductA createProductA(Class t) {
		AbstractProductA pa = (AbstractProductA) Class.forName(t.getName()).newInstance();
		return pa;
	}
	@override 
	public AbstractProductB createProductB(Class t) {
		AbstractProductB pa = (AbstractProductB) Class.forName(t.getName()).newInstance();
		return pa;
	}
}
class Factory2 extends AbstractFactory {
	@override 
	public AbstractProductA createProductA(Class t) {
		AbstractProductA pa = (AbstractProductA) Class.forName(t.getName()).newInstance();
		return pa;
	}

	@override 
	public AbstractProductB createProductB(Class t) {
		AbstractProductB pa = (AbstractProductB) Class.forName(t.getName()).newInstance();
		return pa;
	}
}

// 客户端测试
public class Client {
	public static void main(String[] args) {
		AbstracrFactor factory1 = new Factory1();
		AbstracrFactor factory2 = new Factory2();

		// 生产产品A系列
		AbstractProductA pa1 = factory1.createProductA(AbstractProductA.class);
		AbstractProductA pa2 = factory2.createProductA(AbstractProductA.class);

		// 生产产品B系列
		AbstractProductB pb1 = factory1.createProductB(AbstractProductB.class);
		AbstractProductB pb2 = factory2.createProductB(AbstractProductB.class);
	}
}



