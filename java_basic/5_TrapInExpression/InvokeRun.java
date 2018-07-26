package javaBasic.trapInExpression;

/**
 * zhuguangyuan
 * 20180726
 */
public class InvokeRun extends Thread {
    static int i;
    public synchronized void run(){
        System.out.println("线程 " + Thread.currentThread().getName() + " " + (++i));
    }

    public static void main(String[] args){
        for (int i=0; i<30; i++){
            System.out.println("线程--" + Thread.currentThread().getName() + " " + i);
            if (i == 10){
                // 调用run()方法，就像调用对象的一般方法一样，不会启动线程
                new InvokeRun().run();
            } else if(i == 11){
                // 调用start()方法，线程才启动。
                new InvokeRun().start();
//                i ++ ;
            } else if (i==29){
                Thread t = new InvokeRun();
                t.start();
//                i ++;
            }
        }
    }
}
