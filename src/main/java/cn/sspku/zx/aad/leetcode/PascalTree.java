package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * For example, given numRows = 5, Return
 * 
 * [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
 * 
 * @author zhangxu
 * 
 */
public class PascalTree {
    List<List<Integer>> ret = new ArrayList<List<Integer>>();

	public List<List<Integer>> generate(int numRows) {
		if (numRows < 1)
			return ret;
		else {
			for (int i = 1; i <= numRows; ++i)
				ret.add(generateRow(i));
		}

		return ret;
	}

	private  List<Integer> generateRow(int row) {
		ArrayList<Integer> lst = new ArrayList<Integer>();
		
		lst.add(1);
		if (row > 1) {
			List<Integer> preLst=ret.get(row-2);
			for (int idx = 1; idx < row-1; ++idx) {
				lst.add(preLst.get(idx-1)+preLst.get(idx));
			}
			lst.add(1);
		}
		
		return lst;
	}
}
