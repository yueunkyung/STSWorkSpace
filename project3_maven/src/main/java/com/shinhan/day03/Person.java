package com.shinhan.day03;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Component	//class level에서 사용하는 어노테이션
public class Person {
	String name;
	String phone;
	
	@Autowired	//field, constructor, setter
	// 타입을 기준으로(byType) Bean을 찾아 주입한다.
	CarVO car;
	
	List<String> major;
	List<License> llist;
	Map<String, Book> library;
	Set friend;
	Properties myprofile;
}
