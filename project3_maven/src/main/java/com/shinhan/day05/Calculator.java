package com.shinhan.day05;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//Target...주관심사(core concern), 업무로직
@Component("proxyCal")
public class Calculator {
	public void add() {
		System.out.println("arg0개 가지고있는 add");
	}

	public int add(int x) {
		System.out.println("arg1개 가지고있는 add.... return 있음");
		return x + 1000;
	}

	public void add(int x, int y) {
		int result = x + y;
		System.out.println("arg2개 add 결과:" + result);
	}

	public void subtract(int x, int y) {
		int result = x - y;
		System.out.println("결과:" + result);
	}

	public void multiply(int x, int y) {
		int result = x * y;
		System.out.println("결과:" + result);
	}

	public void divide(int x, int y) {
		int result = x / y;
		System.out.println("결과:" + result);
	}
}
