package cn.sspku.zx.aad.daily;

/**
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get
 * more candies than their neighbors. What is the minimum candies you must give?
 * 
 * @author zhangxu
 * 
 */
public class CandyAssignment {

	/**
	 * 找到每一个局部最小值，设为1；从局部最小值分别从左上坡和右上坡递增1到局部最大值之前；最后在局部最大值的两旁取较大值加1即可。
	 * 
	 * @param ratings
	 * @return
	 */
	public int candy(int[] ratings) {
		if (null == ratings || ratings.length == 0)
			return 0;

		if (ratings.length == 1)
			return 1;

		int total = 0;
		int[] candies = new int[ratings.length];

		// 找到每一个局部最小值
		int idx, minIndex, maxIndex = -1, leftVal, rightVal;
		while (true) {
			idx = maxIndex + 1;//从maxIndex的下一个开始分析
			while (idx < ratings.length - 1 && ratings[idx] > ratings[idx + 1])
				++idx;
			minIndex = idx;
			System.out.println(minIndex);
			// 将局部最小值的糖果数量设置为1
			candies[idx] = 1;
			// 设置局部最小值的左上坡的糖果数量
			if (minIndex != maxIndex)// 避免一开始便是右上坡
			{
				while (idx > maxIndex + 1 && ratings[idx - 1] > ratings[idx]) {
					candies[idx - 1] = candies[idx] + 1;
					--idx;
				}
				// 在局部最大值的两旁取较大值加1
				leftVal = rightVal = 1;
				if (maxIndex > 0)
					leftVal = ratings[maxIndex - 1] < ratings[maxIndex] ? candies[maxIndex - 1] + 1
							: candies[maxIndex - 1];
				if (maxIndex < ratings.length - 1)
					rightVal = ratings[maxIndex + 1] < ratings[maxIndex] ? candies[maxIndex + 1] + 1
							: candies[maxIndex + 1];
				candies[maxIndex] = Math.max(leftVal, rightVal);
			}
			// 计算局部最小值的右上坡的糖果数量
			if (minIndex != ratings.length - 1) {
				idx = minIndex;
				while (idx < ratings.length - 1
						&& ratings[idx] < ratings[idx + 1]) {
					candies[idx + 1] = candies[idx] + 1;
					++idx;
				}
				maxIndex = idx;
				if (maxIndex == ratings.length - 1) {
					candies[maxIndex] = ratings[maxIndex - 1] < ratings[maxIndex] ? candies[maxIndex - 1] + 1
							: candies[maxIndex - 1];
					break;
				}
			} else
				break;
		}

		for (int i = 0; i < candies.length; ++i) {
			System.out.printf("%5d", candies[i]);
			total += candies[i];
		}
		System.out.println();

		return total;
	}

	public static void main(String[] args) {
		CandyAssignment ca = new CandyAssignment();
		System.out.println(ca.candy(new int[] { 1, 3, 3, 3, 2, 2 }));
	}

}
