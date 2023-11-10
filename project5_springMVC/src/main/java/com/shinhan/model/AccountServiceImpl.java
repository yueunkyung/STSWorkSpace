package com.shinhan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(propagation = Propagation.REQUIRED) // tracsaction 전파규칙 설정 //default여서 작성안해도 됨.
public class AccountServiceImpl {
	@Autowired
	AccountDAOMybatis dao;

	public void update() throws DataAccessException {
		dao.update1();
		dao.update2();
	}
}