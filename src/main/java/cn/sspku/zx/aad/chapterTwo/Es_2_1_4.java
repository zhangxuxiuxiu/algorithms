package cn.sspku.zx.aad.chapterTwo;

/**
 * Created by zhangxu on 23/4/14.
 *
 * 问题描述：求N个元素R的全排列
 *
 * 问题分析：全排列Perm(R)=[r(i)Perm(R-r(i))] i=1...n
 */
public class Es_2_1_4 {
    public static void main(String[] args)
    {
        FullPermutation fp=new FullPermutation(3);
        fp.Permute();
    }

    private static class FullPermutation
    {
        private int[] array;


        private FullPermutation(int n)
        {
            array=new int[n];
            for(int idx=0;idx<array.length;++idx)
            {
                array[idx]=idx+1;
            }
        }

        public void Permute()
        {
            lineUp(0,array.length-1);
        }

        private void lineUp(int from,int to)
        {
            if(from==to)
            {
                System.out.print(this);
            }
            else
            {
                for(int idx=from;idx<=to;++idx)
                {
                    swap(from,idx);
                    lineUp(from + 1, to);
                    swap(idx,from);
                }
            }
        }

        /**
         * swap these two values here and there
         * @param here  the first position
         * @param there  the second position
         */
        private void swap(int here,int there)
        {
            if(here!=there)
            {
                int tpr=array[here];
                array[here]=array[there];
                array[there]=tpr;
            }
        }

        @Override
        public String toString()
        {
            String result="";
            for(int idx=0;idx<array.length;++idx)
            {
                result+="\t"+array[idx];
            }
            result+="\n";

            return result;
        }
    }
}
