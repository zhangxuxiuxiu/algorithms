package cn.sspku.zx.aad.leetcode;

/*
 * Compare two version numbers version1 and version1.
 If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

 You may assume that the version strings are non-empty and contain only digits and the . character.
 The . character does not represent a decimal point and is used to separate number sequences.
 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

 Here is an example of version numbers ordering:

 0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumbers {
	/**
	 * Compare two version numbers version1 and version1. If version1 > version2
	 * return 1, if version1 < version2 return -1, otherwise return 0.
	 * 
	 * You may assume that the version strings are non-empty and contain only
	 * digits and the . character.
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	public int compareVersion(String version1, String version2) {
		String[] vn1 = version1.split("\\."), vn2 = version2.split("\\.");

		// if (vn1.length == 0)
		// vn1 = new String[] { version1 };
		// if (vn2.length == 0)
		// vn2 = new String[] { version2 };

		int v1, v2, len = Math.min(vn1.length, vn2.length);
		for (int idx = 0; idx < len; ++idx) {
			// System.out.println(vn1[idx]);

			v1 = Integer.parseInt(vn1[idx]);
			v2 = Integer.parseInt(vn2[idx]);
			if (v1 > v2)
				return 1;
			if (v2 > v1)
				return -1;
		}

		if (vn1.length > vn2.length) {
			int idx = vn2.length;
			while (idx < vn1.length && Integer.parseInt(vn1[idx]) == 0)
				++idx;
			if (idx == vn1.length)
				return 0;
			return 1;
		}
		if (vn1.length < vn2.length)
		{
			int idx = vn1.length;
			while (idx < vn2.length && Integer.parseInt(vn2[idx]) == 0)
				++idx;
			if (idx == vn2.length)
				return 0;
			return -1;
		}
		
		return 0;
	}

	public static void main(String[] args) {
		CompareVersionNumbers cvn = new CompareVersionNumbers();
		System.out.println(cvn.compareVersion("1", "1.0.0"));
	}
}
