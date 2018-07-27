public class SequenceList<T> {
	private int DEFAULT_SIZE = 16;
	private int capacity;
	private Object[] elementData;
	private int size = 0;

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
		elementData = new Object[capaciry];
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
		if (index<0 || index >size){
			throw new IndexOutOfBoundsException("线性表索引越界!");
		}
		ensureCapacity(size + 1);
		System.arraycopy(elementData,index,elementData,index + 1,size - index);
		elementData[index] = element;
		size++;
	}
	private void ensureCapacity(minCapacity){
		if (capacity < minCapacity){
			while(capacity < minCapacity){
				capacity <<= 1;
			}
			elementData = Arrays.copyOf(elementData,capacity);
		}
	}
	public void add(T element){
		insert(elememt,size);
	}
	public T delete(int index){
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("咋回事呢 你咋就不能差UN个好一点的参数进来呢");
		}
		T oldValue = (T)elememtData[index];
		int numMoved = size - index - 1;
		if (numMoved > 0){
			System.arraycopy(elementData,index,elementData,index+1,numMoved);
		}
		elementData[--size] = null;
		return oldValue;
	}
	public T removeTheLastElement(){
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

		list.delete(2);
		System.out.println(list);
		System.out.println("ccc 在线性表中位置是: " + list.locate("ccc"));
	}
}

