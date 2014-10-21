package cn.sspku.zx.aad.daily;

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

	public static void Print(String str) {
		System.out.println(str);
	}
}
