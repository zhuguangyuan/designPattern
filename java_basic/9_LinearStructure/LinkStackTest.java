package javaBasic.LinearStructure;

class LinkStack<T> {
    // 定义一个内部类，表示栈元素节点
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
    private Node top;
    private int size;

    // 构造函数
    public LinkStack(){
        top = null;
        size = 0;
    }
    public LinkStack(T element){
        top = new Node(element,null);
        size++;
    }

    public int length(){
        return size;
    }
    public boolean empty(){
        return size == 0;
    }
    public void clear(){
        top = null;
        size = 0;
    }
    public String toString(){
        if (size == 0){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = top; current != null; current = current.next){
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len-2,len).append("]").toString();
        }
    }

    public void push(T element){
        Node newTop = new Node(element,top);
        top = newTop;
        size++;
    }
    public T pop(){
        if(!empty()){
            Node oldTop = top;
            top = top.next;
            oldTop.next = null;
            size--;
            return oldTop.data;
        }
        return null;
    }
    public T peek(){
        if (!empty()){
            return top.data;
        }
        return null;
    }
}

// 测试类
public class LinkStackTest {
    public static void main(String[] args) {
        LinkStack<String> stack = new LinkStack<>();
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
