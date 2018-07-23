/**
 * @Author:      zhuguangyuan
 * @DateTime:    2018-07-23 21:41:35
 * @Descrition:  继承的相关坑
 */
// java对象是构造器中创建的吗？
// 不是！声明对象的时候JVM会为对象分配内存，并对实例变量赋默认值0/null/false
// 构造器只是按需再次初始化实例变量而已。
// 所以反射中是可以不经过构造器的
// 设计模式中的克隆模式，也不经过构造器
// 设计模式中的单例模式只是限制了构造函数为private的，即外部不可通过new 方法来创建实例
// 
// =====================================================
// @Descrition:  编译类型和运行时类型不一致时，属性与编译类型一致和方法与运行类型一致
// Father f = new Son();
// f.field1 ==> 取到的是Father的field1
// f.method1 ==> 调用的是Son的method1 // 此亦为父类方法被覆写
// =====================================================
class Base {
    private int i = 2;
    public Base(){
        System.out.println("父类中打印this" + this.getClass());
        System.out.println("父类构造函数中打印i" + this.i);
        this.display();
    }
    public void display(){
        System.out.println("父类中打印i = " + i);
    }
}
class Derived extends Base {
    private int i = 22; // 注意这里也定义了一个私有变量i，不同于父类的i
    public Derived(){
        System.out.println("调用子类构造器前 i = " + i);
        i = 222;
        System.out.println("调用子类构造器后 i = " + i);
    }
    public void display(){
        System.out.println("子类中打印i = " + i);
    }
}

// =====================================================
// @Descrition: 不要在父类构造器中调用被子类重写的函数 
// =====================================================
class Animal2 {
    private String desc;
    public Animal2(){
        System.out.println("调用Animal2类的构造器");
        this.desc = getDesc();
    }
    public String getDesc(){
        return "Animal...";
    }
    public String toString(){
        System.out.println("调用Animal类的toString()");
        return this.desc;
    }
}
class Wolf extends Animal2 {
    private String name;
    private double weight;

    public Wolf(String name, double weight){
        System.out.println("调用Wolf类的构造函数前，name = " + this.name + ", weight = " + this.weight);
        this.name = name;
        this.weight = weight;
        System.out.println("调用Wolf类的构造函数后，name = " + this.name + ", weight = " + this.weight);
    }
    public String getDesc(){
        String desc = "Wolf.name = " + this.name + ", Wolf.weight = " + this.weight;
        return desc;
    }
}


public class DerivedTest {
    public static void main(String[] args) {
        new Derived();
        // 以上这条语句执行时
        // JVM会创建对象并分分配内存，内存中有两个i，一个是父类的一个是子类的,初始值都是0
        // 执行到父类的构造部分，定义语句初始化其i值为2，然后执行父类构造函数，
        // this.getClass()打印出是子类Derived
        // this.i 打印出是父类的i的值 2
        // display()方法已被覆写，所以此处执行的是子类方法，打印出 子类中打印i = 0
        // 进入子类定义i的语句，i = 22
        // 进入子类构造函数，i = 222,执行完毕

        /**
         * 运行结果：
         * 父类中打印thisclass main.java.Derived
         * 父类构造函数中打印i2
         * 子类中打印i = 0
         * 调用子类构造器前 i = 22
         * 调用子类构造器后 i = 222
         */

		System.out.println(new Wolf("灰太狼",32.3));
		/**
         * 执行结果：
         * 调用Animal2类的构造器
         * 调用Wolf类的构造函数前，name = null, weight = 0.0
         * 调用Wolf类的构造函数后，name = 灰太狼, weight = 32.3
         * 调用Animal类的toString()
         * Wolf.name = null, Wolf.weight = 0.0
         */
        // 结论：不要在父类构造器中调用会被子类重写的方法A
        // 否则此被重写的方法A会在子类的初始化块及构造函数之前被执行，导致A中若用到相关变量，则是未经正确初始化的
    }
}


