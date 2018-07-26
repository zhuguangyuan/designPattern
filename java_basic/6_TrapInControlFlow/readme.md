# 6.控制流的陷阱
- switch(expression)
	- expression的类型可为byte,short,int,char,enum,String,但不可为long,float,double
	- 如果循环体中只包含一条语句且是局部变量定义语句时，循环体不可以省略花括号。
		- for() int a = 2; 是错误的，需写成 for(){int a = 2;}
	- for循环的初始化条件可以同时定义多个变量，但由于它只能接受一条语句，这两个变量的数据类型应该相同
	- foreach循环遍历的是集合或者数组的元素的副本，而不是元素本身，尽量不要对其赋值
	- 