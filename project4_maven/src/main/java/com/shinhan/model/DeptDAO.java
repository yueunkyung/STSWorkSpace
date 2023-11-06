package com.shinhan.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.DeptVO;
import com.shinhan.util.DBUtil;

//Spring이 DeptDAO를 객체 생성하고자 한다.
//Bean으로 등록한다.
//spring 설정파일 사용하는 경우 => <bean id="deptDAO" class="com.shinhan.model.DeptDAO" />
//어노테이션을 이용하기 : @Component + DAO 역할 => @Repository
@Repository
public class DeptDAO {
	
	@Autowired
	DataSource ds;
	
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	//특정 Location 조회
	public List<DeptVO> selectByLocation(int loc) {
		List<DeptVO> deptlist = new ArrayList<>();
		String sql = "select * from departments where location_id = ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql); //SQL문 준비
			pst.setInt(1, loc);
			rs = pst.executeQuery(sql);
			while(rs.next()) {
				DeptVO dept = makeDept(rs);//reset에서 읽어서 VO만들기
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
		return deptlist;
	}
	
	//Manager가 있는 부서조회
	public List<DeptVO> selectByManager() {
		List<DeptVO> deptlist = new ArrayList<>();
		String sql = "select * from departments where manager_id is not null";
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DeptVO dept = makeDept(rs);//reset에서 읽어서 VO만들기
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}	
		return deptlist;
	}

	//특정부서조회
	public DeptVO selectById(int deptid) {
		DeptVO dept = null;
		String sql = "select * from departments "
				+ " where department_id = " + deptid;

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				dept = makeDept(rs);//reset에서 읽어서 VO만들기				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		System.out.println("DeptDAO DeptDAO DeptDAO DeptDAO selectById............."+dept);
		return dept;
	}
	
	//모두조회
	public List<DeptVO> selectAll() {
		List<DeptVO> deptlist = new ArrayList<>();
		String sql = "select * from departments order by 1";

		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DeptVO dept = makeDept(rs);//reset에서 읽어서 VO만들기
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return deptlist;
	}
	
	private DeptVO makeDept(ResultSet rs) throws SQLException {
		DeptVO dept = new DeptVO();
		dept.setDepartment_id( rs.getInt(1));
		dept.setDepartment_name(rs.getString(2));
		dept.setLocation_id(rs.getInt("Location_id"));
		dept.setManager_id(rs.getInt("Manager_id"));
		return dept;
	}

	public int insertDept(DeptVO dept) {
		String sql = "insert into departments values( ?,?,?,? )";
		int count = 0;
		// ? : binding 변수 (Statement는 지원안함)
		//Statement를 상속받은 하위 PreparedStatement
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql); //SQL문 준비
			pst.setInt(1, dept.getDepartment_id()); // ?에 값을 채운다.
			pst.setString(2, dept.getDepartment_name());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4, dept.getLocation_id());
			count = pst.executeUpdate(); //DML은 executeUpdate()로 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}	
		
		return count;
	}

	public int updateDept(DeptVO dept) {
		String sql = "update departments set department_name=?, manager_id= ?, location_id=? where department_id =?";
		int count = 0;
		// ? : binding 변수 (Statement는 지원안함)
		//Statement를 상속받은 하위 PreparedStatement
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql); //SQL문 준비
			pst.setString(1, dept.getDepartment_name()); // ?에 값을 채운다.
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			pst.setInt(4, dept.getDepartment_id());
			count = pst.executeUpdate(); //DML은 executeUpdate()로 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}	
		
		return count;
	}

	public int deleteDept(int deptid) {
		String sql = "delete from departments where department_id =?";
		int count = 0;
		// ? : binding 변수 (Statement는 지원안함)
		//Statement를 상속받은 하위 PreparedStatement
		try {
			conn = ds.getConnection();
			//conn.setAutoCommit(true); --DML
			//**SQL문 실행 후 자동 commit되는 것이 default이다 => 상황에 따라 조정이 가능하다.
			pst = conn.prepareStatement(sql); //SQL문 준비
			pst.setInt(1, deptid); // ?에 값을 채운다.
			count = pst.executeUpdate(); //DML은 executeUpdate()로 실행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}	
		
		return count;
	}
}