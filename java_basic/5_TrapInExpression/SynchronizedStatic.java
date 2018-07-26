package javaBasic.trapInExpression;

public class SynchronizedStatic implements Runnable{
    static boolean staticFlag = true;
    static String threadName = "";

    // 静态同步方法的同步监视器是本类
    // 本类的所有对象互斥进入本方法
    public static synchronized void test0(){
        for (int i=0; i<5; i++){
            System.out.println(Thread.currentThread().getName() + "test0: " + " " + i);
        }
    }

    public void test1(){
        // 注意这个同步监视器是this,即当前对象
        // 不同的对象可以同时进入本方法，互不干扰
        synchronized (this){
            for (int i=0; i<10; i++){
//                if (threadName.equals(Thread.currentThread().getName())){
//                    System.out.println(threadName + "同一个线程继续执行test1");
//                }else{
//                    System.out.println(Thread.currentThread().getName() + "##换线程执行test1");
//                }
                threadName = Thread.currentThread().getName();
                System.out.println(threadName + "test1: " + " " + i);
            }
            System.out.println("test1执行完毕！\n");
        }
    }

    @Override
    public void run() {
        System.out.println("--- 进入对象的run方法 ---" + Thread.currentThread().getName());
//        if (staticFlag){
//            staticFlag = false;
            test0();
//        } else {
//            staticFlag = true;
            test1();
//        }
    }

    public static void main(String[] args) throws Exception {
        SynchronizedStatic ss = new SynchronizedStatic();
        SynchronizedStatic ss1 = new SynchronizedStatic();

        new Thread(ss).start();

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(ss1).start();
//        new Thread(ss).start();
//        new Thread(ss).start();
//        new Thread(ss).start();
    }
}
