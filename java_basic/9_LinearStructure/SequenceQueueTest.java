package javaBasic.LinearStructure;

import java.util.Arrays;

class SequenceQueue<T> {
    private int DEFAULT_SIZE = 16;
    private int capacity;
    private Object[] elementData;
    private int front = 0;
    private int rear = 0;

    public SequenceQueue(){
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    public SequenceQueue(T element){
        this();
        elementData[0] = element;
        rear++;
    }
    public SequenceQueue(T element, int initSize){
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    public int length(){
        return rear - front;
    }
    public boolean empty(){
        return (rear-front) == 0;
    }
    public void clear(){
        Arrays.fill(elementData,null);
        rear = 0;
        front = 0;
    }
    public String toString(){
        if(empty()){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++){
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len-2,len).append("]").toString();
        }
    }


    public void add(T element){
        if (rear > capacity - 1){
            throw new IndexOutOfBoundsException("队列已满！");
        }
        elementData[rear++] = element;
    }
    public T remove(){
        if(empty()){
            throw new IndexOutOfBoundsException("队列为空，无元素可移除");
        }
        T oldValue = (T)elementData[front];
        elementData[front++] = null;
        return oldValue;
    }
    public T peek(){
        if(empty()){
            throw new IndexOutOfBoundsException("队列为空，无元素可移除");
        }
        T oldValue = (T)elementData[front];
        return oldValue;
    }
}

public class SequenceQueueTest {
    public static void main(String[] args){
        SequenceQueue<String>  queue = new SequenceQueue<>();
        queue.add("aaaa");
        queue.add("bbbb");
        queue.add("cccc");
        queue.add("dddd");
        System.out.println(queue);

        System.out.println("访问队列front端元素：" + queue.peek());
        System.out.println("移除队列front端元素：" + queue.remove());
        System.out.println("移除队列front端元素：" + queue.remove());
        System.out.println("两次移除队列front端元素后：" + queue);
    }
    /**
     * [aaaa, bbbb, cccc, dddd]
     * 访问队列front端元素：aaaa
     * 移除队列front端元素：aaaa
     * 移除队列front端元素：bbbb
     * 两次移除队列front端元素后：[cccc, dddd]
     */
}
