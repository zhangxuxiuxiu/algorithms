package cn.sspku.zx.aad.InterviewQs;

/**
 * visit:http://www.geeksforgeeks.org/longest-monotonically-increasing-
 * subsequence-size-n-log-n/
 * 
 * @author zhangxu
 * 
 */
public class LongestMonotonicallyIncreasingSubsequenceSize {
	/**
	 * @solution
	 * @param arr
	 * @return
	 */
	public int LIS(int[] arr) {
		// corner case
		if (null == arr || arr.length == 0)
			return 0;

		// maximal length of subsequence which ends with arr[i]
		int[] len = new int[arr.length];
		len[0] = 1;
		int maxLen = 1;
		for (int outer = 1; outer < arr.length; ++outer) {
			int _maxLen = 0;
			for (int inner = outer - 1; inner >= 0; --inner)
				if (arr[inner] < arr[outer] && len[inner] >= _maxLen)
					_maxLen = len[inner] + 1;
			len[outer] = _maxLen;
			if (_maxLen > maxLen)
				maxLen = _maxLen;
		}

		return maxLen;
	}

	public static void main(String[] args) {
		LongestMonotonicallyIncreasingSubsequenceSize obj = new LongestMonotonicallyIncreasingSubsequenceSize();
		System.out.println(obj.LIS(null));
	}
}
