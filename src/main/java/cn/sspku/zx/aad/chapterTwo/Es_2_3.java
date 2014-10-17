package cn.sspku.zx.aad.chapterTwo;

/**
 * Created by zhangxu on 23/4/14.
 *
 * 问题描述：对于有序队列L和元素x，试用二分法查找x在L中的位置。
 *
 * 问题分析：二分查找法
 */
public class Es_2_3 {
    public static void main(String[] args)
    {
        BinarySearcher searcher=new BinarySearcher(10);
        int value=9;
        System.out.println(String.format("Position Of %d is %d",value,searcher.Search(value)));
    }

    private static class BinarySearcher
    {
        private int[] array;

        public BinarySearcher(int n)
        {
            array=new int[n];
            for(int idx=0;idx<n;++idx)
            {
                array[idx]=2*idx+1;
            }
        }

        public int Search(int value)
        {
            int left=0,right=array.length-1,mid;

            while(right>=left)
            {
                mid=(left+right)/2;
                if(array[mid]==value) return mid;
                else if(array[mid]>value) right=mid-1;
                else left=mid+1;
            }
            return -1;
            //return binarySearchRecursively(0,array.length,value);
        }

        /**
         * @param from   the begin index
         * @param to     the end   index
         * @param value  value to be searched
         * @return       the index of this value or -1 if not found
         */
       /* private int binarySearchRecursively(int from,int to,int value)
        {
            if(from>=to) return -1;

            int mid=(from+to)/2;
            if(array[mid]==value) return mid;
            else if(value>array[mid]) return binarySearchRecursively(mid+1,to,value);
            return binarySearchRecursively(from,mid,value);
        }
*/    }
}
