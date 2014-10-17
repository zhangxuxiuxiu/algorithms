package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
 * [1,5],[6,9].
 * 
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in
 * as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * 
 * @author zhangxu
 * 
 */
public class IntervalInserter {
	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (null == intervals || intervals.size() == 0 || null == newInterval)
			return intervals;

		Interval itl = null;
		int idx = 0;
		Iterator<Interval> it = intervals.iterator();
		while (it.hasNext()) {
			itl = it.next();
			// 找到第一个start大于newInterval.start的interval,然后插在前面
			if (itl.start > newInterval.start) {
				intervals.add(idx, newInterval);
				break;
			}
			++idx;
		}
		// newInterval是最后的Interval
		if (idx == intervals.size())
			intervals.add(idx, newInterval);

		return merge(intervals);
	}

	/**
	 * precondition:所有interval按照start从小到大排列
	 * 
	 * @param intervals
	 * @return
	 */
	public List<Interval> merge(List<Interval> intervals) {
		int leftBound = Integer.MIN_VALUE, rightBound = Integer.MAX_VALUE;
		Interval itl = null;
		List<Interval> ret = new ArrayList<Interval>();

		if (null == intervals || intervals.size() == 0)
			return ret;

		Collections.sort(intervals,new IntervalComparator());
		Iterator<Interval> it = intervals.iterator();
		while (it.hasNext()) {
			itl = it.next();
			if (leftBound == Integer.MIN_VALUE) {
				leftBound = itl.start;
				rightBound = itl.end;
			} else {
				if (itl.start <= rightBound) {
					rightBound = Math.max(rightBound, itl.end);
				} else {
					ret.add(new Interval(leftBound, rightBound));
					leftBound = itl.start;
					rightBound = itl.end;
				}
			}
		}
		ret.add(new Interval(leftBound, rightBound));

		return ret;
	}

	private static class IntervalComparator implements Comparator<Interval> {

		public int compare(Interval _it1, Interval _it2) {
			if (_it1.start > _it2.start)
				return 1;
			if (_it1.start == _it2.start)
				return 0;
			return -1;
		}

	}

	public static void main(String[] argv) {
		IntervalInserter ii = new IntervalInserter();
		List<Interval> intervals = new LinkedList<Interval>();
		intervals.add(new Interval(1, 2));
		intervals.add(new Interval(3, 5));
		intervals.add(new Interval(6, 7));
		intervals.add(new Interval(8, 10));
		intervals.add(new Interval(12, 16));
		Interval i = new Interval(4, 9);
		// [1,2],[3,5],[6,7],[8,10],[12,16] [4,9]
		/*
		 * for (Interval t : ii.insert(intervals, i)) { System.out.println("[" +
		 * t.start + "," + t.end + "]"); }
		 */
		for (Interval t : ii.insert(intervals, i)) {
			System.out.println("[" + t.start + "," + t.end + "]");
		}
	}
}
