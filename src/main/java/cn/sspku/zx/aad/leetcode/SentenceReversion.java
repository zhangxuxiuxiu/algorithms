package cn.sspku.zx.aad.leetcode;

/**
 * 将“The Sky is blue”转换成“blue is Sky The”
 * 
 * @author zhangxu
 * 
 */
public class SentenceReversion {

	public static String reverse(String sentence) {
		String[] words = sentence.split(" ");
		int left = 0, right = words.length - 1;
		String tpr;
		while (left < right) {
			tpr = words[left];
			words[left] = words[right];
			words[right] = tpr;
			++left;
			--right;
		}

		left = -1;
		right = words.length;
		tpr = "";
		// 当sentence 为"  "时，words长度为零;
		if (words.length == 0)
			return tpr;

		tpr = words[++left];
		while (++left < right) {
			if (!words[left].equals(""))
				tpr += " " + words[left];
		}

		return tpr;
	}

	public static void main(String[] args) {
		String sentence = "  a  b";
		System.out.print(reverse(sentence));
	}

}
