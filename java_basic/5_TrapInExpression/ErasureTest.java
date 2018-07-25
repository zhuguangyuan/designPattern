package main.java;


import java.util.ArrayList;
import java.util.List;

// 范型类
class Apple<T extends Number> {
    T size;

    public Apple(){
    }
    public Apple(T size){
        this.size = size;
    }

    public void setSize(T size){
        this.size = size;
    }
    public T getSize(){
        return this.size;
    }

    public List<String> getApples(){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 3; i++){
            list.add(new Apple<Integer>(10*i).toString());
        }
        return list;
    }
    public String toString(){
        return "Apple[size" + size + "]";
    }
}

public class ErasureTest {
    public static void main(String[] args) {
        Apple<Integer> a = new Apple<Integer>(6);
        for (String apple: a.getApples()) {
            System.out.println(apple);
            System.out.println(apple.getClass());
        }
        System.out.println();

        Apple b = a;
        // 将a变量赋值给一个没有范型声明的b变量后，系统将擦除所有范型信息，
        // 也就是擦除所有尖括号里的信息，对象b调用getApples()将不再返回
        // List<String> 而是返回List。对于里边装的元素，虽然还是String,
        // 但是系统会认为是Obeject而不是String，
        // 不信你将下列语句改为 String apple:b.getApples()试试？
        for ( Object apple: b.getApples()) {
            System.out.println(apple);
            System.out.println(apple.getClass());
        }

    }
}