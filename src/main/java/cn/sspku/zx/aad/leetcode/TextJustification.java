package cn.sspku.zx.aad.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * For example, words: ["This", "is", "an", "example", "of", "text",
 * "justification."] L: 16.
 * 
 * Return the formatted lines as: [ "This    is    an", "example  of text",
 * "justification.  " ] Note: Each word is guaranteed not to exceed L in length.
 * 
 * click to show corner cases.
 * 
 * Corner Cases: A line other than the last line might contain only one word.
 * What should you do in this case? In this case, that line should be
 * left-justified.
 * 
 * @author zhangxu
 * 
 */
public class TextJustification {
	public List<String> fullJustify(String[] words, int L) {
		if (null == words)
			return null;

		List<String> lines = new LinkedList<String>();

		int cur = 0, minLineLen, endCur, moreSpaceCount, eachSpaceInterval;
		while (cur < words.length) {
			endCur = cur;
			minLineLen = 0;
			// 找到当前行所包含的单词
			while (endCur < words.length
					&& minLineLen + words[endCur].length() <= L) {
				minLineLen += words[endCur].length() + 1;
				++endCur;
			}

			String line = "";
			if (endCur == words.length) {
				while (cur < words.length - 1)
					line += words[cur++] + " ";
			} else {
				--minLineLen;// 最后一个单词后面没有个空格
				if (endCur - cur > 1) {
					eachSpaceInterval = (L - minLineLen) / (endCur - cur - 1)
							+ 1;// 平均单词间间隔
					moreSpaceCount = (L - (minLineLen - endCur + cur + 1))
							- eachSpaceInterval * (endCur - cur - 1);// 前面多少个间隔比平均间隔多一个space
				} else {
					eachSpaceInterval = 0;
					moreSpaceCount = 0;
				}
				System.out.println("eachSpaceInterval=" + eachSpaceInterval
						+ " moreSpaceInterval=" + moreSpaceCount);
				for (int idx = 0; idx < moreSpaceCount; ++idx)
					line += words[cur + idx] + nSpace(eachSpaceInterval + 1);
				for (int idx = moreSpaceCount; idx < endCur - cur - 1; ++idx)
					line += words[cur + idx] + nSpace(eachSpaceInterval);
			}
			line += words[endCur - 1];
			if (endCur - cur == 1)
				line += nSpace(L - line.length());
			cur = endCur;
			System.out.println(line.length());
			lines.add(line);
		}

		return lines;
	}

	private String nSpace(int n) {
		char[] str = new char[n];
		Arrays.fill(str, ' ');
		return new String(str);
	}

	public static void main(String[] args) {
		TextJustification tj = new TextJustification();
		 String[] words=new String[] { "This", "is", "an",
		 "example", "of", "text", "justification." };
		 int len=16;
//		String[] words = new String[] { "What", "must", "be", "shall", "be." };
//		int len = 12;
		Utils.PrintString(tj.fullJustify(words, len));
	}
}
