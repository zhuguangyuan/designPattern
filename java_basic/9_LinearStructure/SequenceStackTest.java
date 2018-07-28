package javaBasic.LinearStructure;

import java.util.Arrays;

class SequenceStack<T> {
    // 私有变量
    private int DEFAULT_SIZE = 16;
    private int size ;
    private int capacity;
    private int capacityIncrement;
    private Object[] elementData;

    // 构造函数
    public SequenceStack(){
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    public SequenceStack(T element){
        this();
        elementData[0] = element;
        size ++;
    }
    public SequenceStack(T element,int initSize, int capacityIncrement){
        this.capacity = capacity;
        this.capacityIncrement = capacityIncrement;
        elementData = new Object[capacity];
        elementData[0] = element;
        size ++;
    }


    public int length(){
        return size;
    }
    public boolean empty(){
        return size == 0;
    }
    public void clear(){
        Arrays.fill(elementData,null);
        size = 0;
    }
    public String toString(){
        if (size ==0){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = size - 1; i >= 0; i--){
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len-2,len).append("]").toString();
        }
    }


    public void push(T element){
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }
    private void ensureCapacity(int minCapacity){
        if (capacity < minCapacity){
            if (capacityIncrement > 0){
                capacity = capacity + capacityIncrement;
            } else {
                while(capacity < minCapacity){
                    capacity <<= 1;
                }
            }
            elementData = Arrays.copyOf(elementData,capacity);
        }
    }
    public T pop(){
        T oldValue = (T)elementData[size - 1];
        elementData[--size] = null;
        return oldValue;
    }
    public T peek(){
        return (T)elementData[size - 1];
    }
}

public class SequenceStackTest {
    public static void main(String[] args) {
        SequenceStack<String> stack = new SequenceStack<String>();
        stack.push("aaaa");
        stack.push("bbbb");
        stack.push("cccc");
        stack.push("dddd");
        System.out.println("push四个元素之后：" + stack);

        System.out.println("取出栈顶元素：" + stack.peek());
        System.out.println("弹出栈顶元素：" + stack.pop());
        System.out.println("弹出栈顶元素：" + stack.pop());
        System.out.println("连续弹出两个元素后的栈：" + stack);
    }
    /**
     * push四个元素之后：[dddd, cccc, bbbb, aaaa]
     * 取出栈顶元素：dddd
     * 弹出栈顶元素：dddd
     * 弹出栈顶元素：cccc
     * 连续弹出两个元素后的栈：[bbbb, aaaa]
     */
}
