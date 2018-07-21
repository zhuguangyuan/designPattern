/**
 * @Author:      zhuguangyuan 
 * @DateTime:    2018-07-20 16:25:02
 * @Descrition:  适配器模式 Structural_Adapter.java
 * Convert the interface of a class into another interface clients
 * expect. Adapter lets classes work together that couldn't 
 * otherwise because of incompatible interfaces.
 */

// 目标接口
 public interface Target {
 	public void request();
 }
 public class ConcreteTarget implements Target {
 	public void request(){
 		System.out.println("if you need any help,call me!");
 	}
 }

// 源角色，要适配改造的对象
 public calss Adatee {
 	public void soSomething(){
 		System.out.println("I'm kind of busy,leave me alone,please!");
 	}
 }
 
// 适配器，继承源角色并实现目标接口
 public class Adater extends Adatee implements Target {
 	public void request(){
 		super.doSomething();
 	}
 } 

 // 测试客户端
 public class Client {
 	public static void main(String[] args) {
 		Target target = new ConcreteTarget():
 		target.request();

 		Target target2 = new Adapter();
 		target2.request();
 	}
 }