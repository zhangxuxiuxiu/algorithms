package cn.sspku.zx.aad.daily;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example: A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 * 
 * @author zhangxu
 * 
 */
public class JumpGame {
	public boolean canJump(int[] A) {
		if (null == A || A.length == 0)
			return false;

		if (A.length == 1)
			return true;

		boolean[] cans = new boolean[A.length];
		cans[cans.length - 1] = true;

		for (int pos = cans.length - 2; pos >= 0; --pos) {
			if (pos + A[pos] >= cans.length - 1)
				cans[pos] = true;
			else if (A[pos] == 0)
				cans[pos] = false;
			else {
				for (int step = 1; step <= A[pos]; ++step) {
					if (cans[pos + step]) {
						cans[pos] = true;
						break;
					}
				}
				cans[pos] = false;
			}
		}

		return cans[0];
	}

	//passed algorithm...
	public boolean canJump2(int[] A) {
		if (null == A || A.length == 0)
			return false;

		if (A.length == 1)
			return true;

		int pos=0,maxIdx = 0,idx;
		while( pos <=maxIdx) {			
			if (pos + A[pos]>= A.length - 1)
				return true;
			else
			{
				idx=pos + A[pos];
				if(idx>maxIdx)
					maxIdx=idx;
			}
			++pos;
		}

		return false;
	}

	public boolean canJumpRecursively(int[] data) {
		if (null == data || data.length == 0)
			return false;

		if (data.length == 1)
			return true;

		return tryJump(data, 0);
	}

	private boolean tryJump(int[] data, int pos) {
		if (pos + data[pos] >= data.length - 1)
			return true;

		if (data[pos] == 0)
			return false;

		for (int step = 1; step <= data[pos]; ++step) {
			if (tryJump(data, pos + step))
				return true;
		}

		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
