import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;

/**
 * 循环队列java实现
 * 解决顺序队列的假满问题
 * 20180728
 */
class LoopQueue<T> {
    // 私有变量
    private int DEFAULT_SIZE = 16;
    private int capacity;
    private int front = 0;
    private int rear = 0;
    private Object[] elementData;

    // 构造函数
    public LoopQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    public LoopQueue(T element){
        this();
        elementData[0] = element;
        rear++;
    }
    public LoopQueue(T element, int initSize){
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    //　外部函数
    public int length(){
        if (empty()) {
            return 0;
        }
        return rear > front ? rear-front : (capacity - front + rear);
    }
    public boolean empty(){
        return rear == front && elementData[rear] == null;
    }
    public boolean full(){
        return rear == front && elementData[rear] != null;
    }
    public void clear(){
        Arrays.fill(elementData,null);
        front = 0;
        rear = 0;
    }
    public String toString(){
        if (empty()){
            return "[]";
        } else {
            // 有效元素是front到rear之间的元素
            if (front < rear){
                StringBuilder sb = new StringBuilder("[");
                for (int i = front; i < rear; i++){
                    sb.append(elementData[i].toString() + ", ");
                }
                int len = sb.length();
                return sb.delete(len-2,len).append("]").toString();
            } else {// 有效元素是front->capacity,0->rear之间的元素
                StringBuilder sb = new StringBuilder("[");
                for (int i = front; i < capacity; i++){
                    sb.append(elementData[i].toString() + ", ");
                }
                for (int i = 0; i < rear; i++){
                    sb.append(elementData[i].toString() + ", ");
                }
                int len = sb.length();
                return sb.delete(len-2,len).append("]").toString();
            }
        }
    }

    //　特征方法
    public void add(T element){
        if (full()){
            throw new IndexOutOfBoundsException("队列已满！");
        }
        elementData[rear++] = element;
        rear = rear == capacity ? 0 : rear;
    }
    public T remove(){
        if (empty()) {
            throw new IndexOutOfBoundsException("队列为空！");
        }
        T oldValue = (T)elementData[front];
        elementData[front++] = null;
        front = front == capacity ? 0 : front;
        return oldValue;
    }
    public T element(){
        if (empty()) {
            throw new IndexOutOfBoundsException("队列为空！");
        }
        T oldValue = (T) elementData[front];
        return oldValue;
    }

}

// 测试类
public class LoopQueueTest {
    public static void main(String[] args) {
        LoopQueue<String> queue = new LoopQueue<>("aaa",3);
        queue.add("bbb");
        queue.add("ccc");
        System.out.println("插入三个元素后队列已满：" + queue);

        queue.remove();
        System.out.println("删除一个元素之后的队列：" + queue);

        queue.add("ddd");
        System.out.println("再次增加一个元素：" + queue);
        System.out.println("此时队列长度为:" + queue.length());
    }
    /**
     * 插入三个元素后队列已满：[aaa, bbb, ccc]
     * 删除一个元素之后的队列：[bbb, ccc]
     * 再次增加一个元素：[bbb, ccc, ddd]
     * 此时队列长度为:3
     */
}











