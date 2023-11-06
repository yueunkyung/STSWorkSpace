package com.shinhan.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.dto.JobVO;
import com.shinhan.util.DBUtil;

//DAO(Data Access Object) CRUD작업 (Create Read Update Delete)
//						  insert into~, select~, update~, delete
//POJO(Plain Old Java Object) : 정해진 규칙없이 자바로 만들어진 class
//JDBC를 이용하여 만든 JAVA코드이다... 계속 사용예정..
public class JobDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	//모두조회
	public List<JobVO> selectAll() {
		List<JobVO> joblist = new ArrayList<>();
		String sql = "select * from jobs";
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				JobVO job = new JobVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
//				job.setJob_id( rs.getString(1));
//				job.setJob_title(rs.getString(2));
//				job.setMin_salary(rs.getInt(3));
//				job.setMax_salary(rs.getInt(4));
				joblist.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return joblist;
	}

}