package cn.sspku.zx.aad.leetcode;

import java.util.Iterator;
import java.util.List;

public class Utils {
	public static void Print(List<List<Integer>> list2) {
		Iterator<List<Integer>> listIt = list2.iterator();
		List<Integer> list;
		while (listIt.hasNext()) {
			list = listIt.next();
			Iterator<Integer> it = list.iterator();
			while (it.hasNext()) {
				System.out.printf("%5d", it.next());
			}
			System.out.println();
		}
	}

	public static void PrintString(List<String> list) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void Print(String str) {
		System.out.println(str);
	}

	public static void Print(int[] array)
	{
		for(int idx=0;idx<array.length;++idx)
			System.out.printf("%5d", array[idx]);
		System.out.println();
	}
	
	public static void PrintListString(List<List<String>> list2) {
		Iterator<List<String>> listIt = list2.iterator();
		List<String> list;
		while (listIt.hasNext()) {
			list = listIt.next();
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				System.out.print("  " + it.next());
			}
			System.out.println();
		}

	}
}
