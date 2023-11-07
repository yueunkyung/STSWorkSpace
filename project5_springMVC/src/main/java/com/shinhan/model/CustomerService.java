package com.shinhan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dto.CustomerVO;

@Service	//@Component + Service
public class CustomerService {
	@Autowired
	CustomerDAO dao;

	//����ڰ� �����ϴ���?
	public CustomerVO loginCheck(String custid, String pwd) {
		return dao.loginCheck(custid, pwd);
	}

	//ȸ������
	public int register(CustomerVO customer) {
		return dao.register(customer);
	}
}
