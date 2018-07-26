package javaBasic.trapInOO;

class Dog implements Cloneable {
    private String name;
    private Double weight;

    public Dog(String name, Double weight){
        this.name = name;
        this.weight = weight;
    }
    public Object clone(){
        Dog dog = null;
        try{
            dog = (Dog)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return dog;
    }
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }else if(obj.getClass() == Dog.class){
            Dog target = (Dog)obj;
            return target.name.equals(this.name) && (target.weight == this.weight);
        }else {
            return false;
        }
    }
    public int hashCode(){
        return (int) ((name.hashCode() * 17) + weight);
    }
}

public class CloneTest {
    public static void main(String[] args){
        Dog dog = new Dog("blot",9.8);
        System.out.println("-- 创建对象完成 ---");

        Dog dog2 = (Dog)dog.clone();
        System.out.println(dog.equals(dog2));
        System.out.println(dog == dog2);
    }

}
