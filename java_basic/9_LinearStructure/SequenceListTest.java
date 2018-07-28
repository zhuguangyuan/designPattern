package javaBasic.LinearStructure;

import java.util.Arrays;

/**
 * 线性表-顺序表
 * @param <T>
 */
class SequenceList<T> {
    // 私有变量
    private int DEFAULT_SIZE = 16;
    private int capacity;
    private Object[] elementData;
    private int size = 0;

    // 构造函数
    public SequenceList(){
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    public SequenceList(T element){
        this();
        elementData[0] = element;
        size++;
    }
    public SequenceList(T element, int initSize){
        capacity = 1;
        while( capacity < initSize ){
            capacity <<= 1;
        }
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }


    public int length(){
        return size;
    }
    public T get(int i){
        if (i<0 || i>(size-1)) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        return (T)elementData[i];
    }
    public int locate(T element){
        for(int i=0; i<size; i++){
            if (elementData[i].equals(element)){
                return i;
            }
        }
        return -1;
    }
    public void insert(T element, int index){
        // 注意这里，插入的未知判断和删除的位置判断有细微差别
        if (index<0 || index >size){
            throw new IndexOutOfBoundsException("线性表索引越界!");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = element;
        size++;
    }
    private void ensureCapacity(int minCapacity){
        if (capacity < minCapacity){
            while(capacity < minCapacity){
                capacity <<= 1;
            }
            elementData = Arrays.copyOf(elementData,capacity);
        }
    }
    public void add(T element){
        insert(element,size);
    }
    public T delete(int index){
        // 注意这里，插入的未知判断和删除的位置判断有细微差别
        if(index < 0 || index > size-1) {
            throw new IndexOutOfBoundsException("咋回事呢 你咋就不能差UN个好一点的参数进来呢");
        }
        T oldValue = (T)elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }
    public T remove(){
        return delete(size-1);
    }
    public boolean empty(){
        return size == 0;
    }
    public void clear(){
        Arrays.fill(elementData,null);
        size = 0;
    }
    public String toString(){
        if (size == 0){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i ++){
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len-2,len).append("]").toString();
        }
    }
}

public class SequenceListTest {
    public static void main(String[] args){
        SequenceList<String> list = new SequenceList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.insert("ddd",1);
        System.out.println(list);
        System.out.println(list.get(2));

        String dd = list.delete(2);
        System.out.println("删除掉的元素是：" + dd);
        System.out.println(list);
        System.out.println("ccc 在线性表中位置是: " + list.locate("ccc"));
        System.out.println("ddd 在线性表中位置是: " + list.locate("ddd"));

        list.remove();
        System.out.println(list);
        System.out.println(list.empty());
        System.out.println(list.length());

        System.out.println("———————— 测试不同的构造函数 ————————");
        SequenceList<String> list2 = new SequenceList<>("zhuguangyuan");
        System.out.println(list2);
    }
    /**
     * [aaa, ddd, bbb, ccc]
     * bbb
     * 删除掉的元素是：bbb
     * [aaa, ddd, ccc]
     * ccc 在线性表中位置是: 2
     * ddd 在线性表中位置是: 1
     * [aaa, ddd]
     * false
     * 2
     * ———————— 测试不同的构造函数 ————————
     * [zhuguangyuan]
     */
}

