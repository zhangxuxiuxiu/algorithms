package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0 01 - 1 11 - 3 10 - 2 Note: For a given n, a gray code sequence is not
 * uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the
 * above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code
 * sequence. Sorry about that.
 * 
 * @author zhangxu
 * 
 */
public class GrayCode {
	public List<Integer> grayCode(int n) {
		List<Integer> codes = new ArrayList<Integer>();
		if (n < 0)
			return codes;

		int[] code = new int[n];
		// 初始化为零
		for (int idx = 0; idx < n; ++idx)
			code[idx] = 0;

		coding(codes, code, n-1);

		return codes;
	}

	private void coding(List<Integer> codes, int[] code, int k) {
		if (k == -1) {
			codes.add(intirize(code));
			return;
		}

		coding(codes, code, k - 1);
		if (code[k] == 0)
			code[k] = 1;
		else
			code[k] = 0;
		coding(codes, code, k - 1);
	}

	private Integer intirize(int[] code) {
		int ret = 0;
		int idx = code.length - 1;
		while (idx >= 0)
			ret = ret * 2 + code[idx--];

		return ret;
	}

	static void print(List<Integer> lst)
	{
		Iterator<Integer> it=lst.iterator();
		while(it.hasNext())
			System.out.print(it.next());
	}
	public static void main(String[] args) {
		print(new GrayCode().grayCode(2));
	}
}
