# java内存回收
- JVM的垃圾回收机制采用有向图方式来管理内存中的对象。
	- java对象的状态
		- 可达状态
		- 可恢复状态
			- 调用finalize方法进行资源清理，若再有引用指向本对象，则恢复可达状态OB
		- 不可达状态
			- 调用finalize也不能挽救，回收！
- java.lang.ref
	- 强引用(StrongReference)
		- 内存不足也不会回收，所以会导致内存泄漏甚至程序中止
	- 软引用(SoftReference) 
		- 内存不足时才会回收，内存充足时相当于强引用
	- 弱引用(WeakReference) 
		- JVM运行回收代码时会回收。
		- 参考 WeakHashMapTest.java
	- 虚引用(PhantomReference) 
		- 跟踪对象被垃圾回收的状态
		- java.lang.ref.ReferenceQueue 用于保存被回收后的对象的引用
- java内存泄漏
	- 程序运行过程中会不断地分配内存空间，那些不再使用的内存空间应该即时回收，从而保证系统可以再次使用这些内存，如果存在无用的内存没有被回收回来，那就是内存泄露。
	- 不可达对象
		- c++ 造成内存泄露
		- java的JVM会回收这部分内存
	- 可达但是无用对象
		- 手动释放
		- 不释放则会造成内存泄露

- java垃圾回收机制(堆内存)
	- 垃圾回收算法
		- 串行回收/并行回收
		- 并发执行/应用程序停止
		- 压缩/不压缩/复制
			- 复制：堆内存分为A、B两个区域，对于某个正在使用的区域如A,从根开始访问所有可达对象，复制到B,然后一次性回收A
			- 不压缩：标记清除。先遍历一遍标记出可达对象，再遍历一遍回收未标记的对象
			- 压缩：先遍历一遍标记出可达对象，然后将所有可达对象搬移到一起，回收剩下的内存。
	- 现行垃圾回收器：区域分代,不同区用不同的策略
		- Young:复制算法
			- Eden
			- SurvivorA SurvivorB
			- -XX:SurvivorRatio=32
		- Old：标记压缩
		- Permanent
			- 通常不会回收
			- 装载Class 方法等信息
			- 默认64M
			- Spring/Hibernate 产生大量的动态代理类，需要更多的Permanent代内存
		- 执行频率
			- Young代内存将要用完时，复制回收Young内存
			- Old代内存将要用完时，回收Young/Old两代内存
	- 与垃圾回收相关的附加选项
		- -Xms/-Xmx (JVM堆内存的初始容量、最大容量)
		- -XX:MinHeapFreeRatio/-XX:MaxHeapFreeRatio (JVM堆内存空闲百分比最小、最大)
		- -XX:NewSize/-XX:MaxNewSize(堆内存中Young的默认容量、最大容量)
		- -XX:PermSize/-XX:MaxPermSize(堆内存中Permanent的默认容量、最大容量)
		- -XX:NewRatio(堆内存中Young/Old比例)
		- -XX:SurvivorRatio(堆内存Young代中的Eden/SUrvivor比例)

	- 常见的垃圾回收器
		- 串行回收器(-XX:+UseSerialGC)
		- 并行回收器
		- 并行压缩回收器
		- 并发标记-清理回收器

- 内存管理小技巧
	- 尽量使用直接量
	- 使用StringBuilder和StringBuffer进行字符串连接
	- 尽早释放无用对象的引用
	- 尽量少用静态变量
	- 避免在经常调用的方法、循环中创建java对象
	- 缓存经常使用的对象
	- 尽量不要使用finalize方法
	- 考虑使用SoftReference