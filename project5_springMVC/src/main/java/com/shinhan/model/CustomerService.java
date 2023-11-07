package com.shinhan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dto.CustomerVO;

@Service	//@Component + Service
public class CustomerService {
	@Autowired
	CustomerDAO dao;

	//사용자가 존재하는지?
	public CustomerVO loginCheck(String custid, String pwd) {
		return dao.loginCheck(custid, pwd);
	}

	//회원가입
	public int register(CustomerVO customer) {
		return dao.register(customer);
	}
}
