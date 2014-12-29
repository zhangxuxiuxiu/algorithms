package cn.sspku.zx.aad.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * 
 * @author zhangxu
 * 
 */
public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> rets = new ArrayList<String>();
		if (n < 1)
			return rets;

		generate(rets, new char[n * 2], 0, 0, 0, n);

		return rets;
	}

	void generate(List<String> lst, char[] braces, int location, int left,
			int right, int n) {
		if (location == n * 2)
		{
			lst.add(new String(braces));
			return;
		}
		
		if (left < n) {
			braces[location] = '(';
			generate(lst, braces, location+1, left + 1, right, n);
		}
		if(right<left)
		{
			braces[location] = ')';
			generate(lst, braces, location+1, left, right+1, n);
		}
	}

	static void print(List<String > lsts)
	{
		Iterator<String> it=lsts.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
	
	public static void main(String[] args) {
		print(new GenerateParentheses().generateParenthesis(3));
	}
}
