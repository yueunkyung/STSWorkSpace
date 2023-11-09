package com.shinhan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.EmpVO;
import com.shinhan.util.DBUtil;

//DAO(Data Access Object) CRUD작업 (Create Read Update Delete)
//						  insert into~, select~, update~, delete
//POJO(Plain Old Java Object) : 정해진 규칙없이 자바로 만들어진 class
//JDBC를 이용하여 만든 JAVA코드이다... 계속 사용예정..

//<bean id="empDAO" class="com.shinhan.model.EmpDAO">
// @Repository => @Component + DAO
@Repository
public class EmpDAOMybatis {

	public int empInsert(EmpVO emp) {
		int result = 0;

		return result;
	}

	public int empUpdate(EmpVO emp) {
		int result = 0;

		return result;
	}

	public int empDelete(int empid) {
		String sql = "delete from employees where employee_id =?";
		return result;
	}

	public EmpVO loginCheck(String email, int password) {
		EmpVO emp = null;
		String sql = "select *" + " from employees where email = ? and department_id = ?";
		return emp;
	}

	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		return emp;
	}

	public List<EmpVO> selectAll(int deptid, String jobid, int sal, String hiredate) {
		List<EmpVO> emplist = new ArrayList<>();

		return emplist;
	}

	public List<EmpVO> selectManagerAll() {
		List<EmpVO> emplist = new ArrayList<>();

		return emplist;
	}

}