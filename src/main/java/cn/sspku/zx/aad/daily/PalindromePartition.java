package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 * 
 * @author zhangxu
 * 
 */
public class PalindromePartition {
	public List<List<String>> partition(String s) {
		List<List<String>> partitions = new ArrayList<List<String>>();
		// 检查边界
		if (null == s || s.length() == 0) {
			partitions.add(null);
			return partitions;
		}

		return partitions;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
