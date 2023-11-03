package com.shinhan.day01;

//구현 class ... 반드시 추상 메서드를 구현할 의무가 있다.
public class LgTV implements TV {

	public LgTV() {
		System.out.println("LgTV default 생성자");
	}
	
	public LgTV(String model) {
		System.out.println(model + "--LGTV생성");
	}
	
	@Override
	public void powerOn() {
		System.out.println("-------------------------");
		System.out.println(getClass().getSimpleName()+"전원켜기");
		System.out.println("-------------------------");
	}

	@Override
	public void powerOff() {
		System.out.println("************************");
		System.out.println(getClass().getSimpleName()+"전원끄기");
		System.out.println("************************");
	}

}
