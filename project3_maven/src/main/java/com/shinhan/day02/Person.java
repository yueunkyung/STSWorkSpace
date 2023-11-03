package com.shinhan.day02;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
public class Person {
	String name;
	String phone;
	CarVO car;
	List<String> major;
	List<License> llist;
	Map<String, Book> library;
	Set friend;
	Properties myprofile;
}
