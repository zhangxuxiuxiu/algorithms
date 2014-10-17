package cn.sspku.zx.aad.daily;

public class LRUCache {

	public static class CacheEntry {
		private int key, val;

		public CacheEntry(int key, int value) {
			this.key = key;
			this.val = value;
		}
	}

	private CacheEntry[] data;
	private int size = 0;

	public LRUCache(int capacity) {
		data = new CacheEntry[capacity];
	}

	public int get(int key) {
		for (int idx = 0; idx < size; ++idx) {
			if (data[idx].key == key) {
				CacheEntry p = data[idx];
				adjust(p, idx);

				return p.val;
			}
		}

		return -1;
	}

	public void set(int key, int value) {
		for (int idx = 0; idx < size; ++idx) {
			if (data[idx].key == key) {
				CacheEntry p = data[idx];
				p.val = value;
				adjust(p, idx);
				return;
			}
		}

		// if not exist,insert as the first element
		if (size != data.length)
			++size;
		adjust(new CacheEntry(key, value), size);
	}

	private void adjust(CacheEntry p, int adjustPoint) {
		for (int idx = adjustPoint; idx > 0; --idx) {
			data[idx] = data[idx - 1];
		}
		data[0] = p;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
