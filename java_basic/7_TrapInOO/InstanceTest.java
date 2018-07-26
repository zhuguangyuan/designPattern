package javaBasic.trapInOO;

public class InstanceTest {

    private String name;
    private InstanceTest instance;
    private static int count = 1;

    public InstanceTest(){
        System.out.println("调用无参构造函数");
        //下面这条语句如果在这里执行
        //会造成递归调用
        //this.instance = new InstanceTest();
    }
    public InstanceTest(String name){
        this.name = name;
        this.instance = new InstanceTest();
    }

    public String toString(){
        ++count;
        if (count > 10){
            return "真狠啊！";
        }
        if (null != this.instance) {
            return "InstanceTest[\n\t" + this.instance + "]";
        }else {
            return "呵呵，有意思了";
        }
    }
    public static void main(String[] args){
        InstanceTest in = new InstanceTest();
        System.out.println(in);
        System.out.println(in.instance);
        System.out.println(in.name);

        System.out.println("------------");
        InstanceTest in2 = new InstanceTest("测试测试");
        System.out.println(in2);
        System.out.println(in2.instance);
        System.out.println(in2.name);

        // 相互引用，导致toString 里递归调用
        // count限制递归深度100重后退出
        in.instance = in2;
        in2.instance = in;
        System.out.println(in);
    }
}
