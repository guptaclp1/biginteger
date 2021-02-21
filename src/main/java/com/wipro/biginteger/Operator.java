package com.wipro.biginteger;

import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public interface Operator {
	
	Queue<Integer> add(String OPERAND1, String OPERAND2);
	
	//Stack<Integer> sub(String OPERAND1, String OPERAND2);
	
	Map<String,Stack<Integer>> sub(String OPERAND1, String OPERAND2);
	
	

}
