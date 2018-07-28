package javaBasic.LinearStructure;

/**
 * 线性表-单向链表
 * @param <T>
 */
class LinkList<T> {
    // 定义一个内部类Node,其实例代表链表节点
    private class Node {
        private T data;
        private Node next;

        public Node(){
        }
        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    // 私有变量
    private Node header;
    private Node tail;
    private int size;
    // 构造函数
    public LinkList(){
        header = null;
        tail = null;
    }
    public LinkList(T element){
        header = new Node(element,null);
        tail = header;
        size ++;
    }


    public int length(){
        return size;
    }
    public T get(int index){
        return getNodeByIndex(index).data;
    }
    private Node getNodeByIndex(int index){
        if (index < 0 || index > size-1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node current = header;
        for (int i = 0; i < size && current != null; i++,current = current.next) {
            if (i == index) {
                return current;
            }
        }
        return null;
    }
    public int locate(T element){
        Node current = header;
        for (int i = 0; i < size && current != null; i ++,current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }
    public void insert(T element, int index){
        // 注意这里，插入的未知判断和删除的位置判断有细微差别
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        if (header == null) {
            add(element);
        } else {
            if (index == 0){
                addAtHeader(element);
            } else {
                Node prev = getNodeByIndex(index - 1);
                prev.next = new Node(element,prev.next);
                size++;
            }
        }
    }
    // 采用尾插法为链表添加节点
    public void add(T element){
        if (header == null) {
            header = new Node(element,null);
            tail = header;
        } else {
            Node newNode = new Node(element,null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public void addAtHeader(T element){
        header = new Node(element,header);
        // 如果之前为空链表，则尾指针也受影响，所以需改动尾指针的指向
        if (tail == null){
            tail = header;
        }
        size++;
    }
    public T delete(int index){
        // 注意这里，插入的未知判断和删除的位置判断有细微差别
        if (index < 0 || index > size -1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node del = null;
        if (index == 0) {
            del = header;
            header = header.next;
        } else {
            Node prev = getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            del.next = null;
        }
        size--;
        return del.data;
    }
    public T remove(){
        return delete(size - 1);
    }

    public boolean empty(){
        return size == 0;
    }
    public void clear(){
        header = null;
        tail = null;
        size = 0;
    }
    public String toString(){
        if (empty()){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = header; current != null; current = current.next) {
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len-2,len).append("]").toString();
        }
    }
}

public class LinkListTest {
    public static void main(String[] args) {
        LinkList<String> list = new LinkList<>();
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
        LinkList<String> list2 = new LinkList<>("zhuguangyuan");
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










