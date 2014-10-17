package cn.sspku.zx.aad.daily;

import java.util.Stack;

public class ReversePolishNotation {
	public Integer Calculate(String[] tokens) {
		Stack<Integer> stack=new Stack<Integer>();
        for(int idx=0;idx<tokens.length;++idx)
        {
            if(tokens[idx].equals("+") || tokens[idx].equals("-") || tokens[idx].equals("*")
				|| tokens[idx].equals("/"))
			{
			    Integer right = stack.pop(), left = stack.pop();

	            if(tokens[idx].equals("+")){ stack.push(left+right);}
	            else if(tokens[idx].equals("-")){ stack.push(left-right);}
	            else if(tokens[idx].equals("*")){ stack.push(left*right);}
	            else if(tokens[idx].equals("/")){ stack.push(left/right);}
			}
			else
			{
			    stack.push(Integer.parseInt(tokens[idx]));
			}
        }
        
        return stack.pop();
	}

	public static void main(String[] args) {
		String[] expression=new String[]{"4", "13", "5", "/", "+"};
		
		//System.out.println(expression);
		System.out.println("The result is :"+new ReversePolishNotation().Calculate(expression));
		
	}

}
