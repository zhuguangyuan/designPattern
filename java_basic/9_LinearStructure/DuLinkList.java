public class DuLinkList<T> {
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
	
	private Node header;
	private Node tail;
	private int size;

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
		if (index < size/2){
			Node current = header;
			for (int i = 0; i < size/2 && current != null; i++,current = current.next) {
				if (i == index) {
					return current;
				}
			}
		} else {
			Node current = tail;
			for (int i = size-1; i > size/2 && current != null; i--,current = current.prev) {
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
	

			
