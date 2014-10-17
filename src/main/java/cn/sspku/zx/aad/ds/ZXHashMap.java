package cn.sspku.zx.aad.ds;

import java.util.Map;

public class ZXHashMap<K, V> {
	// 系统默认初始容量，必须是2的n次幂，这是出于优化考虑的
	static final int DEFAULT_INITIAL_CAPACITY = 16;

	// 系统默认最大容量
	static final int MAXIMUM_CAPACITY = 1 << 30;

	// 系统默认负载因子，可在构造函数中指定
	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	// 用于存储的表，长度可以调整，且必须是2的n次幂
	ZXEntry<K, V>[] table;

	// 当前map的key-value映射数，也就是当前size
	int size;

	// 阈值
	int threshold;

	// 哈希表的负载因子
	final float loadFactor;

	// 用于确保使用迭代器的时候，HashMap并未进行更改
	transient volatile int modCount;

	@SuppressWarnings("unchecked")
	public ZXHashMap(int capacity, float loadFactor) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Illegal capacity:" + capacity);
		if (capacity > MAXIMUM_CAPACITY)
			capacity = MAXIMUM_CAPACITY;
		int newCapacity = 1;
		while (newCapacity < capacity)
			newCapacity <<= 1;
		capacity = newCapacity;
		table = new ZXEntry[capacity];

		if (loadFactor <= 0 || loadFactor > 1 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException("Illegal load factor:"
					+ loadFactor);
		this.loadFactor = loadFactor;

		threshold = (int) (capacity * loadFactor);
	}

	public ZXHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public ZXHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * 根据key来获取对应的value
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		if (key == null)
			return null;

		int hashValue = hash(key.hashCode());
		int index = indexFor(hashValue, table.length);
		for (ZXEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
			if ((hashValue == entry.hash)
					&& (key.equals(entry.key) || (key == entry.key))) {
				return entry.value;
			}
		}

		return null;
	}

	/**
	 * 在不考虑key为null的情况，将key和value 保存在entry array当中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public V put(K key, V value) {
		if (key == null)
			return null;

		int hashValue = hash(key.hashCode());
		int index = indexFor(hashValue, table.length);
		for (ZXEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
			if ((entry.hash == hashValue)
					&& (key.equals(entry.key) || key == entry.key)) {
				V oldValue = entry.value;
				entry.value = value;
				entry.recordAccess(this);
				return oldValue;
			}
		}

		return addEntry(index, hashValue, key, value);
	}

	/**
	 * 在table[index]位置加入新的entry
	 * 
	 * @param index
	 * @param hashValue
	 * @param key
	 * @param value
	 */
	private V addEntry(int index, int hashValue, K key, V value) {
		if (index < 0 || index >= table.length)
			throw new IllegalArgumentException("Illegal argument index: "
					+ index);
		ZXEntry<K, V> entry = table[index];
		table[index] = new ZXEntry<K, V>(hashValue, key, value, entry);

		++size;

		return value;
	}

	/**
	 * 根据key来移除对应的entry
	 * 
	 * @param key
	 * @return 所移除的entry对应的value
	 */
	public V remove(K key) {
		if (key == null)
			return null;

		int hashValue = hash(key.hashCode());
		int index = indexFor(hashValue, table.length);
		for (ZXEntry<K, V> entry = table[index], previous = null; entry != null; entry = entry.next) {
			if ((entry.hash == hashValue)
					&& (key.equals(entry.key) || key == entry.key)) {
				// 如果要移除的是链表的第一项
				if (null == previous)
					table[index] = entry.next;
				else
					previous.next = entry.next;

				--size;

				return entry.value;
			}
			previous = entry;
		}
		return null;
	}

	// 预处理hash值，避免较差的离散hash序列，导致桶没有充分利用
	static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	// 返回对应hash值得索引
	static int indexFor(int h, int length) {
		/*****************
		 * 由于length是2的n次幂，所以h & (length-1)相当于h % length。
		 * 对于length，其2进制表示为1000...0，那么length-1为0111...1。
		 * 那么对于任何小于length的数h，该式结果都是其本身h。 对于h = length，该式结果等于0。
		 * 对于大于length的数h，则和0111...1位与运算后， 比0111...1高或者长度相同的位都变成0，
		 * 相当于减去j个length，该式结果是h-j*length， 所以相当于h % length。 其中一个很常用的特例就是h & 1相当于h
		 * % 2。 这也是为什么length只能是2的n次幂的原因，为了优化。
		 */
		return h & (length - 1);
	}

	// 返回当前map的key-value映射数，也就是当前size
	public int size() {
		return size;
	}

	// 该HashMap是否是空的，如果size为0，则为空
	public boolean isEmpty() {
		return size == 0;
	}

	public static class ZXEntry<K, V> implements Map.Entry<K, V> {
		final K key;
		V value;
		ZXEntry<K, V> next;
		final int hash;

		// 构造函数
		ZXEntry(int h, K k, V v, ZXEntry<K, V> n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}

		// 返回key
		public final K getKey() {
			return key;
		}

		// 返回value
		public final V getValue() {
			return value;
		}

		// 设置value
		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		// 是否相同
		public final boolean equals(Object o) {
			// 如果o不是Map.Entry的实例，那么肯定不相同了
			if (!(o instanceof Map.Entry))
				return false;
			// 将o转成Map.Entry
			@SuppressWarnings("unchecked")
			Map.Entry<K, V> e = (Map.Entry<K, V>) o;
			// 得到key和value对比是否相同，相同则为true
			Object k1 = getKey();
			Object k2 = e.getKey();
			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || (v1 != null && v1.equals(v2)))
					return true;
			}
			// 否则为false
			return false;
		}

		// hashCode
		public final int hashCode() {
			return (key == null ? 0 : key.hashCode())
					^ (value == null ? 0 : value.hashCode());
		}

		// 返回String
		public final String toString() {
			return getKey() + "=" + getValue();
		}

		// 使用该方法证明该key已经在该map中
		void recordAccess(ZXHashMap<K, V> m) {
		}

		// 该方法记录该key已经被移除了
		void recordRemoval(ZXHashMap<K, V> m) {
		}
	}
}
