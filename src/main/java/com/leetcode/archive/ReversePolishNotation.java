package com.leetcode.archive;

//https://leetcode.com/problems/evaluate-reverse-polish-notation/description/

import java.util.*;
import java.util.function.BiFunction;

// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
// Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
//
// Note that division between two integers should truncate toward zero.
//
// It is guaranteed that the given RPN expression is always valid.
// That means the expression would always evaluate to a result, and there will not be any division by zero operation.
public class ReversePolishNotation {
    public static void main(String[] args) {
        test(new String[]{"0"}, 0);
        test(new String[]{"-5", "1", "*"}, -5);
        test(new String[]{"-5", "2", "/"}, -2);
        test(new String[]{"5", "2", "/"}, 2);
        test(new String[]{"2", "1", "+", "3", "*"}, 9);
        test(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22);
        test(new String[]{"4", "13", "5", "/", "+"}, 6);

    }

    private static void test(String[] tokens, int expected) {
        System.out.println(Arrays.toString(tokens));
        int actual = evalRPN(tokens);
        System.out.println("expected= " + expected);
        System.out.println("actual= " + actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int evalRPN(String[] tokens) {
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.charAt(token.length() - 1) >= '0') {
                s.push(Integer.parseInt(token));
                continue;
            }
            Integer b = s.pop();
            Integer a = s.pop();
            if (token.equals("+")) {
                s.push(a + b);
                continue;
            }
            if (token.equals("-")) {
                s.push(a - b);
                continue;
            }
            if (token.equals("*")) {
                s.push(a * b);
                continue;
            }
            s.push(a / b);
        }
        return s.pop();
    }

    public static int evalRPNUsingCallStack(String[] tokens) {
        return evalUsingCallStack(tokens, tokens.length - 1);
    }

    public static int evalUsingCallStack(String[] tokens, int i) {
        String token = tokens[i];
        tokens[i] = "";
        if (token.charAt(token.length() - 1) >= '0') {
            return Integer.parseInt(token);
        }
        i--;
        int b = evalUsingCallStack(tokens, i);
        while (tokens[i].equals("")) {
            i--;
        }
        int a = evalUsingCallStack(tokens, i);
        if (token.equals("+"))
            return a + b;
        if (token.equals("-"))
            return a - b;
        if (token.equals("*"))
            return a * b;
        return a / b;
    }

    private static Map<String, BiFunction<Integer, Integer, Integer>> operators = new HashMap<>();

    static {
        operators.put("+", Integer::sum);
        operators.put("-", (a, b) -> a - b);
        operators.put("*", (a, b) -> a * b);
        operators.put("/", (a, b) -> a / b);
    }

    public static int evalRPNOperatorMap(String[] tokens) {
        return evalOperatorMap(tokens, tokens.length - 1);
    }

    private static int evalOperatorMap(String[] tokens, int i) {
        String token = tokens[i];
        tokens[i] = "!";
        BiFunction<Integer, Integer, Integer> f = operators.get(token);
        if (f != null) {
            System.out.println();
            i--;
            int e2 = evalOperatorMap(tokens, i);
            while (tokens[i].equals("!")) {
                i--;
            }
            int e1 = evalOperatorMap(tokens, i);
            System.out.println(e1 + " " + token + " " + e2);
            return f.apply(e1, e2);
        }
        tokens[i] = "!";
        return Integer.parseInt(token);
    }
}
