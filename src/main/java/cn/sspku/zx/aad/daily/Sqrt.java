package cn.sspku.zx.aad.daily;

/**
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * 
 * @author zhangxu
 * 
 */
public class Sqrt {

	public int sqrt(int x) {
        if(x<1) return 0;
        
        double threshold=1e-15,r=x*0.5;
        while(Math.abs(r-x/r)>threshold)
        {
        	r=(r+x/r)/2;
        }
        
        return (int) Math.floor(r);
    }
	
	public static void main(String[] args) {
		int x=16;
		System.out.println("Sqrt Of "+x+" is "+new Sqrt().sqrt(x));
	}
}
