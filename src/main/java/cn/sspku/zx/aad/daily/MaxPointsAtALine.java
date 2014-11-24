package cn.sspku.zx.aad.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 * 
 * @author zhangxu
 * 
 */
public class MaxPointsAtALine {
	public static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public int maxPoints(Point[] points) {
		if (points == null || points.length == 0)
			return 0;
		if (points.length == 1)
			return 1;

		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			map.clear();
			int overlap = 0, max = 0;
			// 与索引为J的点同一条直线上的点，既包含J前面的点，也包含J后面的点，但是这里并不需要考虑J前面的点
			// 因为，假设包含最多点数的直线上点既在J前面，又在J后面，那么在处理J前面点的时候，该点便会记录包含J在内的所有同一直线上的点。
			// 因此，当处理J点时，不需要考虑J前面的点
			for (int j = i + 1; j < points.length; j++) {
				int x = points[j].x - points[i].x;
				int y = points[j].y - points[i].y;
				if (x == 0 && y == 0) {
					overlap++;
					continue;
				}
				int gcd = generateGCD(x, y);
				// 在X或者Y为零的情况下，会变成（0，1）或者（1，0）
				if (gcd != 0) {
					x /= gcd;
					y /= gcd;
				}

				if (map.containsKey(x)) {
					if (map.get(x).containsKey(y)) {
						map.get(x).put(y, map.get(x).get(y) + 1);
					} else {
						map.get(x).put(y, 1);
					}
				} else {
					Map<Integer, Integer> m = new HashMap<Integer, Integer>();
					m.put(y, 1);
					map.put(x, m);
				}
				max = Math.max(max, map.get(x).get(y));
			}
			result = Math.max(result, max + overlap + 1);
		}
		return result;
	}

	private int generateGCD(int a, int b) {
		while (b != 0) {
			int tpr = a;
			a = b;
			b = tpr % b;
		}
		return a;
	}

	public static void main(String[] args) {
		MaxPointsAtALine mpal = new MaxPointsAtALine();
		Point[] points = new Point[] { new Point(3, 4), new Point(4, 4),
				new Point(6, 4), new Point(9, 4) };
		Utils.Print("" + mpal.maxPoints(points));
	}
}
