package cn.sspku.zx.aad.chapterTwo;

/**
 * 在一个数组当中找出第K小的数
 * 
 * @author zhangxu
 * 
 */
public class Es_2_9 {

	public static void Swap(int[] in, int x, int y) {
		if (x != y) {
			int tpr = in[x];
			in[x] = in[y];
			in[y] = tpr;
		}
	}

	public static void Print(int[] data) {
		System.out.println();
		for (int idx = 0; idx < data.length; ++idx) {
			System.out.printf("%5d", data[idx]);
		}
		System.out.println();
	}

	public static int Select(int[] data, int start, int end, int k) {
		if (data == null)
			throw new NullPointerException("Argument data is null!");
		if (!(0 <= start && start < data.length && end <= data.length && k <= data.length))
			throw new IllegalArgumentException("Argument start=" + start
					+ " end=" + end + " and k=" + k + " are illegal");

		int q = partition(data, start, end);
		System.out.print("q=" + q + "\n");
		if (q - start + 1 == k)
			return data[q];
		if (q - start >= k)
			return Select(data, start, q, k);
		return Select(data, q, end, k - q + start);

	}

	private static int partition(int[] data, int start, int end) {
		int p = start - 1, r = end - 1;

		while (true) {
			while (data[++p] < data[end - 1])
				;
			while (data[--r] > data[end - 1] && r > start)
				;
			if (p >= r)
				break;
			Swap(data, p, r);
		}
		Swap(data, p, end - 1);

		return p;
	}

	public static void main(String[] args) {
		int[] in = new int[] { 50, 49, 8, 7, 5, 9, 76 };

		int k = 3, kValue = Select(in, 0, in.length, k);

		Print(in);
		System.out.print("The Kth Min number in array is " + kValue);
	}
}
