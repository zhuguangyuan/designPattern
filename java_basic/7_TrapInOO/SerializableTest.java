package javaBasic.trapInOO;


import java.io.*;

class Wolf implements Serializable {
    private String name;
    public Wolf(String name){
        this.name = name;
    }
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }else if (obj.getClass() == Wolf.class){
            Wolf target = (Wolf)obj;
            return target.name.equals(this.name);
        }
        return false;
    }
}


public class SerializableTest {
    public static void main(String[] args) throws Exception{
        Wolf w = new Wolf("灰太狼");
        System.out.println("--- Wolf 对象创建完毕 ---");

        Wolf w2 = null;

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.bin"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.bin"));

            oos.writeObject(w);
            oos.flush();

            w2 = (Wolf)ois.readObject();
            System.out.println(w.equals(w2));
            System.out.println(w == w2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
