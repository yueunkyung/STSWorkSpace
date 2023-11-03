package com.shinhan.day01;

//구현 class ... 반드시 추상 메서드를 구현할 의무가 있다.
public class SamsungTV implements TV {
	public SamsungTV() {
		System.out.println("삼성TV생성자");
	}
	@Override
	public void powerOn() {
		System.out.println(getClass().getSimpleName()+"전원켜기");
	}

	@Override
	public void powerOff() {
		System.out.println(getClass().getSimpleName()+"전원끄기");
	}

}
