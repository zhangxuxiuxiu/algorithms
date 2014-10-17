package cn.sspku.zx.aad.chapterTwo;

import java.util.Random;

/**
 * 快速排序：将一个数组分成三部分：小于A[Q],A[Q]以及A大于[Q] 直至数组排序完成
 * 
 * @author zhangxu
 * 
 */
public class Es_2_8 {
	public static Random rdm = new Random();

	public static void Swap(int[] in, int x, int y) {
		if (x != y) {
			int tpr = in[x];
			in[x] = in[y];
			in[y] = tpr;
		}
	}

	public static void QuickSort(int[] in, int start, int end) {
		// 如果没有或者只有一个元素，则直接退出
		if (end - start < 2)
			return;
		// 以最后一个元素为参考点
		int pre = start-1, after = end - 1;
		while (true) {
			// 在后面找一个较小的数或者找到第一个数in[start]
			while (in[--after] > in[end - 1] && after > start);
			//在前面找一个较大的树或者找到in[end-1]
			while (in[++pre] < in[end - 1]);
			// 如果找到了，大小数进行交换
			if (pre >= after)
				break;
			Swap(in, pre, after);
			System.out.printf("%5d%5d\n", pre, after);
		}
		Swap(in,end-1,pre);
		
		Print(in);

		QuickSort(in, start, pre);
		QuickSort(in, pre + 1, end);
	}

	public static void Print(int[] data) {
		System.out.println();
		for (int idx = 0; idx < data.length; ++idx) {
			System.out.printf("%5d", data[idx]);
		}
		System.out.println();
	}

	public static void qSort(int[] in, int p, int r) {
		if (p < r) {
			int q = partition(in, p, r);
			Print(in);
			qSort(in, p, q - 1);
			qSort(in, q + 1, r);
		}
	}

	private static int partition(int[] in, int p, int r) {
		int i = p, j = r + 1;
		int x = in[p];
		while (true) {
			while (in[++i] < x && i < r)
				;
			while (in[--j] > x)
				;
			System.out.printf("i=%5d j=%5d\n", i, j);
			if (i >= j)
				break;
			Swap(in, i, j);
		}
		Swap(in, p, j);

		return j;
	}

	public static void main(String[] args) {
		int[] in = new int[] { 50, 49, 8, 7, 5, 9 ,76};

		QuickSort(in, 0, in.length);
		Print(in);
	}
}
