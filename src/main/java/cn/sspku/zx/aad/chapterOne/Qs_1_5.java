package cn.sspku.zx.aad.chapterOne;

/**
 * Created by zhangxu on 22/4/14.
 *
 * 问题描述，对于N个乱序实数，求他们在实轴上相邻两个数之间的
 *          最大差值。（必须是线性时间算法）
 */
public class Qs_1_5 {
    public static void main(String[] args)
    {
       // double[] in=new double[]{2.3,3.1,7.5,1.5,6.3};
      //  double[] in2 = new double[]{2, 1, 5, 3, 4, 8};
        double[] in3 = new double[]{10, 13, 17, 1};

        double result = get_max_interval(in3);
        System.out.println(result);
    }

    public static double get_max_interval(double[] in)
    {
        if (in.length <= 0)
            return 0.0;

        double small = in[0];
        double large = in[0];
        double interval = large - small;

        for(int i = 1; i < in.length; ++i)
        {
            double next = in[i];

            if(next > large && next - large > interval)
            {
                small = large;
                large = next;
                interval = large - small;
            }
            else if(next < small && small - next > interval)
            {
                large = small;
                small = next;
                interval = large - small;
            }
            else if(small < next && next < large)
            {
                if(next - small > large - next)
                {
                    large = next;
                    interval = large - small;
                }
                else
                {
                    small = next;
                    interval = large - small;
                }
            }
        }

        return interval;
    }
}
