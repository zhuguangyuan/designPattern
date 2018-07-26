package javaBasic.trapInOO;


import java.io.*;

class Singleton implements Serializable {
    private static Singleton instance;
    private String name;

    private Singleton(String name){
        this.name = name;
    }
    public static Singleton getInstance(String name){
        if (instance == null){
            instance = new Singleton(name);
        }
        return instance;
    }

    // 反序列化时自动调用这个方法返回指定好的对象
    // 从而保证系统通过反序列化机制不会产生多个java对象
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}


public class SingletonTest {
    public static void main(String[] args){
        Singleton w = Singleton.getInstance("灰太狼");
        System.out.println("--- Wolf 对象创建完成 ---");

        Singleton w2 = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.bin"));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.bin"));

            oos.writeObject(w);
            oos.flush();

            w2 = (Singleton) ois.readObject();
            System.out.println(w.equals(w2));
            System.out.println(w ==w2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
