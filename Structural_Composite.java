/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-22 15:31:15
 * @Descrition:  组合模式
 * Compose objects into tree stractures to represent part-whole hierarchies.
 * Composite lets clients treat individual objects and compositions of objects uniformly.
 */

// =====================================================
// @Descrition:  安全模式，树枝和树叶方法不同
// =====================================================
// 抽象构件
public abstract class Component {
	public void doSomething(){
		// 通用业务逻辑
	}
}

// 具体构件-树枝构件
public class Composite extends Component {
	private ArrayList<Component> componentArrayList = new ArrayList<Component>();

	public void add(Component component){
		this.componentArrayList.add(component);
	}
	public void remove(Component component){
		this.componentArrayList.remove(component);
	}
	public ArrayList<Component> getChildren(){
		return this.componentArrayList;
	}
}
// 具体构件-树叶构件
public class Leaf extends Component {
	// 可以覆写父类方法
	@override
	public void doSomething(){
		super.doSomething();
		// 叶子节点的特殊处理逻辑
	}
}


// 测试客户端
public class Client {

	public static void main(String[] args) {
		// 注意此处直接使用了实现类，与面向接口编程冲突
		Composite root = new Composite();
		root.doSomething();

		Composite branch = new Composite();
		Leaf leaf = new Leaf();

		root.add(branch);
		branch.add(leaf);
		display(root);
	}

	public static void display(Composite root) {
		for (Component c: root.getChildren()) {
			if (c instanceof Leaf) {
				c.doSomething();
			} else {
				display( (Composite)c );
			}
		}
	}
}


// =====================================================
// @Descrition:  透明模式，树叶和树枝实现相同方法，但树叶实现空方法
// =====================================================
// 抽象构件
public abstract class Component {
	public void doSomething(){
		// 业务逻辑
	}

	public abstract void add(Component component);
	public abstract void remove(Component component);
	public abstract ArrayList<Component> getChildren();
}

// 具体构件-树枝构件
public class Composite extends Component {
	private ArrayList<Component> componentArrayList = new ArrayList<Component>();
	public void add(Component component){
		this.componentArrayList.add(component);
	}
	public void remove(Component component){
		this.component.remove(component);
	}
	public ArrayList<Component> getChildren(){
		return this.componentArrayList;
	}
}
// 具体构件-树叶构件
public class Leaf extends Component {
	@Deprecated
	public void add(Component component) throws UnsupportedOperationException {
		throw new UnsupportOperationException();
	}
	@Deprecated
	public void remove(Component component) throws UnsupportedOperationException {
		throw new UnSupportOperationException();
	}
	@Deprecated
	public ArrayList<Component> getChildren() throws UnsupportOperationException {
		throw new UnSupportOperationException();
	}
}

// 测试客户端
public class Client {
	// Main
	public static void main(String[] args) {
		//...
	}
	// 遍历树
	public static void display(Component root) {
		for ( Component c: root.getChildren()) {
			if (c instanceof Leaf) {
				c.doSomething();
			} else {
				display(c);
			}
		}
	}
	// 要实现从某个节点查找其遍历其祖先节点
	// 则需在Component中增加setParent()\getParent()方法
	// 在设置下属时将下属的祖先设为this
}