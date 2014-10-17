package cn.sspku.zx.aad.chapterOne;

/**
 * Created by zhangxu on 22/4/14.
 *
 * ������������������������������������������1~10^9���������������������������������������������
 *         ������������������������������������0-9.
 *
 * ���������������������������������456���
 *                  ������������������������1-3������������100���������
 *              4���������������56+1���������
 *                  ������������������������0-9������������3*10���������
 *              0-4������������10������������5������������7������������100
 *              ������0-9������������������������
 *                  ������������������������0-9������������45���������0-6
 *              ���������1���������������10������0-9������������
 *
 *         ������������������������������������������0������������������0������������
 *              ������������������������456���
 *                  ������400���������������������0-3������������100���
 *              ������������������0-9������������4*10���������������������
 *              0-9������������4*10������������4������57������
 *                  [00���50������������������0-4������10���������������
 *              0-9���������5������������������5������������7���
 *                  [0���6)���������������0-5������1������6������1������
 *              ���������0������������[0,1)������1*3������[1,10)���9*2������[10,100)������
 *              90*1������
 *         ������������������������������������
 */
public class Qs_1_1 {
    public static void main(String[] args)
    {
        try {
            int pages=99999;
            NumberCounterRefresher.NumberCounter nc=new NumberCounterRefresher().CountNumbers(pages);
            if(null!=nc)
                System.out.println(nc);
            else
                System.out.println("Failed in Counting!!!");

        } catch (Exception e) {
            System.err.println("Failure in reading the total pages!!!");
            e.printStackTrace();
        }
    }

    /**
     * ���������������������������������
     */
    private static class NumberCounterRefresher
    {
        private NumberCounter nc=new NumberCounter();

        public NumberCounter CountNumbers(int pages)  {
            if(pages<1||pages>Math.pow(10,9)) return null;

            //���������������������������������
            int before=pages,figure,divident=1;
            while(before>0)
            {
                figure=pages/divident%10;
                divident*=10;
                before=pages/divident;

                nc.updateCounter(0,9,before*divident/10);
                nc.updateCounter(0,figure-1,divident/10);
                nc.updateCounter(figure,figure,pages%(divident/10)+1);
            }

            //���������������������
            int exp=(int)Math.log10(pages);
            int tpr=exp,times;
            while(tpr>0)
            {
                times=(int)(Math.pow(10,tpr-1)-Math.pow(10,tpr)) *(exp-tpr+1);
                nc.updateCounter(0,0,times);
                --tpr;
            }

            nc.updateCounter(0,0,-(exp+1));

            return nc;
        }

        /**
         * ���������������������������������
         */
        private class NumberCounter
        {
            public int[] counter;

            public NumberCounter()
            {
                counter=new int[10];
            }

            public boolean updateCounter(int from,int to,int times)
            {
                if(to>=counter.length||from<0) return false;

                for(int idx=from;idx<=to;idx++)
                {
                    counter[idx]+=times;
                }

                return true;
            }

            @Override
            public String toString()
            {
                String outStr="";
                for(int idx=0;idx<counter.length;++idx)
                {
                    outStr+=String.format("%d appears %3d times;\n",idx,counter[idx]);
                }

                return outStr;
            }
        }
    }
}
