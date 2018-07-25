class Apple<T extends Number> {
	T size;
	public Apple(){

	}
	public Apple(T size){
		this.size = size;
	}
	public void setSize(T size){
		this.size = size;
	}
	public T getSize(){
		return this.size;
	}

	public List<String> getApples(){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 3; i++){
			list.add(new Apple<String>(10*i).toString());
		}
	}
}