package cn.sspku.zx.aad.leetcode;

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
	public int candy(int[] ratings) {
		if (null == ratings || ratings.length == 0)
			return 0;
		if (ratings.length == 1)
			return 1;

		int idx = 0, preLen = 0, len, count = 0;
		while (idx < ratings.length) {
			// 判断上行坡道
			len = lenOfUp(ratings, idx);
			// 如果长度为1，则不是上行坡道；
			if (len != 1) {
				if (idx + len != ratings.length) {// 如果接下来是下坡，则需要计算下坡的长度，然后再来确定局部最高点的值
					if (ratings[idx + len] != ratings[idx + len - 1]) {
						preLen = len;
						len = lenOfDown(ratings, idx + len - 1);
						count += preLen * (preLen - 1) / 2 + len * (len - 1)
								/ 2 + Math.max(preLen, len) - 1;// 下坡的终点不计算在内，所以要减去1
						idx += (preLen + len - 2);// 如果下坡的终点为end,那么idx=end
						if (idx == ratings.length-1) {
							++idx;
							++count;
						}
						System.out.println("count=" + count + "  idx=" + idx);
					}
					// 如果接下来是平缓的rating
					else {
						count += len * (len + 1) / 2;
						idx += len;
						System.out.println("count=" + count + "  idx=" + idx);
					}
				} else {
					idx += len;
					count += len * (len + 1) / 2;
				}
			} else {
				// 判断现在相等的rating的长度
				len = lenOfEqual(ratings, idx);
				count += (len - 1);
				idx += len - 1;
				if (idx == ratings.length - 1) {
					++idx;
					++count;
				}
				System.out.println("count=" + count + "  idx=" + idx);
				// 判断下行坡道
				len = lenOfDown(ratings, idx);
				count += len * (len + 1) / 2 - 1;
				idx += len - 1;
				if (idx == ratings.length - 1) {
					++idx;
					++count;
				}
				System.out.println("count=" + count + "  idx=" + idx);
			}
		}

		return count;
	}

	int lenOfUp(int[] ratings, int start) {
		if (start >= ratings.length)
			return 0;

		int idx = start;
		while (idx < ratings.length - 1 && ratings[idx] < ratings[idx + 1])
			++idx;
		return idx + 1 - start;
	}

	int lenOfDown(int[] ratings, int start) {
		if (start >= ratings.length)
			return 0;

		int idx = start;
		while (idx < ratings.length - 1 && ratings[idx] > ratings[idx + 1])
			++idx;
		return idx + 1 - start;
	}

	int lenOfEqual(int[] ratings, int start) {
		if (start >= ratings.length)
			return 0;

		int idx = start;
		while (idx < ratings.length - 1 && ratings[idx] == ratings[idx + 1])
			++idx;
		return idx + 1 - start;
	}

	public static void main(String[] args) {
		CandyAssignment ca = new CandyAssignment();
		System.out.println(ca.candy(new int[] { 1, 3, 2, 3, 3, 2, 2 }));
	}
}
