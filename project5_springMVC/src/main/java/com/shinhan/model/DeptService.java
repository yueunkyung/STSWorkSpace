package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.shinhan.dto.DeptVO;

//Spring이 DeptService를 객체 생성하고자 한다.
//Bean으로 등록한다.
//spring 설정파일 사용하는 경우 => <bean id="deptService" class="com.shinhan.model.DeptService" />
//어노테이션을 이용하기 : @Component + DAO 역할 => @Repository
@Service("dService")
public class DeptService {
	
	@Autowired
	DeptDAOMybatis dao;
	
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

	public List<DeptVO> selectByName(String dname) {
		return dao.selectByName(dname);
	}
}
