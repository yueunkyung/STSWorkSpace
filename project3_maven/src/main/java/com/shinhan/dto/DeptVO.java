package com.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

//DTO(Data Transfer Object)
//VO(ValueObject)
//JavaBean : 자바 객체

//DTO(Data Transfer Object), VO(Value Object)
public class DeptVO {
	private int department_id;
	private String department_name;
	private int manager_id;
	private int location_id;
}