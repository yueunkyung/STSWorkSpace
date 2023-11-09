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
public class EmpDAO {

	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	//자동으로 주입한다.(Spring이 Bean을 등록하고 주입)
	// 1.field, 2.생성자, 3.setter
	@Autowired //1.field
	@Qualifier("dataSource")
	DataSource ds;
	
	//2.생성자
	/*
	@Autowired
	EmpDAO(DataSource ds) {
		this.ds = ds;
	}
	*/
	
	//3.setter
	/*
	@Autowired
	void setDs() {
		this.ds = ds;
	}
	 */

	public int empInsert(EmpVO emp) {
		int result = 0;
		String sql = "insert into employees VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, emp.getEmployee_id());
			pst.setString(2, emp.getFirst_name());
			pst.setString(3, emp.getLast_name());
			pst.setString(4, emp.getEmail());
			pst.setString(5, emp.getPhone_number());
			pst.setDate(6, emp.getHire_date());
			pst.setString(7, emp.getJob_id());
			pst.setInt(8, emp.getSalary());
			pst.setDouble(9, emp.getCommission_pct());
			pst.setInt(10, emp.getManager_id());
			pst.setInt(11, emp.getDepartment_id());

			result = pst.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}	
		return result;
	}
	
	public int empUpdate(EmpVO emp) {
		int result = 0;
		String sql = "update employees \r\n"
				+ "	set first_name=?,\r\n"
				+ "		last_name=?,\r\n"
				+ "		email=?,\r\n"
				+ "		phone_number=?,\r\n"
				+ "		hire_date=?,\r\n"
				+ "		job_id=?,\r\n"
				+ "		salary=?,\r\n"
				+ "		commission_pct=?,\r\n"
				+ "		manager_id=?,\r\n"
				+ "		department_id=? \r\n"
				+ "	where employee_id=?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, emp.getFirst_name());
			pst.setString(2, emp.getLast_name());
			pst.setString(3, emp.getEmail());
			pst.setString(4, emp.getPhone_number());
			pst.setDate(5, emp.getHire_date());
			pst.setString(6, emp.getJob_id());
			pst.setInt(7, emp.getSalary());
			pst.setDouble(8, emp.getCommission_pct());
			pst.setInt(9, emp.getManager_id());
			pst.setInt(10, emp.getDepartment_id());
			pst.setInt(11, emp.getEmployee_id());
			
			result = pst.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}	
		return result;
	}

	public int empDelete(int empid) {
		String sql = "delete from employees where employee_id =?";
		int result = 0;
		// ? : binding 변수 (Statement는 지원안함)
		//Statement를 상속받은 하위 PreparedStatement
		try {
			conn = ds.getConnection();
			//conn.setAutoCommit(true); --DML
			//**SQL문 실행 후 자동 commit되는 것이 default이다 => 상황에 따라 조정이 가능하다.
			pst = conn.prepareStatement(sql); //SQL문 준비
			pst.setInt(1, empid); // ?에 값을 채운다.
			result = pst.executeUpdate(); //DML은 executeUpdate()로 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}	
		
		return result;
	}
	
	public EmpVO loginCheck(String email, int password) {
		EmpVO emp = null;
		String sql = "select *"
					+ " from employees where email = ? and department_id = ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setInt(2, password);
			rs = pst.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return emp;
	}

	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		String sql = "select * from employees where employee_id = ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, empid);
			rs = pst.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return emp;
	}
	
	public List<EmpVO> selectAll(int deptid, String jobid, int sal, String hiredate) {
		List<EmpVO> emplist = new ArrayList<>();
		String sql = "select * "
				+ " from employees "
				+ " where (0=? or department_id = ?)"
				+ " and job_id like ?"
				+ " and salary >= ?"
				+ " and hire_date >= ?"
				+ " order by 1";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			pst.setInt(2, deptid);
			pst.setString(3, jobid);
			pst.setInt(4, sal);
			pst.setString(5, hiredate);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return emplist;
	}
	
	public List<EmpVO> selectManagerAll() {
		List<EmpVO> emplist = new ArrayList<>();
		String sql = "select *"
					+ " from employees"
					+ " where employee_id in("
					+ " select DISTINCT manager_id from employees)";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return emplist;
	}
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(rs.getDouble("commission_pct"));
		emp.setDepartment_id(rs.getInt("department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getInt("salary"));
		return emp;
	}

}