package cn.sspku.zx.aad.daily;

/**
 * Given an array and a value, remove all instances of that value in place and
 * return the new length.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond
 * the new length.
 * 
 * @author zhangxu
 * 
 */
public class RemoveElement {
	public int removeElement(int[] A, int elem) {
		if (null == A || A.length == 0)
			return 0;

		int counter = 0, idx = 0;
		//找出第一个相等元素的坐标
		while (idx < A.length && A[idx] != elem)
			++idx;

		//把后面不相等的元素往前移
		while (idx < A.length - counter) {
			++counter;
			while(idx < A.length - counter&&A[idx + counter] != elem)
			{
				A[idx] = A[idx + counter];
				++idx;
			}
		}

		return A.length - counter;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
