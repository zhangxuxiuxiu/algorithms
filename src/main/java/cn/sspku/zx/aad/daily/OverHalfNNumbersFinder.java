package cn.sspku.zx.aad.daily;

/**
 * @Problem 一个数在数组中重复出现次数超过N的一半， 如何快速的找出这个树
 * @author zhangxu
 * @Solution
 * 
 */
public class OverHalfNNumbersFinder {

	public static int Find(int[] in) {
		if (in.length < 1)
			throw new IllegalArgumentException("Argument in is empty!!!");

		// 找出出现次数超过一半的数字
		int figure = in[0], nTimes;
		for (int i = nTimes = 0; i < in.length; ++i) {
			if (nTimes == 0) {
				figure = in[i];
				nTimes = 1;
			} else {
				if (figure == in[i])
					++nTimes;
				else
					--nTimes;
			}
		}

		// 验证找到的数字是否超过一半的出现次数
		nTimes = 0;
		for (int i = 0; i < in.length; ++i) {
			if (in[i] == figure)
				++nTimes;
		}
		if (nTimes > in.length / 2)
			return figure;

		throw new IllegalArgumentException(
				"There didn't exist a figure which appears over half of N numbers in argument 'in'!!!");
	}

	public static void main(String[] args) {

		System.out.printf("The figure appears over half of N numbers is "
				+ Find(new int[] { 2, 3, 4, 5, 2, 2, 2 }));

	}

}
