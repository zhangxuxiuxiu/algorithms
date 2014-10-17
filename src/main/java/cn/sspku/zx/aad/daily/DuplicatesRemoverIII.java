package cn.sspku.zx.aad.daily;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most
 * twice?
 * 
 * For example, Given sorted array A = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * 
 * @author zhangxu
 * 
 */
public class DuplicatesRemoverIII {
	public int removeDuplicates(int[] A) {
		if (null == A)
			return 0;

		int p = 0, i = 0, ret = A.length;
		while (p + i < ret) {
			while (p + i < ret && A[p] == A[p + i])
				++i;

			if (i > 2) {
				for (int idx = 0; idx < ret - p - i; ++idx) {
					A[p + 2 + idx] = A[p + i + idx];
				}
				ret -= (i - 2);
			}

			p += (i > 2 ? 2 : i);
			i = 0;
		}

		return ret;
	}
}
