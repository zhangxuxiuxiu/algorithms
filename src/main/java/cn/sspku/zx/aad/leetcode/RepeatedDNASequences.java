package cn.sspku.zx.aad.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 * 
 * For example,
 * 
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * 
 * Return: ["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * @author zhangxu
 * 
 */
public class RepeatedDNASequences {
	private static final int WINDOWSIZE = 10;

	public List<String> findRepeatedDnaSequences(String s) {
		if (null == s)
			return null;

		List<String> replica = new LinkedList<String>();
		if (s.length() <= WINDOWSIZE)
			return replica;

		Map<String, Integer> replicas = new HashMap<String, Integer>();
		for (int idx = 0; idx < s.length() - WINDOWSIZE; ++idx) {
			String rep = s.substring(idx, idx+WINDOWSIZE);
			if (replicas.containsKey(rep))
				replicas.put(rep, 1 + replicas.get(rep));
			else
				replicas.put(rep, 1);
		}

		Iterator<String> it = replicas.keySet().iterator();
		while (it.hasNext()) {
			String tpr = it.next();
			if (replicas.get(tpr) > 1)
				replica.add(tpr);
		}

		return replica;
	}

	public static void main(String[] args) {
		RepeatedDNASequences rs = new RepeatedDNASequences();
		Utils.PrintString(rs
				.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
	}
}
