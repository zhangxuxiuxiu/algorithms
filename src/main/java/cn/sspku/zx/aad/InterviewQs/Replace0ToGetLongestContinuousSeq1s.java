package cn.sspku.zx.aad.InterviewQs;


/**
 * @question:Given an array of 0s and 1s, find the position of 0 to be replaced with 1 to
 * get longest continuous sequence of 1s. Expected time complexity is O(n) and
 * auxiliary space is O(1). Example:
 * 
 * Input: arr[] = {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1} Output: Index 9
 * Assuming array index starts from 0, replacing 0 with 1 at index 9 causes the
 * maximum continuous sequence of 1s.
 * 
 * Input: arr[] = {1, 1, 1, 1, 0} Output: Index 4
 * 
 * @author zhangxu
 * 
 */

/**
 * @solution: the problem can solved in O(n) time. The idea is to keep track of
 *            three indexes, current index (curr), previous zero index
 *            (prev_zero) and previous to previous zero index (prev_prev_zero).
 *            Traverse the array, if current element is 0, calculate the
 *            difference between curr and prev_prev_zero (This difference minus
 *            one is the number of 1s around the prev_zero). If the difference
 *            between curr and prev_prev_zero is more than maximum so far, then
 *            update the maximum. Finally return index of the prev_zero with
 *            maximum difference.
 * @author zhangxu
 * 
 */
public class Replace0ToGetLongestContinuousSeq1s {
	/**
	 * @precondition: only one or zero is in argument 'arr'
	 * @param arr
	 * @return the index of 0 to be replaced with 1 to get the longest
	 *         continuous 1s
	 */
	public int maxOnesIndex(int[] arr) {
		if (null == arr || arr.length == 0)
			return -1;

		int _pre = -1, cur = 0, _pre_pre = -1, maxLen = 1, maxIndex = 0;
		while (cur < arr.length) {
			if (arr[cur] == 0) {
				if (cur - _pre_pre > maxLen)
				{
					maxLen = cur - _pre_pre;
					maxIndex=cur;
				}
				_pre_pre = _pre;
				_pre = cur;
			}
			++cur;
		}

		return maxIndex;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1 };
		Replace0ToGetLongestContinuousSeq1s replacer = new Replace0ToGetLongestContinuousSeq1s();
		System.out.println(replacer.maxOnesIndex(arr));
	}

}
