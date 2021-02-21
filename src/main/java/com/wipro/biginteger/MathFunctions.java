package com.wipro.biginteger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

public class MathFunctions implements Operator {

	public static void main(String[] args) {
		MathFunctions m1 = new MathFunctions();
		//Queue<Integer> queue = m1.add("1600", "91000");
		Queue<Integer> queue = m1.add("700", "5");

		// Stack<Integer> stack =m1.sub("-12", "-3");

		// Stack<Integer> stack =m1.sub("-3", "-12");

		// Stack<Integer> stack =m1.sub("10", "02");

		//Map<String, Stack<Integer>> mapStack = m1.sub("10", "12");
		
		Map<String, Stack<Integer>> mapStack = m1.sub("-3", "-7");

		StringBuilder input1 = new StringBuilder();

		// append a string into StringBuilder input1
		do {
			input1.append(queue.poll());
		} while (!queue.isEmpty());
		// input1.append();
		System.out.println(input1.reverse());

		StringBuilder input2 = new StringBuilder();

		// append a string into StringBuilder input1
		boolean signNegative = false;
		if (mapStack.containsKey("-")) {
			signNegative = true;
		}

		Stack<Integer> stack = mapStack.get(signNegative ? "-" : "+");
		do {
			input2.append(stack.pop());
		} while (!stack.isEmpty());
		// input1.append();
		if (signNegative) {
			System.out.println("OutPut: " + "-" + input2);
		} else {
			System.out.println("OutPut: " + input2);
		}
	}

	public Queue<Integer> add(String OPERAND1, String OPERAND2) {
		System.out.println(OPERAND1+" ,"+OPERAND2);
		Stack<Integer> opd1 = new Stack<Integer>();
		Stack<Integer> opd2 = new Stack<Integer>();
		Queue<Integer> opd = new LinkedList<Integer>();
		int opd1size = OPERAND1.length();
		int opd2size = OPERAND2.length();
		if(opd1size > opd2size){
			OPERAND2=StringUtils.leftPad(OPERAND2,opd1size,"0");
		}else if(opd1size < opd2size){
			OPERAND1=StringUtils.leftPad(OPERAND1,opd2size,"0");
		}
		
		System.out.println(OPERAND1+" ,"+OPERAND2);
		for (int i = 0; i < opd1size; i++) {
			opd1.push(Integer.parseInt(Character.toString(OPERAND1.charAt(i))));
		}

		for (int i = 0; i < opd1size; i++) {
			opd2.push(Integer.parseInt(Character.toString(OPERAND2.charAt(i))));
		}

		int reminder = 0;
		boolean isReminderwillAdd = false;
		do {
			int temp = opd1.pop() + opd2.pop() + reminder;
			if (temp < 10) {
				opd.add(temp);
				isReminderwillAdd = false;
			} else {
				reminder = temp % 10;
				opd.add(reminder);
				reminder = temp / 10;
				isReminderwillAdd = true;

			}

		} while (!opd1.empty() && !opd2.empty());
		if (isReminderwillAdd && opd1.empty() && opd2.empty()) {
			opd.add(reminder);
		}

		if (reminder != 0 && !opd1.empty()) {
			opd.add(reminder + opd1.pop());
		}

		if (reminder != 0 && !opd2.empty()) {
			opd.add(reminder + opd2.pop());
		}

		if (reminder == 0 && !opd1.empty()) {
			opd.add(reminder + opd1.pop());
		}

		if (reminder == 0 && !opd2.empty()) {
			opd.add(reminder + opd2.pop());
		}

		return opd;
	}

	public Map<String, Stack<Integer>> sub(String OPERAND1, String OPERAND2) {

		Stack<Integer> opd1 = new Stack<Integer>();
		Stack<Integer> opd2 = new Stack<Integer>();
		Map<String, Stack<Integer>> map = new HashMap<String, Stack<Integer>>();

		Stack<Integer> opd = new Stack<Integer>();
		// Stack<Integer> opd1;// = new Stack<>();
		int opd1size = OPERAND1.length();
		boolean isFirstParamNegative = false;
		boolean isSecondParamNegative = false;
		int j = 0;
		if (OPERAND1.startsWith("-")) {
			isFirstParamNegative = true;
			j = 1;

		}
		int opd2size = OPERAND2.length();

		for (int i = j; i < opd1size; i++) {
			opd1.push(Integer.parseInt(OPERAND1.charAt(i) + ""));
		}

		j = 0;

		if (OPERAND2.startsWith("-")) {
			isSecondParamNegative = true;
			j = 1;
		}

		for (int i = j; i < opd2size; i++) {
			opd2.push(Integer.parseInt(OPERAND2.charAt(i) + ""));
		}
		if (isFirstParamNegative && isSecondParamNegative) {
			// check for the greater
			int k = OPERAND1.compareTo(OPERAND2);
			
			System.out.println("Value of OPERAND1: " + OPERAND1 +",OPERAND2: "+OPERAND2);
			System.out.println("Value of K: " + k);

			if (k < 0) {
				System.out.println("Negative value" + k);
				opd = getFinalValue(opd1, opd2);
				map.put("-", opd);

				System.out.println("-");
			} else if (k > 0) {
				System.out.println("Positive value" + k);

				opd = getFinalValue(opd2, opd1);
				map.put("-", opd);
				System.out.println("+");
			}

		} else if (!isFirstParamNegative && !isSecondParamNegative) {
			System.out.println("Both provided no is positive. Param1: " + OPERAND1 + ", param2=" + OPERAND2);
			int k = OPERAND1.compareTo(OPERAND2);
			System.out.println("Value of K: " + k);
			if (k > 0) {
				System.out.println("Positive value: " + k);
				opd = getFinalValue(opd1, opd2);
				map.put("+", opd);

				System.out.println("+");
			} else if (k < 0) {
				System.out.println("Negative value: " + k);

				opd = getFinalValue(opd2, opd1);

				map.put("-", opd);
				System.out.println("-" + opd);

			}

			// opd =getFinalValue(opd1,opd2);
		}

		return map;

	}

	public Stack<Integer> getFinalValue(Stack<Integer> opd1, Stack<Integer> opd2) {
		Stack<Integer> opd = new Stack<Integer>();
		int reminder = 0;
		boolean isReminderwillDeduct = false;
		do {
			int a = opd1.pop() - reminder;
			int b = opd2.pop();
			int temp;
			if (a < b && !opd1.isEmpty()) {
				temp = (10 + a) - b;
				isReminderwillDeduct = true;
				reminder = 1;
			} else {
				temp = a - b;
				isReminderwillDeduct = false;
				reminder = 0;
			}
			opd.add(temp);

		} while (!opd1.empty() && !opd2.empty());
		if (isReminderwillDeduct && opd1.empty() && opd2.empty()) {
			opd.add(reminder);
		}

		if (reminder != 0 && !opd1.empty()) {
			opd.add(opd1.pop() - reminder);
		}

		if (reminder != 0 && !opd2.empty()) {
			opd.add(opd2.pop() - reminder);
		}

		if (reminder == 0 && !opd1.empty()) {
			opd.add(opd1.pop() - reminder);
		}

		if (reminder == 0 && !opd2.empty()) {
			opd.add(opd2.pop() - reminder);
		}

		return opd;
	}

}
