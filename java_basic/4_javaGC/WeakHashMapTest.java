package main.java;

import java.util.WeakHashMap;

/**
 * @Author:      zhuguangyuan
 * @DateTime:    2018-07-24 12:49:56
 * @Descrition:  弱引用测试
 */

class CrazyKey {
    String name;
    public CrazyKey(String name){
        this.name = name;
    }
    public int hashCode(){
        return name.hashCode();
    }
    public boolean equals(Object obj){
        if (obj == null) {
            return true;
        }
        if (obj != null && (obj.getClass() == CrazyKey.class)) {
            return name.equals(((CrazyKey)obj).name);
        }
        return false;
    }
    public String toString(){
        return "CrazyKey[name=" + name + "]";
    }
}

public class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        // 强引用map指向堆内的一个HashMap类型的map,
        // map中通过key映射到String,String类型的实例value11作为弱引用引用堆内的String对象
        // 当垃圾回收的时候，只有弱引用指向的堆内对象会被回收
        // 同时对应的弱引用对象也会被回收？
        /**
         * --- 运行垃圾回收前 ---
         * { CrazyKey[name=key9]=value910,
         *   CrazyKey[name=key7]=value710,
         *   CrazyKey[name=key8]=value810,
         *   CrazyKey[name=key1]=value110,
         *   CrazyKey[name=key2]=value210,
         *   CrazyKey[name=key0]=value010,
         *   CrazyKey[name=key5]=value510,
         *   CrazyKey[name=key6]=value610,
         *   CrazyKey[name=key3]=value310,
         *   CrazyKey[name=key4]=value410
         *   }
         * value210
         * --- 运行垃圾回收后 ---
         * {}
         * null
         */
        WeakHashMap<CrazyKey,String> map = new WeakHashMap<CrazyKey,String>();
        for (int i=0; i<10; i++) {
            map.put( new CrazyKey("key"+i), "value"+i+10 );
        }

        System.out.println("--- 运行垃圾回收前 ---");
        System.out.println(map);
        System.out.println(map.get(new CrazyKey("key2")));

        System.gc();
        Thread.sleep(50);
        System.out.println("--- 运行垃圾回收后 ---");

        System.out.println(map);
        System.out.println(map.get(new CrazyKey("key2")));
    }
}