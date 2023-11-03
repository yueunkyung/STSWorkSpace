package com.shinhan.model;

import java.util.List;

import com.shinhan.dto.DeptVO;

//Service : 비지니스로직 담당
//POJO(Plain Old Java Object) : 정해진 규칙없이 자바로 만들어진 class
//계속 사용예정..
public class DeptService {
	DeptDAO dao = new DeptDAO();
	//특정Location
	public List<DeptVO> selectByLocation(int loc) {
		return dao.selectByLocation(loc);		
	}
	//특정Manager
	public List<DeptVO> selectByManager() {
		return dao.selectByManager();		
	}
	//특정부서
	public DeptVO selectById(int deptid) {
		return dao.selectById(deptid);
	}
	//all
	public List<DeptVO> selectAll() {
		return dao.selectAll();	
	}
	public int insertDept(DeptVO dept) {		
		return dao.insertDept(dept);
	}
	public int updateDept(DeptVO dept) {		
		return dao.updateDept(dept);
	}
	public int deleteDept(int deptid) {
		return dao.deleteDept(deptid);
	}
}
