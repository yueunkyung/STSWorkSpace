package com.shinhan.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.DeptVO;
import com.shinhan.util.DBUtil;

//Spring이 DeptDAO를 객체 생성하고자 한다.
//Bean으로 등록한다.
//spring 설정파일 사용하는 경우 => <bean id="deptDAO" class="com.shinhan.model.DeptDAO" />
//어노테이션을 이용하기 : @Component + DAO 역할 => @Repository
@Repository
public class DeptDAOMybatis {
	
	@Autowired
	SqlSession sqlSession;
	
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	
	String namespace="net.firstzone.dept.";
	
	//특정 Location 조회
	public List<DeptVO> selectByLocation(int loc) {
		List<DeptVO> deptlist = sqlSession.selectList(namespace+"selectByLocation", loc);
		
		return deptlist;
	}
	
	//Manager가 있는 부서조회
	public List<DeptVO> selectByManager() {
		return sqlSession.selectList(namespace+"selectByManager");
	}

	//특정부서조회
	public DeptVO selectById(int deptid) {
		DeptVO dept = sqlSession.selectOne(namespace+"selectById", deptid);
		logger.info(getClass().getSimpleName()+":"+dept);
		
		return dept;
	}
	
	//모두조회
	public List<DeptVO> selectAll() {
		List<DeptVO> deptlist = sqlSession.selectList(namespace+"selectAll");
		logger.info(getClass().getSimpleName()+":" + deptlist.size());
		
		return deptlist;
	}
	
	public int insertDept(DeptVO dept) {
		int result = sqlSession.insert(namespace+"insertDept", dept);
		logger.info(getClass().getSimpleName()+":" + result +"건 !! insert !!");
		
		return result;
	}

	public int updateDept(DeptVO dept) {
		int result = sqlSession.update(namespace+"updateDept", dept);
		logger.info(getClass().getSimpleName()+":" + result +"건 !! update @@");
		
		return result;
	}

	public int deleteDept(int deptid) {
		int result = sqlSession.delete(namespace+"deleteDept", deptid);
		logger.info(getClass().getSimpleName()+":" + result +"건 @@ delete ##");
		
		return result;
	}

	public List<DeptVO> selectByName(String dname) {
		List<DeptVO> dlist = sqlSession.selectList(namespace+"selectByName", "%"+dname+"%");
		logger.info(getClass().getSimpleName()+":" + dlist.size() +"건 && 조회 &&");
		
		return dlist;
	}
	
}