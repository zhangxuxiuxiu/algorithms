package cn.sspku.zx.aad.daily;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3, Return [1,3,3,1].
 * 
 * Note: Could you optimize your algorithm to use only O(k) extra space?
 * 
 * @author zhangxu
 * 
 */
public class PascalTreeII {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> ret = new ArrayList<Integer>();

		if (rowIndex == 0)
			ret.add(1);
		if (rowIndex == 1) {
			ret.add(1);
			ret.add(1);
		}
		
		int[] firstRow = new int[rowIndex + 1], secRow = new int[rowIndex+1];
		firstRow[0]=1;
		secRow[0]=1;
		secRow[1]=1;
		
		int row=2;
		while(row<rowIndex)
		{
			for(int idx=1;idx<row;++idx)
			{
				firstRow[idx]=secRow[idx-1]+secRow[idx];
			}
			firstRow[row]=1;
			
			for(int idx=1;idx<row+1;++idx)
			{
				secRow[idx]=firstRow[idx-1]+firstRow[idx];
			}
			secRow[row+1]=1;
			
			row+=2;
		}
		//row 为双数
		if(row==rowIndex)
		{
			for(int idx=1;idx<row;++idx)
			{
				firstRow[idx]=secRow[idx-1]+secRow[idx];
			}
			firstRow[row]=1;
			
			return toList(firstRow);
		}
		
		return toList(secRow);
	}
	
	public static List<Integer> toList(int[] a)
	{
		List<Integer> lst=new ArrayList<Integer>();
		for(int idx=0;idx<a.length;++idx)
		{
			lst.add(a[idx]);
		}
		
		return lst;
	}
	
	public static void Print(List<Integer> lst)
	{
		Iterator<Integer> it=lst.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
	
	public static void main(String[] args)
	{
		PascalTreeII tree=new PascalTreeII();
		List<Integer> lst=tree.getRow(5);
		Print(lst);
	}
}
