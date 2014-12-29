package cn.sspku.zx.aad.leetcode;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example: Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * 
 * @author zhangxu
 * 
 */
public class JumpGameII {
	/**
	 * 假设终点总是可达的
	 * 
	 * @param A
	 * @return
	 */
	public int jump(int[] A) {
		if (null == A || A.length < 2)
			return 0;
	
		int _steps=0, max, eachMaxStep, pos = 0, end = 1;
		while (true) {
			System.out.println("start=  " + pos + "  end=   " + end);
			++_steps;

			// 计算当前区间一步可以走得最远的地方
			max = Integer.MIN_VALUE;
			while (pos < end) {
				eachMaxStep = pos + A[pos];
				// 如果当前位置可以直达最后的位置，则直接返回
				if (eachMaxStep >= A.length - 1)
					return _steps;
				// 如果当前位置可以走得更远
				if (eachMaxStep > max)
					max = eachMaxStep;
				++pos;
			}

			end = max + 1;
		}
	}

	public static void main(String[] args) {
		JumpGameII jg = new JumpGameII();
		System.out.println(jg.jump(new int[] { 2, 3, 1, 1, 4 }));

	}
}
