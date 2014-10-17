package cn.sspku.zx.aad.chapterOne;

/**
 * Created by zhangxu on 22/4/14.
 *
 * 问题描述：如果一个字符串内部的每个字符都比其前面的字符大，则称为升序字符串
 *          给定一个字符串，对于字母表'a'-'z'，在所有可能中，该字符串的位置
 *          比如“ac”的位置为28.
 *
 * 问题分析： 比如，对于字母序列“egz”，
 *      首先，它的前面排了C（26，1）个单字母，C(26,2)个双字母;
 *      接着，对于‘e’，它的前面排了(‘e’-('a'-1)-1)*C(('z'-('a'-1)-1),2)
 *           对于‘g‘，它的前面排了(’g‘-'e'-1)    *C(('z'-'e'-1),1)
 *           对于’z‘，它的前面排了('z'-'g'-1)    *C(('z'-'g'-1),0)
 *           所以“egz”的位置是（前面之和+1）
 */
public class Qs_1_2 {
    private static final int TOTALLETTERS=26;

    public static void main(String[] args)
    {
        String inStr="c";
        int position=0;
        int length=inStr.length();

        //calculate possibilities with which has a less length of string
        for(int idx=1;idx<length;++idx)
        {
            position+=composites(TOTALLETTERS,idx);
        }

        //calculate its position in its equal-length string list
        char previousChar='a'-1;
        for(int idx=0;idx<length;++idx)
        {
            position+=(inStr.charAt(idx)-previousChar-1)*composites(('z'-previousChar-1),(length-idx-1));
            previousChar=inStr.charAt(idx);
        }

        //mark its current position
        position+=1;

        System.out.println(position);
    }

    /**
     *
     * @param total    total items to choose from
     * @param selectors  select 'selectors' items from 'total' items
     * @return how many possibilities
     */
    private static int composites(int total,int selectors)
    {
        int tpr=selectors,divisor=1,dividend=1;
        for(int idx=0;idx<tpr;++idx)
        {
            divisor*=(total-idx);
            dividend*=(idx+1);
        }

        return divisor/dividend;
    }
}
