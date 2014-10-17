package cn.sspku.zx.aad.daily;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * @author zhangxu
 * 
 */
public class SimpleDivision {

	public int divide(int dividend, int divisor) {
		int op = 1;
		if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0)
			op = -1;
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		int result = 0, incr = 1, tpr = divisor,upperLimit=1<<31;;

		while (tpr < dividend) {
			tpr <<= 1;
			incr <<= 1;
			if (tpr >= upperLimit) {
				dividend -= tpr;
				result += incr;
				break;
			}			
		}

		while (dividend >= divisor) {
			while (tpr > dividend) {
				tpr >>= 1;
				incr >>= 1;
			}
			if (tpr == dividend) {
				result += incr;
				break;
			}
			dividend -= tpr;
			result += incr;
		}

		return op * result;
	}

	public static void main(String[] args) {
		SimpleDivision sd = new SimpleDivision();
		int dividend = 2147483647, divisor = 1;
	    System.out.println("" + dividend + "/" + divisor + "="
		 + sd.divide(dividend, divisor) + " Equal? "
		 + (sd.divide(dividend, divisor) == dividend / divisor));
	}

}
