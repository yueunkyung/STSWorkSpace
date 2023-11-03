package com.shinhan.day03;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//VO(Value Object), DTO(Data Transfer Object)
//, JavaBean에 이용된다.
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter@ToString


//<bean 
//id="carcar" 
//class="com.shinhan.day02.CarVO"
//p:model="그랜져123" p:price="3000"
///>

//@Component
//@Component("carVO")
@Component("carcar")
public class CarVO {
	//1.field
	private String model="AAA";
	private int price=1000;
	/*
	public CarVO() {
		super();
		System.out.println("arg 없는 default 생성자로 생성하기");
	}
	public CarVO(String model, int price) {
		super();
		this.model = model;
		this.price = price;
		System.out.println("arg 2개 생성하기");
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
		System.out.println("setModel.........."+model);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
		System.out.println("setPrice.........."+price);
	}
	@Override
	public String toString() {
		return "CarVO [model=" + model + ", price=" + price + "]";
	}
	*/
}
