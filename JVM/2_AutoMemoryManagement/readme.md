# 2.自动内存管理机制
- 运行时数据区域
	- 程序计数器
		- 线程私有，
	- java虚拟机栈
		- 线程私有，为执行java方法服务，
		- 存储局部变量表
		- 操作数栈
		- 动态链接
		- 方法出口
	- 本地方法栈
		- 线程私有，为执行Native方法服务
	- java堆
		- 所有线程共享，存放实例对象及数组
		- HotSpot虚拟机GC算法采用分代收集算法，对堆内存分区：Eden Space（伊甸园）、Survivor Space(幸存者区)、Tenured Gen（老年代-养老区）。
			1、一个人（对象）出来（new 出来）后会在Eden Space（伊甸园）无忧无虑的生活，直到GC到来打破了他们平静的生活。GC会逐一问清楚每个对象的情况，有没有钱（此对象的引用）啊，因为GC想赚钱呀，有钱的才可以敲诈嘛。然后富人就会进入Survivor Space（幸存者区），穷人的就直接kill掉。

			2、并不是进入Survivor Space（幸存者区）后就保证人身是安全的，但至少可以活段时间。GC会定期（可以自定义）会对这些人进行敲诈，亿万富翁每次都给钱，GC很满意，就让其进入了Genured Gen(养老区)。万元户经不住几次敲诈就没钱了，GC看没有啥价值啦，就直接kill掉了。

			3、进入到养老区的人基本就可以保证人身安全啦，但是亿万富豪有的也会挥霍成穷光蛋，只要钱没了，GC还是kill掉。
	- 方法区
		- 所有线程共享
		- 存储JVM加载的类信息，常量，静态变量，即时编译器编译后的代码等
		- 运行时常量池
			- 存放编译期间生成的各种字面量和符号引用
			- 也可在运行时将新的常量放入池中 String.intern()
	- 堆外内存（直接内存）
- HotSpot虚拟机对象
	- 对象的创建过程
		- JVM接收到new指令，检查指令参数是否能定位到一个类的符号引用，并检查对应类是否已经加载、解析、初始化，若没有，则执行类的加载过程。
		- 为新生对象分配内存。
			- 指针碰撞
			- 空闲列表
		- JVM将新分配的内存空间初始化为零值。
		- 设置对象头
			- 此对象属于哪个类
			- 如何才能找到类的元数据信息
			- 对象的哈希码
			- 对象的分代年龄
		- 执行init方法(类的构造函数对对象的初始化)
	- 对象的内存布局
		- 对象头
		- 实例数据
		- 对齐填充
	- 对象的访问定位
		- 