package com.shinhan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.dto.CustomerVO;
import com.shinhan.util.DBUtil;

@Repository //@Component + DAO
public class CustomerDAO {
	
	@Autowired	//타입이 같으면 자동으로 주입한다.(injection)
	DataSource ds;
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	int result;

	//사용자가 존재하는지?
	public CustomerVO loginCheck(String custid, String pwd) {
		String sql = "select * from customer where cust_id = ? and password = ?";
		CustomerVO cust = null;
		try {
			conn =ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, custid);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if(rs.next()) {
				cust = new CustomerVO();
				cust.setCust_id(custid);
				cust.setCust_name(rs.getString("cust_name"));
				cust.setEmail(rs.getString("email"));
				cust.setPassword(pwd);
				cust.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
		return cust;
	}

	//회원가입
	public int register(CustomerVO customer) {
		String sql = "insert into customer(cust_id,cust_name,email,password,phone) values(?,?,?,?,?)";
		
		try {
			conn =ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, customer.getCust_id());
			pst.setString(2, customer.getCust_name());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getPassword());
			pst.setString(5, customer.getPhone());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
		return result;
	}
}
