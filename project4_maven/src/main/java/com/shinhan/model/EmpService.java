package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dto.EmpVO;

//@Component
@Service("eService")
public class EmpService {
	@Autowired
	EmpDAO dao;

	public int empInsert(EmpVO emp) {
		return dao.empInsert(emp);		
	}
	public int empUpdate(EmpVO emp) {
		return dao.empUpdate(emp);		
	}
	public EmpVO loginCheck(String email, int password) {
		return dao.loginCheck(email, password);
	}
	public EmpVO selectById(int empid) {
		return dao.selectById(empid) ;
	}

	public List<EmpVO> selectAll() {
		return dao.selectAll();
	}

	public List<EmpVO> selectManagerAll() {
		return dao.selectManagerAll();		
	}
}