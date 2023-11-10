package com.shinhan.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.EmpVO;

//DAO(Data Access Object) CRUD작업 (Create Read Update Delete)
//						  insert into~, select~, update~, delete
//POJO(Plain Old Java Object) : 정해진 규칙없이 자바로 만들어진 class
//JDBC를 이용하여 만든 JAVA코드이다... 계속 사용예정..

//<bean id="empDAO" class="com.shinhan.model.EmpDAO">
// @Repository => @Component + DAO

@Repository
public class EmpDAOMybatis {

	@Autowired
	SqlSession sqlSession;

	String namespace="net.firstzone.emp.";
	
	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	
	public int empDelete(int empid) {	
		System.out.println(empid+"76?&&?==============================================");
		int result = sqlSession.delete(namespace + "empDelete", empid);
		logger.info("삭제할 직원번호:{}...성공여부:{}", empid, result);
		return result;
	}
	
	public int empInsert(EmpVO emp) {
		int result = sqlSession.insert(namespace + "empInsert", emp);
		logger.info("입력할 직원:{}...성공여부:{}", emp.toString(), result);
		return result;
	}

	public int empUpdate(EmpVO emp) {
		int result = sqlSession.update(namespace + "empUpdate", emp);
		logger.info("수정할 직원:{}...성공여부:{}", emp.toString(), result);
		return result;
	}

	public EmpVO loginCheck(String email, int pass) {
		 return null;
	}

	public EmpVO selectById(int empid) {
		EmpVO emp = sqlSession.selectOne(namespace + "selectById", empid);
		logger.info("조회할 직원번호:{}...결과:{}", empid, emp.toString());
		return emp;
	}

	public List<EmpVO> selectAll(int deptid, String job, int sal, String hdate) {
		
		Map<String,Object> mapData = new HashMap<>();
		mapData.put("deptid", deptid);
		mapData.put("job", job);
		mapData.put("sal", sal);
		mapData.put("hdate", hdate);
		List<EmpVO> emplist = sqlSession.selectList(namespace + "selectAll", mapData);
		logger.info("조회조건:{}...결과:{}건", mapData, emplist.size());
		return emplist;
	}

	public List<EmpVO> selectManagerAll() {
		List<EmpVO> emplist = sqlSession.selectList(namespace + "selectManageAll");
		logger.info("결과:{}건", emplist.size());
		return emplist;
	}

}