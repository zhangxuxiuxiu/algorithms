package cn.sspku.zx.aad.daily;

/**
 * @Problem 一个未排序整数数组，有正负数，重新排列使负数排在正数前面，并且要求不改变原来的正负数之间相对顺序 比如： input:
 *          1,7,-5,9,-12,15 ans: -5,-12,1,7,9,15 要求时间复杂度O(N),空间O(1) 。
 * @author zhangxu
 * @Solution 从左到右，如果正数块内正数个数小于或等于邻接负数块内负数个数，则按正数个数按次序交换两个块内的数，正数块起点移动新的起点；
 *           否则按负数个数交换，正数块起点不变。
 */
public class StablyExchangeNegativeAndPositiveNumbers {

	private int[] data;

	public StablyExchangeNegativeAndPositiveNumbers(int[] _data) {
		data = _data;
		System.out.printf("The origal data order is :\n");
		PrintResult();
		System.out.println();
	}

	// 将负数交换到前面，正数交换到后面，但是正负数内部相对关系不变
	public void Exchange() {
		int pStart = 0, pEnd, nStart, nEnd;

		while (pStart < data.length) {
			// 计算正数块的区间
			pEnd = pStart;
			while (pEnd < data.length) {
				if (data[pEnd] > 0)
					++pEnd;
				else
					break;
			}

			// 计算负数块的区间
			nEnd = nStart = pEnd;
			while (nEnd < data.length) {
				if (data[nEnd] < 0)
					++nEnd;
				else
					break;
			}

			System.out.printf("%6d%6d", pEnd, nEnd);
			// 如果已经交换好了，就退出
			if (nEnd == nStart)
				return;

			// 如果正数块区间小，则将整个正数区间与相邻的部分负数区间交换，正数块起点进行更新
			if (pEnd - pStart <= nEnd - nStart) {
				move(pStart, pEnd);
				pStart = pEnd;
			} else // 否则，将整个负数块与相邻的部分正数块交换，正数起点不变
			{
				move(2 * nStart - nEnd, nStart);
			}

			PrintResult();
		}
	}

	// 交换[pStart,pEnd)和[pEnd,2*pEnd-pStart)两个区间的数据
	private void move(int pStart, int pEnd) {
		int tpr;
		for (int idx = 0; idx < pEnd - pStart; ++idx) {
			tpr = data[pStart + idx];
			data[pStart + idx] = data[pEnd + idx];
			data[pEnd + idx] = tpr;
		}
	}

	public void PrintResult() {
		for (int idx = 0; idx < data.length; ++idx) {
			System.out.printf("%8d", data[idx]);
		}
		System.out.printf("\n");
	}

	public static void main(String[] args) {
		StablyExchangeNegativeAndPositiveNumbers alg = new StablyExchangeNegativeAndPositiveNumbers(
				new int[] { 1, -7, -5, 9, -12, -15 });

		System.out.println("The exchange process  is :");
		alg.Exchange();
		System.out.println("\nThe final result is :");
		alg.PrintResult();

	}
}
