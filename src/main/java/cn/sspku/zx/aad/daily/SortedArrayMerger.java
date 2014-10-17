package cn.sspku.zx.aad.daily;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * Note: You may assume that A has enough space (size that is greater or equal
 * to m + n) to hold additional elements from B. The number of elements
 * initialized in A and B are m and n respectively.
 * 
 * @author zhangxu
 * 
 */
public class SortedArrayMerger {
	public void merge(int A[], int m, int B[], int n) {
		if (null == A || A.length < m + n || null == B || B.length == 0)
			return;

		int p1 = 0, p2 = 0, i;
		while (p1 < m && p2 < n) {
			// 在A中找出第一个比B[p2]大的数

			while (p1 < m && A[p1] <= B[p2])
				++p1;
			// 如果p1没到结尾
			if (p1 != m) {
				// 在B中小于A[p1]的最大数
				i = 0;
				while (p2 + i < n && B[p2 + i] < A[p1])
					++i;

				insertRange(A, p1, m, B, p2, i);
				m += i;
				p2 += i;
				p1 += i;
			}
			// 如果p1已经到了结尾
			else {
				insertRange(A, p1, m, B, p2, n - p2);
			}
		}
		if(p1==m)
		{
			for(int idx=0;idx<n-p2;++idx)
			{
				A[p1+idx]=B[p2+idx];
			}
		}	
	}

	private void insertRange(int[] a, int p1, int m, int[] b, int p2, int i) {
		// 将a[p1->m-1]后移i位
		for (int j = m - 1; j >= p1; --j) {
			a[j + i] = a[j];
		}
		// 将b[p2->p2+i-1]移到a[p1->p1+i-1]
		for (int j = 0; j < i; ++j) {
			a[p1 + j] = b[p2 + j];
		}
	}
}
