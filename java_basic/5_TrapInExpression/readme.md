# 5.表达式中的陷阱
- java创建对象的几种方式
	- new
		- String java = new String("疯狂java");
		- 在字符串池中保存一个字符串常量对象"疯狂java"
			- 此种情况只有编译阶段就能确定字符串才会出现
			- 若原字符串常量改为包含非final的变量的表达式，或者包含方法调用则不会创建字符串常量放到字符串池中。
			- String str = "hello," + "brucezhu";,由于编译期间就能确定，所以此语句只创建了一个字符串常量，"hello,brucezhu"
		- 在堆内存中保存一个String对象，底层有一个array,用于保存各个字符
	- Class.newInstance()
	- java反序列化
	- clone
	- 对于基本类型，可以通过直接量或者简单的算术表达式的方式来创建
		- String str = "brucezhu";
		- int a = 3 + 4;
- 字符串比较
	- == 比较两个对象是否是同一个对象
	- 重写equals 比较两个对象的字符序列是否相同
- 表达式的陷阱
	- 表达式类型的自动提升
	- 复合运算符包含隐式类型转换，而普通的单目运算符不会进行类型转换
		- E1 op= E2; 等效于 E1 = (E1的类型)E1 op E2;
	- 二进制整数的陷阱
	- 输入全角字符导致的报错
	- 注释字符必须合法
	- Unicode转义字符的陷阱
	- \u000a换行
- 泛型可能引起的错误
	- 在严格的泛型程序中，使用泛型声明的类时应该总是为之指定类型实参，但是为了和老java代码保持一致，java也允许使用带泛型声明的类时不指定类型参数。如果不指定参数，那个这个类型参数默认是声明该参数时指定的第一个上限类型，这个类型就称为原始类型。
	- 原始类型变量的赋值
	- 原始类型的泛型擦除
	- 不能创建范型数组
- 正则表达式陷阱
	- String.split(String regex),参数是正则表达式
	- 正则表达式的(.)匹配任意字符
	- 区分两个方法的传入参数不同 
		- replace(CharSequence target,CharSequence replacement)
		- replaceAll(String regex,String replacement)
		- 例子
			- "brucezhu.27".replace(".","\\")
			- "brucezhu.27".replaceAll("\\.","\\\\")，其中\\用于生成转义的反斜线
- 多线程的陷阱
	- 从java5开始，提供三种方式创建启动多线程
		- 继承Thread类来创建线程类，重写run()方法作为线程执行体
		- 实现Runable接口来创建线程类，重写run()方法作为线程执行体
		- 实现Callable接口来创建线程类，重写call()方法作为线程执行体
	- 静态块或者静态函数的同步监视器是本类，非静态块或者非静态函数的同步监视器是本实例this
	- 静态初始化块中启动新线程执行初始化
		- 实际上新线程里的"初始化操作"并不是真正的初始化，它只是一次普通的赋值。
		- 类似的，在非静态块的“对象初始化”操作也不是真正的初始化操作，也只是新线程的执行体。
	- 注意调用线程对象t的start()方法后线程并不一定立即执行，需挂起当前线程才会执行
	- 注意当前线程mian调用线程t.join()方法后，其实是将cpu控制权交给t线程，且等待t线程执行完毕，才会将cpu转回给main
	- 将线程不安全的类改为线程安全：对于访问到共享资源的方法，用synchronized 修饰即可。