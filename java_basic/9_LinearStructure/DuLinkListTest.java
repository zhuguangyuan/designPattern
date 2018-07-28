package javaBasic.LinearStructure;

/**
 * 线性表-双向链表
 * @param <T>
 */
class DuLinkList<T> {
    // 定义一个内部节点类
    private class Node {
        private T data;
        private Node prev;
        private Node next;

        public Node(){
        }
        public Node(T data, Node prev, Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    // 内部私有变量
    private Node header;
    private Node tail;
    private int size;
    // 构造函数
    public DuLinkList(){
        header = null;
        tail = null;
    }
    public DuLinkList(T element){
        header = new Node(element,null,null);
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
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界！");
        }
        if (index <= size/2){
            Node current = header;
            for (int i = 0; i <= size/2 && current != null; i++,current = current.next) {
                if (i == index) {
                    return current;
                }
            }
        } else {
            System.out.println("dfsfgfg");
            Node current = tail;
            for (int i = size-1; i > size/2 && current != null; i++,current = current.prev) {
                System.out.println("----i,current.data = " + i + current.data);
                if (i == index) {
                    return current;
                }
            }
        }
        return null;
    }

    public int locate(T element) {
        Node current = header;
        for (int i = 0; i < size && current != null; i++,current = current.next) {
            if (current.data.equals(element)){
                return i;
            }
        }
        return -1;
    }
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        if (header == null) {
            add(element);
        } else {
            if (index == 0) {
                addAtHeader(element);
            } else {
                Node prev = getNodeByIndex(index-1);
                Node next = prev.next;
                Node newNode = new Node(element,prev,next);

                prev.next = newNode;
                next.prev = newNode;
                size ++;
            }
        }
    }
    public void add(T element) {
        if (header == null) {
            header = new Node(element, null, null);
            tail = header;
        } else {
            Node newNode = new Node(element, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public void addAtHeader(T element){
        header = new Node(element,tail,null);
        if (tail == null) {
            tail = header;
        }
        size++;
    }
    public T delete(int index){
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node del;
        if (index == 0){
            del = header;
            header = header.next;
            header.prev = null;
        } else {
            Node prev = getNodeByIndex(index -1);
            del = prev.next;
            prev.next = del.next;
            if (del.next != null){
                del.next.prev = prev;
            }

            del.next = null;
            del.prev = null;
        }
        size--;
        return del.data;
    }
    public T remove(){
        return delete(size - 1);
    }
    public boolean empty(){
        return size == 1;
    }
    public void clear(){
        header = null;
        tail = null;
        size = 0;
    }
    public String toString(){
        if(empty()){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = header; current != null; current = current.next){
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len -2,len).append("]").toString();
        }
    }
    public String reverseToString(){
        if(empty()){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = tail; current != null; current = current.prev){
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len-2,len).append("]").toString();
        }
    }
}



public class DuLinkListTest {
    public static void main(String[] args){
        DuLinkList<String> list = new DuLinkList<>();
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
