package javaBasic.trapInOO;

class Animal {
    public static void info(){
        System.out.println("Animal 类的info");
    }
}
public class StaticInherit extends Animal{
//    @Override
    public static void info(){
        System.out.println("heheheh");
    }
}
